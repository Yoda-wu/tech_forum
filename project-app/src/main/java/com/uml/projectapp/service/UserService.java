package com.uml.projectapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;

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
     * @param openid 微信唯一标识符
     * @param name 名称
     * @param avatarUrl 图像url
     * @return 结果实体
     */
    public String userLogin(String openid,String name,String avatarUrl) throws JsonProcessingException;
}
