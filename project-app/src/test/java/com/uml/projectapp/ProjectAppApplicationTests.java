package com.uml.projectapp;

import com.uml.common.constant.ArticleState;
import com.uml.common.constant.ArticleType;
import com.uml.common.po.User;
import com.uml.projectapp.dao.UserDao;
import com.uml.projectapp.service.ArticleService;
import com.uml.projectapp.service.ArticleTagService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        articleService.insertArticle(
                1354971607569526785L,
                "数据库系统",
                "数据库是非常重要的一个软件系统",
                ArticleType.CODING,
                ArticleState.EDITING
        );
    }

}
