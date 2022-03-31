package com.uml.projectapp.service.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uml.common.constant.ArticleState;
import com.uml.common.constant.ArticleType;
import com.uml.common.constant.ErrorCode;
import com.uml.common.po.Article;
import com.uml.common.utils.ResultUtil;
import com.uml.common.vo.ArticleListVo;
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
    private final ObjectMapper objectMapper;
    ArticleServiceImpl(ArticleDao articleDao, ObjectMapper objectMapper){
        this.articleDao = articleDao;
        this.objectMapper = objectMapper;
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
        ArticleListVo articleVo = new ArticleListVo();
        List<ArticleVo> articleVos = articleDao.listPublishedArticle(current, size);
        articleVo.setCurrent(current);
        articleVo.setSize(size);
        articleVo.setArticles(articleVos);
        articleVo.setTotal((long) articleVos.size());
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(articleVo);

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
