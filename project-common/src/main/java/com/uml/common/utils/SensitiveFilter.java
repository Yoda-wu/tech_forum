package com.uml.common.utils;


import io.netty.util.CharsetUtil;
import org.apache.commons.codec.binary.CharSequenceUtils;
import org.apache.http.util.CharsetUtils;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.apache.commons.lang.CharUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wuyuda
 * @date 2022-04-26 16:19
 */
@Component
public class SensitiveFilter {

    private static final Logger logger = LoggerFactory.getLogger(SensitiveFilter.class);
    private static final String REPLACE = "**";
    /**
     * 根节点
     */
    private TrieNode rootNode = new TrieNode();

    @PostConstruct
    public void init() {
        logger.info("init.................");
        try (
                InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("sensitive_word.txt");

        ) {
            assert resourceAsStream != null;
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream,"utf-8"));
            ) {
                String keyword;
                while ((keyword = bufferedReader.readLine()) != null) {
                    // 添加到前缀树
                    this.addKeyWord(keyword);
                }

            }
        } catch (Exception e) {
            logger.info("敏感词文件读取失败");
        }
    }

    /**
     * 将一个敏感词添加到前缀树中
     *
     * @param keyword 铭感词
     */
    private void addKeyWord(String keyword) {
        logger.info("adding key word.......");
        TrieNode tempNode = rootNode;
        for (int i = 0; i < keyword.length(); i++) {
            char c = keyword.charAt(i);
            logger.info("adding key word " + c);
            TrieNode subNode = tempNode.getSubNode(c);
            if (subNode == null) {
                // 初始化子节点
                subNode = new TrieNode();
                tempNode.setSubNode(c, subNode);
            }

            // 将指针指向子节点
            tempNode = subNode;
            // 设置结束标识
            if (i == keyword.length() - 1) {
                logger.info("setting key end.......... ");
                tempNode.setKeyEnd(true);
            }
        }
    }

    /**
     * 过滤敏感词
     *
     * @param text 待过滤的文本
     * @return 过滤后的文本
     */
    public String filter(String text) {
        if(text == null || text.length() < 1){
            return null;
        }
        // 指针遍历
        TrieNode tempNode = rootNode;
        // 起始位置
        int begin = 0;
        // 终止位置
        int position = 0;
        // 最终结果
        StringBuilder stringBuilder = new StringBuilder();
        while (position < text.length()){
            char c = text.charAt(position);
            // 跳过符号
            if(isSymbol(c)){
                logger.info("Symbol "+ c);
                // 若指针处于根节点则将此符号计入结果，让指针2走下一步
                if(tempNode == rootNode){
                    stringBuilder.append(c);
                    begin++;
                }
                // 无论符号是什么情况，都会向下走
                position++;
                continue;
            }
            // 检查下级节点
            tempNode = tempNode.getSubNode(c);
            if(tempNode == null){
                logger.info("detect "+c+"....");
                // 以begin开头的字符串不是敏感词
                tempNode = rootNode;
                stringBuilder.append(c);
                // 进入下一个位置
                position = ++begin;
            }else if(tempNode.isKeyEnd()){
                logger.info("replace "+c+" to *");
                stringBuilder.append(REPLACE);
                // 进入下一个位置
                begin = ++position;
                // 重新指向根节点
                tempNode = rootNode;
            }else {
                // 检查下一个字符
                ++position;
            }
        }
        stringBuilder.append(text.substring(begin));
        return stringBuilder.toString();
    }

    private boolean isSymbol(Character c){
        return CharUtils.isAsciiAlphanumeric(c) && (c < 0x2E80 || c > 0x9FFF);
    }
    private class TrieNode {
        /**
         * 关键词结束的标识
         */
        private boolean isKeyEnd = false;

        /**
         * 子节点(key 是下级节点字符，value是下级节点）
         */
        private Map<Character, TrieNode> subNode = new HashMap<>();

        /**
         * 添加子节点
         *
         * @param character 键
         * @param node      值
         */
        public void setSubNode(Character character, TrieNode node) {
            subNode.put(character, node);
        }

        /**
         * 获取子节点
         *
         * @param key 键
         * @return 子节点
         */
        public TrieNode getSubNode(Character key) {
            return subNode.get(key);
        }

        public boolean isKeyEnd() {
            return isKeyEnd;
        }

        public void setKeyEnd(boolean keyEnd) {
            isKeyEnd = keyEnd;
        }
    }


}
