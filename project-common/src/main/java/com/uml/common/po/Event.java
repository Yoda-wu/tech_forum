package com.uml.common.po;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wuyuda
 * @date 2022-05-19 20:59
 */
public class Event {

    /**
     * 事件主题
     */
    private String topic;
    /**
     * 事件发出的用户id
     */
    private Long userId;
    /**
     * 实体类型
     */
    private String entityType;
    /**
     * 实体id
     */
    private Long entityId;
    /**
     * 实体的作者id
     */
    private Long entityUserId;
    /**
     * 数据
     */
    private Map<String ,Object> data = new HashMap<>();

    public String getTopic() {
        return topic;
    }

    public Event setTopic(String topic) {
        this.topic = topic;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public Event setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getEntityType() {
        return entityType;
    }

    public Event setEntityType(String entityType) {
        this.entityType = entityType;
        return this;
    }

    public Long getEntityId() {
        return entityId;
    }

    public Event setEntityId(Long entityId) {
        this.entityId = entityId;
        return this;
    }

    public Long getEntityUserId() {
        return entityUserId;
    }

    public Event setEntityUserId(Long entityUserId) {
        this.entityUserId = entityUserId;
        return this;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public Event setData(String key, Object value) {
        this.data.put(key,value);
        return this;
    }
}
