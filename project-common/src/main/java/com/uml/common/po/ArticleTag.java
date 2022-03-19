package com.uml.common.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.uml.common.constant.ArticleType;

import java.sql.Date;

/**
 * 标签实体
 *
 * @author wuyuda
 * @date 2022-03-18 17:30
 */
@SuppressWarnings(value = "unused")
@TableName("`article_tag`")
public class ArticleTag extends BaseEntity{

    public static final String ARTICLE_ID = "`article_id`";
    public static final String TAG_NAME = "`tag_name`";

    @TableField(ARTICLE_ID)
    private Long articleId;

    @TableField(TAG_NAME)
    private ArticleType tagName;

    public ArticleTag() {
    }

    public ArticleTag(Long id, int version, Date createTime, Date updateTime, boolean delete, Long articleId, ArticleType tagName) {
        super(id, version, createTime, updateTime, delete);
        this.articleId = articleId;
        this.tagName = tagName;
    }

    public Long getArticleId() {
        return articleId;
    }

    public ArticleTag setArticleId(Long articleId) {
        this.articleId = articleId;
        return this;
    }

    public ArticleType getTagName() {
        return tagName;
    }

    public ArticleTag setTagName(ArticleType tagName) {
        this.tagName = tagName;
        return this;
    }

    @Override
    public String toString() {
        return "ArticleTag{" +
                "articleId=" + articleId +
                ", tagName=" + tagName +
                ", id=" + id +
                '}';
    }
}
