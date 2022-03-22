package com.uml.projectapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.uml.projectapp.dao.UserDao;
import com.uml.projectapp.service.ArticleService;
import com.uml.projectapp.service.ArticleTagService;
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
    ArticleTagService articleTagService;

    @Test
    void contextLoads() throws JsonProcessingException {
        gender();

    }
    public void gender( ) throws JsonProcessingException {


    }

}
