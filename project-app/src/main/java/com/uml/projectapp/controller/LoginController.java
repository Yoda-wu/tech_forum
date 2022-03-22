package com.uml.projectapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uml.common.constant.Constant;
import com.uml.common.po.User;
import com.uml.common.utils.HttpClientUtil;
import com.uml.projectapp.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * @author wuyuda
 * @date 2022-03-20 14:22
 */
@RestController()
@Api("登录相关的接口")
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private final UserService userService;

    private final ObjectMapper objectMapper;

    LoginController(UserService userService, ObjectMapper objectMapper) {
        this.userService = userService;
        this.objectMapper = objectMapper;
    }


    @PostMapping("/wx/login")
    @SuppressWarnings("all")
    @ApiOperation(value = "小程序登录操作", notes = "登陆时需要传递微信登录code以及昵称和头像", response = User.class)
    public String login(@RequestBody @ApiParam(value = "是一个Json字符串", required = true) String jsonString) throws JsonProcessingException {
        Map<?,?> map = objectMapper.readValue(jsonString, Map.class);
        logger.info(" =================== " + map);
        Map<?,?> responseMap = objectMapper.readValue(HttpClientUtil.wxLoginCode2Session((String) map.get(Constant.JS_CODE)), Map.class);
        if (responseMap.get("openid") == null) {
            return "Error";
        }
        logger.info(" =================== " + responseMap.get("openid"));
        return userService.userLogin((String) responseMap.get("openid"), (String) map.get("name"), (String) map.get("avatarUrl"), (Integer) map.get("gender"));
    }
}
