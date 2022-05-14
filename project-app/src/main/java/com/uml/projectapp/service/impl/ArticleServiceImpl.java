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
import com.uml.common.utils.SensitiveFilter;
import com.uml.common.vo.ArticleListVo;
import com.uml.common.vo.ArticleVo;
import com.uml.projectapp.dao.ArticleDao;
import com.uml.projectapp.service.ArticleService;
import com.uml.projectapp.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * 敏感词过滤器
     */
    private final SensitiveFilter sensitiveFilter;

    ArticleServiceImpl(ArticleDao articleDao, SensitiveFilter sensitiveFilter) {
        this.articleDao = articleDao;
        this.sensitiveFilter = sensitiveFilter;
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
    public String insertArticle(Article article, ArticleState state) throws JsonProcessingException {
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
    public Map<String,Object> setView(Long id, Long uid) {
        Map<String,Object> map = new HashMap<>();
        logger.info("[Setting views]...........");
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
        logger.info("[Setting views]..........."+viewNumber);
        // 更新数据库文章的浏览量
        updateById(new UpdateWrapper<Article>().eq(Constant.ID, id).set(Constant.VIEW, viewNumber));
        // 返回新的浏览量。
        map.put("ViewNumber",viewNumber);
        return map;
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
            return insertArticle(article,
                    ArticleState.SAVING);
        } else {
            article.setState(ArticleState.SAVING);
            // 发布草稿箱里的文章
            return updateArticle(article);
        }
    }

    @Override
    public String publishArticle(Article article) throws JsonProcessingException {
        logger.info("[发布文章] --- "+String.valueOf(article));
        // 发布非草稿箱里的文章
        if (article.getId() == null) {
            logger.info("[ID 为空] --- ");
            article.setContent(sensitiveFilter.filter(article.getContent()));
            article.setTitle(sensitiveFilter.filter(article.getTitle()));
            return insertArticle(article,
                    ArticleState.PUBLISHED);
        } else {
            article.setState(ArticleState.PUBLISHED);
            // 发布草稿箱里的文章
            return updateArticle(article);
        }
    }
}
