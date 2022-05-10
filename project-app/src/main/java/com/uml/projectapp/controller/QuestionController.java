package com.uml.projectapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.uml.common.constant.ErrorCode;
import com.uml.common.utils.ResultUtil;
import com.uml.projectapp.service.QuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuyuda
 * @date 2022-05-10 16:40
 */
@RestController
@RequestMapping(value = "/question", produces = "application/json;charset=UTF-8")
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("get")
    public String getQuestion(String type, String domain, Integer current, Integer size) throws JsonProcessingException {
        return ResultUtil.generateResult(ErrorCode.SUCCESS,questionService.getQuestionByType(type,domain,current,size));
    }
}
