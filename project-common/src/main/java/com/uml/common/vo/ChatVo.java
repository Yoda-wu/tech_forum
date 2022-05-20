package com.uml.common.vo;

import com.uml.common.po.Chat;

import java.util.Date;

/**
 * @author wuyuda
 * @date 2022-05-19 21:34
 */
public class ChatVo extends Chat {
    private String name;
    private String avatar;

    public ChatVo() {
    }

    public ChatVo(Long id, int version, Date createTime, Date updateTime, boolean delete, Long senderId, Long receiverId, String content, String conservationId, Integer statu, String name, String avatar) {
        super(id, version, createTime, updateTime, delete, senderId, receiverId, content, conservationId, statu);
        this.name = name;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
