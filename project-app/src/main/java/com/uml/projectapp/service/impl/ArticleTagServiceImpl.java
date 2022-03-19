package com.uml.projectapp.service.impl;

import com.uml.common.constant.ArticleType;
import com.uml.common.po.Article;
import com.uml.common.po.ArticleTag;
import com.uml.projectapp.dao.ArticleDao;
import com.uml.projectapp.dao.ArticleTagDao;
import com.uml.projectapp.service.ArticleTagService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wuyuda
 * @date 2022-03-19 15:03
 */
@Service
public class ArticleTagServiceImpl implements ArticleTagService {



    private final ArticleTagDao articleTagDao;

    ArticleTagServiceImpl(ArticleTagDao articleTagDao) {
        this.articleTagDao = articleTagDao;
    }


    @Override
    public void articleTagTestService() {
        List<ArticleTag> articleTags = articleTagDao.selectList(null);
        if(articleTags != null){
            articleTags.forEach(System.out::println);
        }else{
            System.out.println("Error");
        }
    }

    @Override
    public void insertTag(Long articleId, ArticleType type) {
        ArticleTag tag = new ArticleTag();
        tag.setTagName(type);
        tag.setArticleId(articleId);
        articleTagDao.insert(tag);

    }
}
