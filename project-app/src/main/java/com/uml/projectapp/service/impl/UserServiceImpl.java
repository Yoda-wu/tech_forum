package com.uml.projectapp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uml.common.po.User;
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
            System.out.println("Error");
        }
    }

    @Override
    public String userLogin(String openid, String name, String avatarUrl) throws JsonProcessingException {
        logger.info(openid);
        User user = userDao.selectByOpenId(openid);
        if(user != null){
            new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(user);
        }
        user = new User();
        user.setOpenId(openid);
        user.setName(name);
        user.setAvatar(avatarUrl);
        userDao.insert(user);
        return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(user);
    }
}
