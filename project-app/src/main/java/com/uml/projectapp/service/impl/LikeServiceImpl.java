package com.uml.projectapp.service.impl;

import com.uml.common.constant.Constant;
import com.uml.common.utils.RedisKeyUtil;
import com.uml.projectapp.service.LikeService;
import com.uml.projectapp.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wuyuda
 * @date 2022-04-02 21:37
 */
@Service
public class LikeServiceImpl implements LikeService {

    private final static Logger logger = LoggerFactory.getLogger(LikeServiceImpl.class);
    @Override
    public void like(String type, Long id, Long uid) {
        // 准备点赞的键值
        String likeKey = RedisKeyUtil.generateKey(type + RedisKeyUtil.SPLIT + Constant.LIKE, id);
        logger.info("[like]>>>> key is "+ likeKey);
        // 判断该用户是否在这个键值对应的集合中
        Boolean isMember = RedisUtil.isSetMember(likeKey, uid);
        logger.info("[like]>>>> is member:  "+isMember);
        if (isMember) {
            // 如果是则移除用户
            RedisUtil.removeSetItem(likeKey, uid);
            System.out.println(RedisUtil.isSetMember(likeKey, uid));
        } else {
            // 如果不是则添加用户
            RedisUtil.addSetItem(likeKey, uid);
        }
    }

    @Override
    public Map<String,Object> countLike(String type, Long id) {
        Map<String,Object> map = new HashMap<>();
        // 准备点赞的键值
        String likeKey = RedisKeyUtil.generateKey(type + RedisKeyUtil.SPLIT + Constant.LIKE, id);
        logger.info("[countLike]>>>> key is :  "+likeKey);
        // 统计点赞数
        Long likeNumber = RedisUtil.setSize(likeKey);
        map.put("likeNumber",likeNumber);
        return map;
    }

    @Override
    public Map<String,Object> userLikeState(String type, Long id, Long uid) {
        Map<String,Object> map = new HashMap<>();
        // 准备点赞的键值
        String likeKey = RedisKeyUtil.generateKey(type + RedisKeyUtil.SPLIT + Constant.LIKE, id);
        logger.info("[userLikeState]>>>> key is :  "+likeKey);
        // 返回该用户是否在对应的集合中，如果是则认为该用户点赞了，如果不是则认为该用户没点赞。
        int likeState = RedisUtil.isSetMember(likeKey, uid) ? 1 : 0;
        map.put("likeState",likeState);
        return map;
    }
}
