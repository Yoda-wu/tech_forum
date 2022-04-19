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
        aliyunOssService.deleteImage("https://scut-bbs-images.oss-cn-shenzhen.aliyuncs.com/d90f4e9589e4415980a701a52223e8f5image-20200406184656917.png");
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
