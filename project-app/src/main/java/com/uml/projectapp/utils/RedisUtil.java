package com.uml.projectapp.utils;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;


/**
 * @author wuyuda
 * @date 2022-03-31 16:35
 */
@SuppressWarnings("unused")
@Component
public class RedisUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;


    /**
     * 指定缓存的时间、
     *
     * @param key  键
     * @param time 时间 单位秒
     * @return 是否设定成功
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据key获取过期的时间
     *
     * @param key 键 不能为空
     * @return 时间，返回0代表永久有效
     */
    public Long getExpire(@NonNull String key) {
        Long expire = redisTemplate.getExpire(key, TimeUnit.SECONDS);
        assert expire != null;
        return expire;
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在； false 不存在
     */
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 删除对应键值的缓存
     *
     * @param key 可以传一个值或多个值
     */
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(Arrays.asList(key));
            }
        }
    }

    /**
     * 获取缓存的值
     *
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 往缓存中放入值
     *
     * @param key   键
     * @param value 值
     * @return 是否成功放入
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 往缓存中放入值并设置过期时间
     *
     * @param key   键
     * @param value 值
     * @param time  过期时间
     * @return 是否成功放入
     */
    public boolean set(String key, Object value, Long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                return set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 递增
     *
     * @param key   键
     * @param delta 要增多少，必须大于0 默认为1
     * @return 是否递增成功
     */
    public Long incr(String key, Long delta) {
        if (delta < 0) {
            delta = 1L;
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 递减
     *
     * @param key   键
     * @param delta 要递减多少，必须小于0 默认为-1
     * @return 是否递减成功
     */
    public Long decr(String key, Long delta) {
        if (delta > 0) {
            delta = -1L;
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }


}

