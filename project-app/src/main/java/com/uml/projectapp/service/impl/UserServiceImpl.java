package com.uml.projectapp.service.impl;

import com.uml.common.po.User;
import com.uml.projectapp.dao.UserDao;
import com.uml.projectapp.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wuyuda
 * @date 2022-03-18 20:06
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

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
}
