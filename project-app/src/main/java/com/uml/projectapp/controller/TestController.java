package com.uml.projectapp.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.uml.common.constant.ErrorCode;
import com.uml.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wuyuda
 * @date 2022-02-21 19:41
 */
@RestController
@Api

public class TestController {


    @GetMapping("/")
    public String test() throws JsonProcessingException {
        return ResultUtil.generateResult(ErrorCode.SUCCESS,"欢迎访问校园技术论坛api");
    }

    @GetMapping("/test2")
    @ApiOperation("")
    public String test2(){
        return "testService.test()";
    }
}
