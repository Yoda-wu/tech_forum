package com.uml.common.vo;

import com.uml.common.constant.ArticleState;
import com.uml.common.constant.ArticleType;
import com.uml.common.po.Article;

import java.sql.Date;

/**
 * @author wuyuda
 * @date 2022-03-29 17:03
 */
public class ArticleVo extends Article {

    private String name;
    private String avatar;

    public ArticleVo() {
    }

    public ArticleVo(Long id, int version, Date createTime, Date updateTime, boolean delete, Long uid, ArticleType type, String title, String content, String firstPicture, int likes, int views, ArticleState state, String username, String avatar) {
        super(id, version, createTime, updateTime, delete, uid, type, title, content, firstPicture, likes, views, state);
        this.name = username;
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
