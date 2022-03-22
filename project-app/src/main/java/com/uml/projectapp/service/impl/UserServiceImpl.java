package com.uml.projectapp.service.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uml.common.constant.ErrorCode;
import com.uml.common.constant.Gender;
import com.uml.common.po.User;
import com.uml.common.utils.ResultUtil;
import com.uml.projectapp.dao.UserDao;
import com.uml.projectapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wuyuda
 * @date 2022-03-18 20:06
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    public UserServiceImpl(UserDao userDao){
        this.userDao = userDao;
    }
    @Override
    public void userTestService() {
        List<User> users = userDao.selectList(null);
        if(users != null){
            users.forEach(
                    System.out::println
            );
        }
        else {
            logger.info("Error");
        }
    }


    @Override
    public String userLogin(String openid, String name, String avatarUrl,Integer gender) throws JsonProcessingException {
        logger.info(openid);
        User user = userDao.selectByOpenId(openid);
        if(user != null){
            new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(user);
        }
        user = new User();
        user.setOpenId(openid);
        user.setName(name);
        user.setAvatar(avatarUrl);
        user.setGender(Gender.values()[gender]);
        userDao.insert(user);
        return ResultUtil.generateResult(ErrorCode.SUCCESS,user);
    }
}
