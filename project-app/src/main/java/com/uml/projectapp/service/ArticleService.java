package com.uml.projectapp.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.uml.common.constant.ArticleState;
import com.uml.common.constant.ArticleType;
import com.uml.common.po.Article;
import com.uml.common.vo.ArticleListVo;
import com.uml.common.vo.ArticleVo;

import java.util.List;
import java.util.Map;

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
     *
     * @param uid     用户id
     * @param title   文章标题
     * @param content 内容
     * @param type    类型
     * @param state   状态
     * @return 增加是否成功的消息
     * @throws JsonProcessingException json处理异常
     */
    public String insertArticle(Long uid, String title, String content, String type, ArticleState state) throws JsonProcessingException;

    /**
     * 更改文章
     *
     * @param article 文章实体类
     * @return 文章的id
     * @throws JsonProcessingException json处理异常
     */
    public String updateArticle(Article article) throws JsonProcessingException;

    /**
     * 通过id更新
     * @param wrapper 更新条件
     * @return 更新结果
     */
    public int updateById(UpdateWrapper<Article> wrapper);

    /**
     * 保存文章--未发布
     *
     * @param article 文章实体
     * @return 未发布文章信息
     * @throws JsonProcessingException json处理异常
     */
    public String saveArticle(Article article) throws JsonProcessingException;

    /**
     * 发布文章
     *
     * @param article 文章实体
     * @return 未发布文章信息
     * @throws JsonProcessingException json处理异常
     */
    public String publishArticle(Article article) throws JsonProcessingException;

    /**
     * 分页获取已发布的文章
     *
     * @param current 当前页面
     * @param size    页面大小
     * @return 页面文章列表
     */
    public ArticleListVo listPublishedArticle(Integer current, Integer size) ;

    /**
     * 删除用户的文章
     * @param articleId 文章id
     * @return 是否删除成功
     */
    public Boolean deleteArticle(Long articleId);

    /**
     * 设置浏览量
     * @param id 文章的id
     * @param uid 用户id
     * @return 流量量。
     */
    public Long setView(Long id, Long uid);


}
