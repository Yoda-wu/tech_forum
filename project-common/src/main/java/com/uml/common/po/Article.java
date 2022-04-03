package com.uml.common.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.uml.common.constant.ArticleState;
import com.uml.common.constant.ArticleType;

import java.sql.Date;

/**
 * 文章实体
 *
 * @author wuyuda
 * @date 2022-03-18 17:29
 */
@SuppressWarnings(value = "unused")
@TableName(value = "`article`")
public class Article extends BaseEntity {

    public static final String UID = "`uid`";
    public static final String TYPE = "`type`";
    public static final String TITLE = "`title`";
    public static final String CONTENT = "`content`";
    public static final String FIRST_PICTURE = "`first_picture`";
    public static final String LIKES = "`likes`";
    public static final String VIEWS = "`views`";
    public static final String STATE = "`state`";

    @TableField(UID)
    private Long uid;

    @TableField(TYPE)
    private ArticleType type;

    @TableField(TITLE)
    private String title;

    @TableField(CONTENT)
    private String content;

    @TableField(FIRST_PICTURE)
    private String firstPicture;

    @TableField(LIKES)
    private int likes;

    @TableField(VIEWS)
    private int views;

    @TableField(STATE)
    private ArticleState state;

    public Article() {
    }

    public Article(Long id, int version, Date createTime, Date updateTime, boolean delete, Long uid, ArticleType type, String title, String content, String firstPicture, int likes, int views, ArticleState state) {
        super(id, version, createTime, updateTime, delete);
        this.uid = uid;
        this.type = type;
        this.title = title;
        this.content = content;
        this.firstPicture = firstPicture;
        this.likes = likes;
        this.views = views;
        this.state = state;
    }

    public Long getUid() {
        return uid;
    }

    public Article setUid(Long uid) {
        this.uid = uid;
        return this;
    }

    public ArticleType getType() {
        return type;
    }

    public Article setType(ArticleType type) {
        this.type = type;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Article setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Article setContent(String content) {
        this.content = content;
        return this;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public Article setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
        return this;
    }

    public int getLikes() {
        return likes;
    }

    public Article setLikes(int likes) {
        this.likes = likes;
        return this;
    }

    public int getViews() {
        return views;
    }

    public Article setViews(int views) {
        this.views = views;
        return this;
    }

    public ArticleState getState() {
        return state;
    }

    public Article setState(ArticleState state) {
        this.state = state;
        return this;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", uid=" + uid +
                ", type=" + type +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", firstPicture='" + firstPicture + '\'' +
                ", likes=" + likes +
                ", views=" + views +
                ", state=" + state +
                '}';
    }
}
