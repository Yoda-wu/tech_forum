package com.uml.projectapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.uml.common.constant.ArticleState;
import com.uml.common.constant.ArticleType;
import com.uml.projectapp.dao.ArticleDao;
import com.uml.projectapp.dao.UserDao;
import com.uml.projectapp.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProjectAppApplicationTests {
    @Autowired
    UserDao userDao;

    @Autowired
    ArticleService articleService;

    @Autowired
    ArticleDao articleDao;

    @Test
    void contextLoads() throws JsonProcessingException {
        for (int i = 1; i <= 2; i++) {
            System.out.println(articleService.listPublishedArticle(i, 2));

        }

    }
    public void gender( ) throws JsonProcessingException {

    }

}
