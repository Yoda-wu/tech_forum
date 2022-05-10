package com.uml.projectapp.service.impl;

import com.uml.common.constant.QuestionType;
import com.uml.common.po.Answer;
import com.uml.common.po.Question;
import com.uml.common.vo.QuestionListVo;
import com.uml.common.vo.QuestionVo;
import com.uml.projectapp.dao.AnswerDao;
import com.uml.projectapp.dao.QuestionDao;
import com.uml.projectapp.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author wuyuda
 * @date 2022-05-10 16:01
 */

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao questionDao;
    private final AnswerDao answerDao;
    public QuestionServiceImpl(QuestionDao questionDao,AnswerDao answerDao) {
        this.questionDao = questionDao;
        this.answerDao = answerDao;
    }

    @Override
    public QuestionListVo getQuestionByType(String type, String domain, Integer current, Integer size) {
        if(type.equals( String.valueOf(QuestionType.SINGLE_CHOICE))){
            return getSingleChoiceQuestion(domain,current,size);
        } else {
            return getSimpleAnswerQuestion(domain, current, size);
        }
    }

    /**
     * 通过习题领域来显示简答题目和答案
     *
     * @param domain  习题领域
     * @param current 当前页
     * @param size    页面大小
     * @return 题目
     */
    public QuestionListVo getSimpleAnswerQuestion(String domain,Integer current,Integer size){
        QuestionListVo questionListVo = new QuestionListVo();
        questionListVo.setSize(size);
        questionListVo.setCurrent(current);
        List<QuestionVo> questionVos = questionDao.listSaqByDomain(domain, current, size);
        questionListVo.setTotal((long)questionVos.size());
        questionListVo.setQuestionVos(questionVos);
        return  questionListVo;
    }

    public QuestionListVo getSingleChoiceQuestion(String domain,Integer current, Integer size){
        QuestionListVo questionListVo = new QuestionListVo();
        questionListVo.setCurrent(current);
        questionListVo.setSize(size);
        List<Question> questions = questionDao.listQuestionByType(domain, String.valueOf(QuestionType.SINGLE_CHOICE), current, size);
        List<QuestionVo> voList = new ArrayList<>();
        for(Question question: questions){
            QuestionVo questionVo = new QuestionVo();
            List<String> answerList = questionDao.getAnswerList(question.getId());
            Answer answer = answerDao.selectById(question.getAnswerId());
            questionVo.setTitle(question.getTitle());
            questionVo.setAnswerList(answerList);
            questionVo.setAnswer(answer.getContent());
            voList.add(questionVo);
        }
        questionListVo.setQuestionVos(voList);
        questionListVo.setTotal((long) voList.size());
        return questionListVo;
    }

}
