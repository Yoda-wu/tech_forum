package com.uml.common.vo;

import com.uml.common.po.Comment;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * @author wuyuda
 * @date 2022-04-21 19:14
 */
public class CommentVo implements Serializable {
    private String name;
    private String avatar;
    private Long uid;
    private Long id;
    private String content;
    public CommentVo() {
    }

    public CommentVo(String name, String avatar, Long uid, Long id, String content) {
        this.name = name;
        this.avatar = avatar;
        this.uid = uid;
        this.id = id;
        this.content = content;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
