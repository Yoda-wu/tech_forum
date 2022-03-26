package com.uml.common.vo;

import com.uml.common.po.Article;
import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * @author wuyuda
 * @date 2022-03-22 16:57
 */
@ApiModel
public class ArticleVo {

    private Integer current;
    private Integer size;
    private Long total;
    private List<Article> articles;

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

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
