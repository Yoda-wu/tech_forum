package com.uml.projectapp.service.impl;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.uml.common.constant.Constant;
import com.uml.common.constant.ErrorCode;
import com.uml.common.constant.Gender;
import com.uml.common.po.LoginTicket;
import com.uml.common.po.User;
import com.uml.common.utils.RedisKeyUtil;
import com.uml.common.utils.ResultUtil;
import com.uml.projectapp.dao.UserDao;
import com.uml.projectapp.service.UserService;
import com.uml.projectapp.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author wuyuda
 * @date 2022-03-18 20:06
 */
@Service
public class UserServiceImpl implements UserService {


    private final UserDao userDao;
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void userTestService() {
        List<User> users = userDao.selectList(null);
        if (users != null) {
            users.forEach(
                    System.out::println
            );
        } else {
            logger.info("Error");
        }
    }


    @Override
    public Map<String, Object> userLogin(String openid, String name, String avatarUrl, Integer gender) throws JsonProcessingException {
        logger.info(openid);
        Map<String,Object> map = new HashMap<>();
        User user = userDao.selectByOpenId(openid);
        if (user == null) {
            user = new User();
            user.setOpenId(openid);
            user.setName(name);
            user.setAvatar(avatarUrl);
            user.setGender(Gender.values()[gender]);
            userDao.insert(user);
            this.initCache(user.getId());
        }
        // 创建登录凭证类
        // TODO 登录凭证创建（整合SpringSecurity）
        LoginTicket ticket = new LoginTicket();
        ticket.setUid(user.getId());
        ticket.setTicket(String.valueOf(UUID.randomUUID()));
        ticket.setDate(new Date(System.currentTimeMillis() + 60 * 10 * 1000));
        String redisKey = RedisKeyUtil.getTicketKey(ticket.getTicket());
        RedisUtil.set(redisKey,ticket);
        map.put(Constant.USER,user);
        map.put(Constant.TICKET,ticket.getTicket());
        return  map;
    }


    @Override
    public String userUpdate(User user) throws JsonProcessingException {
        logger.info(user.toString());
        Long id = user.getId();
        if(id == null){
            return ResultUtil.generateResult(ErrorCode.FAIL,"所传的用户id为空");
        }
        User old = userDao.selectById(id);
        // 更新用户信息 -- 直接传入对象
        updateById(new UpdateWrapper<User>().eq("id",id)
                .set(User.NAME,(user.getName() == null || user.getName().length() == 0)?old.getName():user.getName())
                .set(User.AVATAR,(user.getAvatar() == null || user.getAvatar().length() == 0 ? old.getAvatar():user.getAvatar()))
                .set(User.PHONE,(user.getPhone() == null || user.getPhone().length() == 0)?old.getPhone():user.getPhone())
                .set(User.DESC,(user.getDesc() == null  ? old.getDesc() : user.getDesc()))
                .set(User.GENDER,(user.getGender() == null ? old.getGender() :user.getGender()))
                .set(User.SCHOOL,(user.getSchool() == null || user.getSchool().length() == 0 ? old.getSchool():user.getSchool()))
        );

        clearCache(id);
        return ResultUtil.generateResult(ErrorCode.SUCCESS, user);
    }

    @Override
    public int updateById(UpdateWrapper<User> wrapper) {
        // 通过id更新文章，在数据库中更新某个字段
        return userDao.update(null, wrapper);
    }

    @Override
    public String userUpdateNameById(String name, Long id) throws JsonProcessingException {
        User user = userDao.selectById(id);
        user.setName(name);
        userDao.updateById(user);
        return ResultUtil.generateResult(ErrorCode.SUCCESS, user);
    }

    @Override
    public String userUpdatePhoneById(String phone, Long id) throws JsonProcessingException {
        User user = userDao.selectById(id);
        user.setPhone(phone);
        userDao.updateById(user);
        return ResultUtil.generateResult(ErrorCode.SUCCESS, user);
    }

    @Override
    public String userUpdateAvatarById(String avatar, Long id) throws JsonProcessingException {
        User user = userDao.selectById(id);
        user.setAvatar(avatar);
        userDao.updateById(user);
        return ResultUtil.generateResult(ErrorCode.SUCCESS, user);
    }

    @Override
    public String userUpdateGenderById(Integer gender, Long id) throws JsonProcessingException {
        User user = userDao.selectById(id);
        user.setGender(Gender.values()[gender]);
        userDao.updateById(user);
        return ResultUtil.generateResult(ErrorCode.SUCCESS, user);
    }

    @Override
    public String userUpdateDescById(String desc, Long id) throws JsonProcessingException {
        User user = userDao.selectById(id);
        user.setDesc(desc);
        userDao.updateById(user);
        return ResultUtil.generateResult(ErrorCode.SUCCESS, user);
    }

    @Override
    public String userUpdateSchoolById(String school, Long id) throws JsonProcessingException {
        User user = userDao.selectById(id);
        user.setSchool(school);
        userDao.updateById(user);

        return ResultUtil.generateResult(ErrorCode.SUCCESS, user);
    }

    @Override
    public LoginTicket getTicket(String ticket) {
        // TODO 获取登录凭证（整合SpringSecurity）
        String redisKey = RedisKeyUtil.getTicketKey(ticket);

        return (LoginTicket) RedisUtil.get(redisKey);
    }

    @Override
    public User findUserById(Long uid) {
        // TODO 获取登录凭证（整合SpringSecurity）
        User user = getCache(uid);
        if(user == null){
            user = initCache(uid);
        }
        return user;
    }

    /**
     * 从缓存中获取数据
     * @param id 用户id
     * @return 用户信息
     */
    public User getCache(Long id){
        String userKey = RedisKeyUtil.getUserKey(id);
        return (User) RedisUtil.get(userKey);
    }

    /**
     * 初始化缓存
     * @param id 用户id
     * @return 用户信息
     */
    public User initCache(Long id){
        User user = userDao.selectById(id);
        String userKey = RedisKeyUtil.getUserKey(id);
        RedisUtil.set(userKey,user,3600L);
        return  user;
    }

    /**
     * 清空缓存
     * @param id 用户id
     */
    public void clearCache(Long id){
        String userKey = RedisKeyUtil.getUserKey(id);
        RedisUtil.del(userKey);

    }
}
