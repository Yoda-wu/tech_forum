package com.uml.common.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.sql.Date;

/**
 * 消息实体
 * @author wuyuda
 * @date 2022-03-18 17:30
 */
@TableName("`chat`")
@SuppressWarnings(value = "unused")
public class Chat extends BaseEntity{

    public static final String SENDER_ID = "`sender_id`";
    public static final String RECEIVER_ID = "`receiver_id`";
    public static final String CONTENT = "`content`";

    @TableField(SENDER_ID)
    private Long senderId;

    @TableField(RECEIVER_ID)
    private Long receiverId;

    @TableField(CONTENT)
    private String content;

    public Chat(Long senderId) {
        this.senderId = senderId;
    }

    public Chat(Long id, int version, Date createTime, Date updateTime, boolean delete, Long senderId, Long receiverId, String content) {
        super(id, version, createTime, updateTime, delete);
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
    }


    public Long getSenderId() {
        return senderId;
    }

    public Chat setSenderId(Long senderId) {
        this.senderId = senderId;
        return this;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public Chat setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Chat setContent(String content) {
        this.content = content;
        return this;

    }

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", content='" + content + '\'' +
                '}';
    }
}
