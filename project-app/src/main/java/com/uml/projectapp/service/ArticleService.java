package com.uml.projectapp.service;

import com.uml.common.constant.ArticleState;
import com.uml.common.constant.ArticleType;

/**
 * @author wuyuda
 * @date 2022-03-19 14:43
 */

public interface ArticleService {

    /**
     * 测试mybatis-plus是否连接成功
     */
    public void articleTestService();


    /**
     * 往数据库里新增一篇文章
     */
    public void insertArticle(Long uid, String title, String content, ArticleType type, ArticleState state);
}
