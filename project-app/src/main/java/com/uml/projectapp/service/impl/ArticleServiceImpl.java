package com.uml.projectapp.service.impl;

import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.uml.common.constant.ArticleState;
import com.uml.common.constant.ArticleType;
import com.uml.common.po.Article;
import com.uml.common.po.ArticleTag;
import com.uml.projectapp.dao.ArticleDao;
import com.uml.projectapp.dao.ArticleTagDao;
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

    private final ArticleTagDao articleTagDao;

    ArticleServiceImpl(ArticleDao articleDao,ArticleTagDao articleTagDao){
        this.articleDao = articleDao;
        this.articleTagDao = articleTagDao;
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
    public void insertArticle(Long uid, String title, String content, ArticleType type, ArticleState state) {
        Article article = new Article();
        article.setUid(uid);
        article.setTitle(title);
        article.setContent(content);
        article.setType(type);
        article.setState(state);
        articleDao.insert(article);
        System.out.println(article.getId());
        articleTagDao.insert(new ArticleTag().setArticleId(article.getUid()).setTagName(type));
    }
}
