package com.uml.projectapp.service;

import com.uml.common.vo.QuestionListVo;

/**
 * @author wuyuda
 * @date 2022-05-01 16:07
 */
public interface QuestionService {

    QuestionListVo getQuestionByType(String type,String domain,Integer current,Integer size);


}
