package com.uml.projectapp.controller;

import com.uml.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuyuda
 * @date 2022-02-21 19:41
 */
@RestController
public class TestController {
    @Autowired
    private TestService testService;

    @GetMapping("/test")
    public String test(){
        return testService.test();
    }
    @GetMapping("/test2")
    public String test2(){
        return "testService.test()";
    }
}
