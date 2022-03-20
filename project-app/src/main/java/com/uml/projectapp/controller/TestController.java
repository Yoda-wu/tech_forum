package com.uml.projectapp.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuyuda
 * @date 2022-02-21 19:41
 */
@RestController
@Api

public class TestController {


    @GetMapping("/test2")
    @ApiOperation("")
    public String test2(){
        return "testService.test()";
    }
}
