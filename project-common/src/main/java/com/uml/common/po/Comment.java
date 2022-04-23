package com.uml.common.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.sql.Date;

/**
 * 评论实体
 *
 * @author wuyuda
 * @date 2022-03-18 17:30
 */
@SuppressWarnings(value = "unused")
@TableName("`comment`")
public class Comment extends BaseEntity {

    public static final String UID = "`uid`";
    public static final String ARTICLE_ID = "`article_id`";
    public static final String PARENT_ID = "`parent_id`";
    public static final String CONTENT = "`content`";
    public static final String LIKES = "`likes`";


    @TableField(UID)
    private Long uid;
    @TableField(ARTICLE_ID)
    private Long articleId;
    @TableField(PARENT_ID)
    private Long parentId;
    @TableField(CONTENT)
    private String content;
    @TableField(LIKES)
    private int likes;

    public Comment(Long id, int version, Date createTime, Date updateTime, boolean delete, Long uid, Long articleId, Long parentId, String content, int likes) {
        super(id, version, createTime, updateTime, delete);
        this.uid = uid;
        this.articleId = articleId;
        this.parentId = parentId;
        this.content = content;
        this.likes = likes;
    }

    public Comment() {
    }

    public Long getUid() {
        return uid;
    }

    public Comment setUid(Long uid) {
        this.uid = uid;
        return this;

    }

    public Long getArticleId() {
        return articleId;
    }

    public Comment setArticleId(Long articleId) {
        this.articleId = articleId;
        return this;

    }

    public Long getParentId() {
        return parentId;
    }

    public Comment setParentId(Long parentId) {
        this.parentId = parentId;
        return this;

    }

    public String getContent() {
        return content;
    }

    public Comment setContent(String content) {
        this.content = content;
        return this;

    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", uid=" + uid +
                ", articleId=" + articleId +
                ", parentId=" + parentId +
                ", content='" + content + '\'' +
                ", likes=" + likes +
                '}';
    }
}
