package com.uml.projectapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.uml.common.constant.ArticleState;
import com.uml.common.constant.ArticleType;
import com.uml.projectapp.dao.ArticleDao;
import com.uml.projectapp.dao.UserDao;
import com.uml.projectapp.service.ArticleService;
import com.uml.projectapp.utils.HttpClientUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProjectAppApplicationTests {
    @Autowired
    UserDao userDao;

    @Autowired
    ArticleService articleService;

    @Autowired
    ArticleDao articleDao;

    @Value("${wechat.miniprogram.appId}")
    public String test;
    @Test
    void contextLoads() throws JsonProcessingException {
        System.out.println(HttpClientUtil.appSecret);


    }
    public void gender( ) throws JsonProcessingException {

    }

}
