package com.uml.projectapp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.uml.common.po.Question;
import com.uml.common.vo.QuestionVo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wuyuda
 * @date 2022-05-01 15:21
 */
@Component
public interface QuestionDao extends BaseMapper<Question> {

    /**
     * 通过习题领域来显示简答题目和答案
     *
     * @param domain  习题领域
     * @param current 当前页
     * @param size    页面大小
     * @return 题目
     */
    @Select("select title, content as answer " +
            "from question as q, answer as a " +
            "where q.ques_type = 'SAQ' and q.ques_domain = #{domain} and q.id = a.question_id " +
            "order by rand() " +
            "LIMIT #{current}, #{size} ")
    List<QuestionVo> listSaqByDomain(String domain, Integer current, Integer size);

    /**
     * 根据问题的id 获取答案列表
     *
     * @param questionId 问题的id
     * @return 答案列表
     */
    @Select("select content " +
            "from answer " +
            "where question_id = #{questionId} ")
    List<String> getAnswerList(Long questionId);


    /**
     * 通过领域和类型来获取题目信息
     *
     * @param domain  领域
     * @param type    类型
     * @param current 当前页
     * @param size    大小
     * @return 题目信息列表
     */
    @Select("select * " +
            "from question " +
            "where ques_type = #{type} and ques_domain = #{domain} " +
            "order by rand() " +
            "LIMIT #{current}, #{size} ")
    List<Question> listQuestionByType(String domain, String type, Integer current, Integer size);



}
