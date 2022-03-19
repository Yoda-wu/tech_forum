package com.uml.common.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.sql.Date;

/**
 * 点赞实体
 * @author wuyuda
 * @date 2022-03-18 17:31
 */
@SuppressWarnings("unused")
@TableName("`like`")
public class Like extends BaseEntity{

    public static final String UID = "`uid`";

    @TableField(UID)
    private Long uid;

    public Like() {
    }

    public Like(Long id, int version, Date createTime, Date updateTime, boolean delete, Long uid) {
        super(id, version, createTime, updateTime, delete);
        this.uid = uid;
    }

    public Long getUid() {
        return uid;
    }

    public Like setUid(Long uid) {
        this.uid = uid;
        return this;
    }

    @Override
    public String toString() {
        return "Like{" +
                "id=" + id +
                ", uid=" + uid +
                '}';
    }
}
