package com.uml.projectapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.uml.projectapp.dao.ArticleDao;
import com.uml.projectapp.dao.UserDao;
import com.uml.projectapp.service.AliyunOssService;
import com.uml.projectapp.service.ArticleService;
import com.uml.projectapp.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;




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

    @Autowired
    AliyunOssService aliyunOssService;
    @Value("${wechat.miniprogram.appId}")
    public String test;

    @Test
    void contextLoads() throws JsonProcessingException {
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
