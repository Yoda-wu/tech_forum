package com.uml.projectapp.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.uml.common.po.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;


/**
 * @author wuyuda
 * @date 2022-03-18 19:32
 */
@Component
public interface UserDao extends BaseMapper<User> {


    /**
     * 查询用户是否存在
     *
     * @param openId 微信号唯一标识符
     * @return 是否存在
     */
    @Select("SELECT EXISTS(SELECT 1 FROM `user` WHERE `open_id` = #{openId} ")
    boolean checkUserExists(@Param("openId") String openId);

    /**
     * 通过openId来查询用户
     *
     * @param openId 微信号唯一标识符
     * @return 用户实体
     */
    @Select("SELECT * FROM `user` WHERE `open_id` = #{openId}")
    User selectByOpenId(@Param("openId") String openId);
}
