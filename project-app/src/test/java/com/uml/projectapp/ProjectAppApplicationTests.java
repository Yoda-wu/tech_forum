package com.uml.projectapp;

import com.uml.common.constant.ArticleState;
import com.uml.common.constant.ArticleType;
import com.uml.common.po.User;
import com.uml.common.utils.HttpClientUtil;
import com.uml.projectapp.dao.UserDao;
import com.uml.projectapp.service.ArticleService;
import com.uml.projectapp.service.ArticleTagService;
import org.apache.ibatis.javassist.bytecode.ByteArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.List;

@SpringBootTest
class ProjectAppApplicationTests {
    @Autowired
    UserDao userDao;

    @Autowired
    ArticleService articleService;

    @Autowired
    ArticleTagService articleTagService;

    @Test
    void contextLoads() {


    }

}
