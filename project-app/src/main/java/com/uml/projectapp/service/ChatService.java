package com.uml.projectapp.service;

import com.uml.common.po.Chat;
import com.uml.common.vo.ChatListVo;
import com.uml.common.vo.ChatVo;

import java.util.List;

/**
 * @author wuyuda
 * @date 2022-03-19 16:10
 */
public interface ChatService {


    /**
     * 查询当前用户的会话列表
     * 针对每个会话值返回一条最新的私信
     *
     * @param uid     当前用户id
     * @param current 当前页
     * @param size    页面大小
     * @return 会话列表
     */
    ChatListVo selectConversation(Long uid, Integer current, Integer size);

    /**
     * 查询当前用户的会话数量
     *
     * @param uid 当前用户id
     * @return 会话数量
     */
    Integer selectConversationCount(Long uid);

    /**
     * 查询某个会话所包含的私信列表
     *
     * @param conversationId 会话id
     * @param current        当前页
     * @param size           页大小
     * @return 私信列表
     */
    ChatListVo selectLetters(String conversationId, Integer current, Integer size);

    /**
     * 某个会话所包含的私信数量
     *
     * @param conversationId 会话id
     * @return 私信数量
     */
    Integer selectLettersCount(String conversationId);

    /**
     * 查询未读私信数量
     *
     * @param uid            用户id
     * @param conversationId 会话id
     * @return 数量
     */
    Integer selectUnreadCount(Long uid, String conversationId);

    /**
     * 添加消息
     *
     * @param chat 消息对象
     * @return 插入的id
     */
    Long insertChatMessage(Chat chat);

    /**
     * 修改消息的状态
     *
     * @param ids    id集合
     *
     * @return 更新的数目
     */
    Long updateStatus(List<Long> ids);

    /**
     * 查询某个主题下最新的通知
     * @param uid 用户id
     * @param topic 主题
     * @return 最新的通知
     */
    ChatVo selectLatestNotice(Long uid,String topic);

    /**
     * 查询某个主题所包含的通知数量
     * @param uid 用户id
     * @param topic 主题
     * @return 通知数量
     */
    int selectNoticeCount(Long uid,String topic);

    /**
     * 查询未读系统消息的数量
     * @param uid 用户id
     * @param topic 主题
     * @return 通知数量
     */
    int selectUnreadNotice(Long uid, String topic);
}


