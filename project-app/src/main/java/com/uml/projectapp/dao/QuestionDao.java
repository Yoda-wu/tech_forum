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
     * 通过习题领域来显示题目
     *
     * @param domain  习题领域
     * @param current 当前页
     * @param size    页面大小
     * @return 题目
     */
    @Select("select title, content as answer " +
            "from question as q, answer as a " +
            "where q.ques_type = SAQ and q.ques_domain = #{domain} and q.id = a.question_id")
    List<QuestionVo> listSaqByDomain(String domain, Integer current, Integer size);
}
