package com.uml.projectapp.service.impl;

import com.uml.common.constant.Constant;
import com.uml.common.po.User;
import com.uml.common.utils.RedisKeyUtil;
import com.uml.projectapp.dao.UserDao;
import com.uml.projectapp.service.FollowService;
import com.uml.projectapp.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author wuyuda
 * @date 2022-04-22 14:49
 */
@Service
public class FollowServiceImpl implements FollowService {
    private final UserDao userDao;
    private final Logger logger = LoggerFactory.getLogger(FollowServiceImpl.class);

    public FollowServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public String followOperation(Long uid, String entityType, Long entityId, Boolean remove) {

        if (remove) {
            RedisUtil.sessionCallback(new SessionCallback<>() {
                @Override
                public Object execute(RedisOperations operations) throws DataAccessException {
                    String followeeKey = RedisKeyUtil.generateFolloweeKey(uid, entityType);
                    String followerKey = RedisKeyUtil.generateFollowerKey(entityId, entityType);
                    logger.info("[follow operation]:>>>>>> followee key is : "+followeeKey);
                    logger.info("[follow operation]:>>>>>> follower key is : "+followerKey);
                    operations.multi();
                    operations.opsForZSet().remove(followeeKey, entityId, System.currentTimeMillis());
                    operations.opsForZSet().remove(followerKey, uid, System.currentTimeMillis());
                    return operations.exec();
                }
            });
        } else {
            RedisUtil.sessionCallback(new SessionCallback<>() {

                @Override
                public Object execute(RedisOperations operations) throws DataAccessException {
                    String followeeKey = RedisKeyUtil.generateFolloweeKey(uid, entityType);
                    String followerKey = RedisKeyUtil.generateFollowerKey(entityId, entityType);
                    logger.info("[follow operation]:>>>>>> followee key is : "+followeeKey);
                    logger.info("[follow operation]:>>>>>> follower key is : "+followerKey);
                    operations.multi();
                    operations.opsForZSet().add(followeeKey, entityId, System.currentTimeMillis());
                    operations.opsForZSet().add(followerKey, uid, System.currentTimeMillis());
                    return operations.exec();
                }
            });
        }
        return "";
    }

    @Override
    public boolean getFolloweeState(Long uid, String entityType, Long entityId) {

        String followeeKey = RedisKeyUtil.generateFolloweeKey(uid, entityType);
        logger.info("[follow operation - get state ]:>>>>>> followee key is : "+followeeKey);
        return RedisUtil.zSetScore(followeeKey, entityId) != null;
    }


    /**
     * 查询用户关注的人或者查询用户的粉丝
     */
    @Override
    public List<Map<String, Object>> findFollowLists(Long uid, Integer offset, Integer limit, Boolean findFans) {
        String key;
        if (findFans) {
            key = RedisKeyUtil.generateFollowerKey(uid, Constant.USER);
        } else {
            key = RedisKeyUtil.generateFolloweeKey(uid, Constant.USER);
        }
        logger.info("[follow list: ]>>>>>>>>>>>>>>> "+key);
        Set<Object> objects = RedisUtil.zSetRange(key, offset, limit);
        if (objects == null) {
            return null;
        }
        List<Map<String, Object>> res = new ArrayList<>();
        for (Object object : objects) {
            Map<String, Object> map = new HashMap<>();
            User user = userDao.selectById((Integer) object);
            map.put("user", user);
            Double score = RedisUtil.zSetScore(key, object);
            map.put("followTime", new Date(score.longValue()));
            res.add(map);
        }
        return res;
    }

    @Override
    public Long getFolloweeNumber(Long uid) {
        String key = RedisKeyUtil.generateFolloweeKey(uid, Constant.USER);
        logger.info("[getFolloweeNumber : ]>>>>>>>>>>>>>>> "+key);
        return RedisUtil.zSetSize(key);
    }

    @Override
    public Long getFollowerNumber(Long uid) {
        String key = RedisKeyUtil.generateFollowerKey(uid, Constant.USER);
        logger.info("[getFollowerNumber: ]>>>>>>>>>>>>>>> "+key);
        return RedisUtil.zSetSize(key);
    }


}
