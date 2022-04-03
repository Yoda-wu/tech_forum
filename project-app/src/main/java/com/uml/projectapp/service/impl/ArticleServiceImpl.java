package com.uml.projectapp.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uml.common.constant.ArticleState;
import com.uml.common.constant.ArticleType;
import com.uml.common.constant.Constant;
import com.uml.common.constant.ErrorCode;
import com.uml.common.po.Article;
import com.uml.common.utils.RedisKeyUtil;
import com.uml.common.utils.ResultUtil;
import com.uml.common.vo.ArticleListVo;
import com.uml.common.vo.ArticleVo;
import com.uml.projectapp.dao.ArticleDao;
import com.uml.projectapp.service.ArticleService;
import com.uml.projectapp.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wuyuda
 * @date 2022-03-19 14:45
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    /**
     * 日志输出
     */
    private final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);
    /**
     * dao层对象
     */
    private final ArticleDao articleDao;
    /**
     * jackson将对象转换为json对象。
     */
    private final ObjectMapper objectMapper;

    ArticleServiceImpl(ArticleDao articleDao, ObjectMapper objectMapper) {
        this.articleDao = articleDao;
        this.objectMapper = objectMapper;
    }

    @Override
    public void articleTestService() {
        List<Article> articles = articleDao.selectList(null);
        if (articles != null) {
            articles.forEach(
                    System.out::println
            );
        } else {
            System.out.println("Error");
        }
    }


    @Override
    public String insertArticle(Long uid, String title, String content, String type, ArticleState state) throws JsonProcessingException {
        // 添加一个新的文章
        Article article = new Article();
        // 设置好文章的用户id
        article.setUid(uid);
        // 文章的标题
        article.setTitle(title);
        // 文章的内容
        article.setContent(content);
        // 文章的类型
        article.setType(ArticleType.valueOf(type));
        // 文章的状态
        article.setState(state);
        // 调用dao层往数据库中加插入文章
        articleDao.insert(article);
        // 日志输出 --- TODO：  改到aop 2022.04.03
        logger.info("[Inserting article ]>>>>>>>>>" + article);
        // 返回结果
        return ResultUtil.generateResult(ErrorCode.SUCCESS, article.getId());
    }

    @Override
    public ArticleListVo listPublishedArticle(Integer current, Integer size) {
        // 准备好结果的视图层对象
        ArticleListVo articleListVo = new ArticleListVo();
        // 调用dao层分页查询
        List<ArticleVo> articleVos = articleDao.listPublishedArticle(current, size);
        // 把查询结果放到结果的视图层对象
        articleListVo.setCurrent(current);
        articleListVo.setSize(size);
        articleListVo.setArticles(articleVos);
        articleListVo.setTotal((long) articleVos.size());
        // 返回结果
        return articleListVo;

    }

    @Override
    public Boolean deleteArticle(Long articleId) {
        // 日志输出 -- TODO 转移到aop  2022.04.03
        logger.info("[deleting article]>>>>>>>>>" + articleDao.exists(new QueryWrapper<Article>().eq("id", articleId)));
        // 逻辑删除
        return articleDao.deleteById(articleId) > 0;
    }

    @Override
    public Long setView(Long id, Long uid) {
        // 设置文章浏览量的键值
        String viewKey = RedisKeyUtil.generateKey(Constant.ARTICLE + RedisKeyUtil.SPLIT + Constant.VIEW, id);
        // 设置用户浏览的键值 -- 为了历史浏览功能
        String userViewKey = RedisKeyUtil.generateKey(Constant.USER + RedisKeyUtil.SPLIT + Constant.VIEW, uid);
        // 把用户加入到文章浏览量集合中
        RedisUtil.addSetItem(viewKey, uid);
        // 把文章加入用户浏览的集合中
        RedisUtil.addSetItem(userViewKey, id);
        // 获取浏览量
        Long viewNumber = RedisUtil.setSize(viewKey);
        // 更新数据库文章的浏览量
        updateById(new UpdateWrapper<Article>().eq(Constant.ID,id).set(Constant.VIEW, viewNumber));
        // 返回新的浏览量。
        return viewNumber;
    }

    @Override
    public String updateArticle(Article article) throws JsonProcessingException {
        // 更新文章 -- 直接传入更新的文章对象
        articleDao.updateById(article);
        return ResultUtil.generateResult(ErrorCode.SUCCESS, article);
    }

    @Override
    public int updateById(UpdateWrapper<Article> wrapper) {
        // 通过id更新文章，在数据库中更新某个字段。
        return articleDao.update(null, wrapper);

    }

    @Override
    public String saveArticle(Article article) throws JsonProcessingException {
        // 发布非草稿箱里的文章
        if (article.getId() == null) {
            return insertArticle(article.getUid(),
                    article.getTitle(),
                    article.getContent(),
                    article.getType().name(),
                    ArticleState.SAVING);
        } else {
            article.setState(ArticleState.SAVING);
            // 发布草稿箱里的文章
            return updateArticle(article);
        }
    }

    @Override
    public String publishArticle(Article article) throws JsonProcessingException {
        // 发布非草稿箱里的文章
        if (article.getId() == null) {
            return insertArticle(article.getUid(),
                    article.getTitle(),
                    article.getContent(),
                    article.getType().name(),
                    ArticleState.PUBLISHED);
        } else {
            article.setState(ArticleState.PUBLISHED);
            // 发布草稿箱里的文章
            return updateArticle(article);
        }
    }
}
