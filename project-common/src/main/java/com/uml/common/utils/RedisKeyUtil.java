package com.uml.common.utils;

/**
 * 生成redis的键
 * @author wuyuda
 * @date 2022-04-02 21:00
 */
public class RedisKeyUtil {

    public static final String SPLIT = ":";

    public static String generateKey(String entityType, Long entityId) {
        return entityType + SPLIT + entityId;
    }
}
