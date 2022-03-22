package com.uml.projectapp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.uml.common.constant.ArticleState;
import com.uml.common.constant.ArticleType;
import com.uml.common.constant.ErrorCode;
import com.uml.common.po.Article;
import com.uml.common.utils.ResultUtil;
import com.uml.common.vo.ArticleVo;
import com.uml.projectapp.dao.ArticleDao;
import com.uml.projectapp.service.ArticleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wuyuda
 * @date 2022-03-19 14:45
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleDao articleDao;

    ArticleServiceImpl(ArticleDao articleDao){
        this.articleDao = articleDao;
    }

    @Override
    public void articleTestService() {
        List<Article> articles = articleDao.selectList(null);
        if(articles != null){
            articles.forEach(
                    System.out::println
            );
        }
        else {
            System.out.println("Error");
        }
    }

    @Override
    public String insertArticle(Long uid, String title, String content, String type, ArticleState state) throws JsonProcessingException {
        Article article = new Article();
        article.setUid(uid);
        article.setTitle(title);
        article.setContent(content);
        article.setType(ArticleType.valueOf(type));
        article.setState(state);
        articleDao.insert(article);
        System.out.println(article.getId());
        return ResultUtil.generateResult(ErrorCode.SUCCESS,article.getId());
    }

    @Override
    public String listPublishedArticle(Integer current, Integer size) throws JsonProcessingException {
        ArticleVo articleVo = new ArticleVo();
        Page<Article> page = new Page<>(current, size);
        QueryWrapper<Article> wrapper = new QueryWrapper<>();

        articleDao.selectPage(page, wrapper.eq(Article.STATE,ArticleState.PUBLISHED.name()));
        articleVo.setCurrent(current);
        articleVo.setSize(size);
        articleVo.setArticles(page.getRecords());
        articleVo.setTotal(page.getTotal());
        return ResultUtil.generateResult(ErrorCode.SUCCESS,articleVo);
    }

    @Override
    public String updateArticle(Article article) throws JsonProcessingException {
        articleDao.updateById(article);
        return ResultUtil.generateResult(ErrorCode.SUCCESS,article);
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
        }else{
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
