package com.uml.common.utils;

/**
 * 生成redis的键
 *
 * @author wuyuda
 * @date 2022-04-02 21:00
 */
public class RedisKeyUtil {

    public static final String SPLIT = ":";
    public static final String PREFIX_FOLLOWEE = "followee";
    public static final String PREFIX_FOLLOWER = "follower";
    public static final String PREFIX_TICKET = "ticket";
    public static final String PREFIX_USER = "user";

    public static String generateKey(String entityType, Long entityId) {
        return entityType + SPLIT + entityId;
    }

    /**
     * 用户关注的实体是什么
     *
     * @param uid        用户id
     * @param entityType 实体类型（可以是用户，可以是文章）
     * @return 键值 -> zset  (entityId, now);
     */
    public static String generateFolloweeKey(Long uid, String entityType) {
        return PREFIX_FOLLOWEE + SPLIT + uid + SPLIT + entityType;
    }

    /**
     * 某个实体的粉丝
     *
     * @param entityId   实体id
     * @param entityType 实体类型
     * @return 键值 -> zset（uid，now）
     */
    public static String generateFollowerKey(Long entityId, String entityType) {
        return PREFIX_FOLLOWER + SPLIT + entityType + SPLIT + entityId;
    }

    /**
     * 登陆凭证
     * @param ticket 凭证
     * @return 登陆凭证的键值
     */
    public static String getTicketKey(String ticket){
        return PREFIX_TICKET + SPLIT + ticket;
    }

    public static String getUserKey(Long uid){
        return PREFIX_USER + SPLIT + uid;
    }
}
