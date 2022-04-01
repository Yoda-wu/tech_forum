package com.uml.common.vo;

import com.uml.common.po.Article;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.List;

/**
 * @author wuyuda
 * @date 2022-03-22 16:57
 */
@ApiModel
public class ArticleListVo implements Serializable {

    private Integer current;
    private Integer size;
    private Long total;
    private List<ArticleVo> articles;

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<ArticleVo> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleVo> articles) {
        this.articles = articles;
    }
}
