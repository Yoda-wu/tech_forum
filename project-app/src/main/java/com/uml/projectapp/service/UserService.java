package com.uml.projectapp.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.uml.common.po.LoginTicket;
import com.uml.common.po.User;

import java.util.Map;

/**
 * @author wuyuda
 * @date 2022-03-18 19:39
 */

public interface UserService {


    /**
     * 测试mybatis-plus是否连接成功
     */
    public void userTestService();

    /**
     * 登录
     *
     * @param openid    微信唯一标识符
     * @param name      名称
     * @param avatarUrl 图像url
     * @param gender    性别
     * @return 结果实体
     * @throws JsonProcessingException json处理异常。
     */
    public Map<String,Object> userLogin(String openid, String name, String avatarUrl, Integer gender) throws JsonProcessingException;


    /**
     * 更新用户信息
     * @param user 用户
     * @return 结果实体
     * @throws JsonProcessingException
     */
    public String userUpdate(User user)throws JsonProcessingException;


    /**
     * 根据id更新
     * @param wrapper 更新条件
     * @return 更新结果
     */
    public int updateById(UpdateWrapper<User> wrapper);


    /**根据id修改用户名
     *
     * @param name 用户名
     * @param id 用户ID
     * @return 结果实体
     */
    public String userUpdateNameById(String name,Integer id) throws JsonProcessingException;

    /**根据id修改用户名
     *
     * @param phone 用户手机
     * @param id 用户ID
     * @return 结果实体
     */
    public String userUpdatePhoneById(String phone,Integer id) throws JsonProcessingException;

    /**根据id修改用户头像
     *
     * @param avatar 用户头像
     * @param id 用户ID
     * @return 结果实体
     */
    public String userUpdateAvatarById(String avatar,Integer id) throws JsonProcessingException;

    /**根据id修改用户
     *
     * @param gender 用户性别
     * @param id 用户ID
     * @return 结果实体
     */
    public String userUpdateGenderById(Integer gender,Integer id) throws JsonProcessingException;

    /**根据id修改用户名
     *
     * @param desc 用户简介
     * @param id 用户ID
     * @return 结果实体
     */
    public String userUpdateDescById(String desc,Integer id) throws JsonProcessingException;


    /**根据id修改用户学院
     *
     * @param school 用户学院
     * @param id 用户ID
     * @return 结果实体
     */
    public String userUpdateSchoolById(String school,Integer id) throws JsonProcessingException;

    /**
     * 获取登录凭证的对象
     * @param ticket 凭证
     * @return 登陆凭证对象
     */
    public LoginTicket getTicket(String ticket);

    /**
     * 通过id查询用户
     * @param uid 用户id
     * @return 用户
     */
    public User findUserById(Long uid);
}
