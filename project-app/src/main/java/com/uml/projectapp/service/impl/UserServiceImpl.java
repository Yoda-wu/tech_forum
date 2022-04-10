package com.uml.projectapp.service.impl;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
            return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(user);
        }
        user = new User();
        user.setOpenId(openid);
        user.setName(name);
        user.setAvatar(avatarUrl);
        user.setGender(Gender.values()[gender]);
        userDao.insert(user);
        return ResultUtil.generateResult(ErrorCode.SUCCESS,user);
    }

    @Override
    public String userUpdate(User user) throws JsonProcessingException {
        // 更新用户信息 -- 直接传入对象
        userDao.updateById(user);
        return ResultUtil.generateResult(ErrorCode.SUCCESS,user);
    }

    @Override
    public int updateById(UpdateWrapper<User> wrapper) {
        // 通过id更新文章，在数据库中更新某个字段
        return userDao.update(null,wrapper);
    }

    @Override
    public String userUpdateNameById(String name, Integer id) throws JsonProcessingException {
        User user = userDao.selectById(id);
        user.setName(name);
        userDao.updateById(user);
        return ResultUtil.generateResult(ErrorCode.SUCCESS,user);
    }

    @Override
    public String userUpdatePhoneById(String phone, Integer id) throws JsonProcessingException {
        User user = userDao.selectById(id);
        user.setPhone(phone);
        userDao.updateById(user);
        return ResultUtil.generateResult(ErrorCode.SUCCESS,user);
    }

    @Override
    public String userUpdateAvatarById(String avatar, Integer id) throws JsonProcessingException {
        User user = userDao.selectById(id);
        user.setAvatar(avatar);
        userDao.updateById(user);
        return ResultUtil.generateResult(ErrorCode.SUCCESS,user);
    }

    @Override
    public String userUpdateGenderById(Integer gender, Integer id) throws JsonProcessingException {
        User user = userDao.selectById(id);
        user.setGender(Gender.values()[gender]);
        userDao.updateById(user);
        return ResultUtil.generateResult(ErrorCode.SUCCESS,user);
    }

    @Override
    public String userUpdateDescById(String desc, Integer id) throws JsonProcessingException {
        User user = userDao.selectById(id);
        user.setDesc(desc);
        userDao.updateById(user);
        return ResultUtil.generateResult(ErrorCode.SUCCESS,user);
    }

    @Override
    public String userUpdateSchoolById(String school, Integer id) throws JsonProcessingException {
        User user = userDao.selectById(id);
        user.setSchool(school);
        userDao.updateById(user);
        return ResultUtil.generateResult(ErrorCode.SUCCESS,user);
    }
}
