package com.uml.common.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

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
    public static final String CONVERSATION_ID = "`conversation_id`";
    public static final String STATUS = "`status`";

    @TableField(SENDER_ID)
    private Long senderId;

    @TableField(RECEIVER_ID)
    private Long receiverId;

    @TableField(CONTENT)
    private String content;

    @TableField(CONVERSATION_ID)
    private String conversationId;

    /**
     * 0 --- 未读
     * 1 --- 已读
     */
    @TableField(STATUS)
    private Integer status;

    public Chat() {
    }

    public Chat(Long id, int version, Date createTime, Date updateTime, boolean delete, Long senderId, Long receiverId, String content, String conservationId, Integer statu) {
        super(id, version, createTime, updateTime, delete);
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
        this.conversationId = conservationId;
        this.status = statu;
    }

    public String getConservationId() {
        return conversationId;
    }

    public void setConservationId(String conservationId) {
        this.conversationId = conservationId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
                ", conservationId=" + conversationId +
                ", status=" + status +
                '}';
    }
}
