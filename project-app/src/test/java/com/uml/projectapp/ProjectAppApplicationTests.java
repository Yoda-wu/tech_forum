package com.uml.projectapp;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.uml.common.constant.QuestionType;
import com.uml.common.po.Answer;
import com.uml.common.po.Question;
import com.uml.common.utils.SensitiveFilter;
import com.uml.projectapp.dao.AnswerDao;
import com.uml.projectapp.dao.ArticleDao;
import com.uml.projectapp.dao.QuestionDao;
import com.uml.projectapp.dao.UserDao;
import com.uml.projectapp.service.AliyunOssService;
import com.uml.projectapp.service.ArticleService;
import com.uml.projectapp.service.FollowService;
import com.uml.projectapp.service.UserService;
import com.uml.projectapp.utils.HttpClientUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


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
    AnswerDao answerDao;
    @Autowired
    QuestionDao questionDao;

    @Autowired
    AliyunOssService aliyunOssService;
    @Value("${wechat.miniprogram.appId}")
    public String test;
    @Autowired
    FollowService followService;
    @Autowired
    SensitiveFilter sensitiveFilter;
    @Test
    void contextLoads() throws JsonProcessingException {


    }

    /**
     * 爬取120到java简答题
     */
    public void getQuestionLib(){
        String url ="https://www.nowcoder.com/ta/review-network/review?page=";
        List<String> urls = new ArrayList<>();
        for (int i = 1; i <= 11; i++){
            urls.add(url+i);
        }
        long id = 121;
        for (String link : urls){
            String html = HttpClientUtil.doGet(link, null);
            Document document = Jsoup.parse(html);
            Elements question = document.getElementsByClass("final-question");
            Question questionEntity = new Question();
            questionEntity.setTitle(question.text());
            questionEntity.setId(id);
            questionEntity.setQuesDomain(QuestionType.NETWORK);
            questionEntity.setQuesType(QuestionType.SAQ);
            questionEntity.setAnswer_id(id);
            questionDao.insert(questionEntity);
            Elements answer = document.getElementsByClass("design-answer-box");
            Answer answerEntity = new Answer();
            answerEntity.setContent(answer.text());
            answerEntity.setId(id);
            answerEntity.setQuestionId(id);
            answerDao.insert(answerEntity);
            id++;
        }
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
