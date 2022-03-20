package com.uml.projectapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uml.common.constant.Constant;
import com.uml.common.utils.HttpClientUtil;
import com.uml.projectapp.service.UserService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


/**
 * @author wuyuda
 * @date 2022-03-20 14:22
 */
@RestController()
public class WxController {

    private final Logger logger = LoggerFactory.getLogger(WxController.class);

    private final UserService userService;
    WxController(UserService userService){
        this.userService = userService;
    }


    @PostMapping("/wx/login")
    @SuppressWarnings("all")
    public String login(@RequestBody String jsonString ) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map map = mapper.readValue(jsonString, Map.class);
        logger.info(" =================== "+map);
        Map responseMap = mapper.readValue(HttpClientUtil.wxLoginCode2Session((String) map.get(Constant.JS_CODE)), Map.class);
        logger.info(" =================== "+responseMap.get("openid"));
        return userService.userLogin((String) responseMap.get("openid"), (String) map.get("name"), (String) map.get("avatarUrl"));
    }
}
