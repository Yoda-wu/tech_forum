package com.uml.projectapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.uml.common.po.User;
import com.uml.projectapp.controller.LoginController;
import com.uml.projectapp.dao.ArticleDao;
import com.uml.projectapp.dao.UserDao;
import com.uml.projectapp.service.ArticleService;
import com.uml.projectapp.service.UserService;
import com.uml.projectapp.utils.HttpClientUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;


@SpringBootTest
class ProjectAppApplicationTests {
    @Autowired
    UserDao userDao;

    @Autowired
    UserService userService;


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

    @Test
    public void gender() throws JsonProcessingException {
//        Long id = 1354971607569526785L;
//        System.out.println(id);
//        System.out.println(userDao == null);
    }

    @Test
    public void testmodify() throws JsonProcessingException{
        userService.userUpdateNameById("M",2);
    }
}
