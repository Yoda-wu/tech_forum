package com.uml.projectapp.service;

import com.uml.common.constant.ArticleType;

/**
 * @author wuyuda
 * @date 2022-03-19 15:02
 */
public interface ArticleTagService {

    /**
     * 测试mybatis-plus是否连接成功
     */
    public void articleTagTestService();


    /**
     * 插入标签
     *
     * @param articleId 文章id
     * @param type      标签
     */
    public void insertTag(Long articleId, ArticleType type);

}
