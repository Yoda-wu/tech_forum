package com.uml.common.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.sql.Date;

/**
 * 关注实体
 * @author wuyuda
 * @date 2022-03-18 17:30
 */
@TableName("`follow`")
@SuppressWarnings("unused")
public class Follow extends BaseEntity{
    public static final String FOLLOW_ID = "`follow_id`";
    public static final String UID = "`uid`";

    @TableField(FOLLOW_ID)
    private Long followId;
    @TableField(UID)
    private Long uid;

    public Follow(Long id, int version, Date createTime, Date updateTime, boolean delete, Long followId, Long uid) {
        super(id, version, createTime, updateTime, delete);
        this.followId = followId;
        this.uid = uid;
    }

    public Follow() {
    }

    public Long getFollowId() {
        return followId;
    }

    public Follow setFollowId(Long followId) {
        this.followId = followId;
        return this;
    }

    public Long getUid() {
        return uid;
    }

    public Follow setUid(Long uid) {
        this.uid = uid;
        return this;
    }

    @Override
    public String toString() {
        return "Follow{" +
                "id=" + id +
                ", followId=" + followId +
                ", uid=" + uid +
                '}';
    }
}
