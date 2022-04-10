package com.uml.projectapp.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.uml.common.po.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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


//    /**通过id修改用户名
//     *
//     * @param Name 用户名
//     * @param Id 用户id
//     */
//    @Update("UPDATE `user` SET `name`=#{Name} WHERE `id`=#{Id}")
//    void updateNameById(@Param("Name") String Name,@Param("Id") int Id);
//
//    /**通过id修改用户头像
//     *
//     * @param Avatar 用户头像
//     * @param Id 用户id
//     */
//    @Update("UPDATE `user` SET `avatar`=#{Avatar} WHERE `id`=#{Id}")
//    void updateAvatarById(@Param("Avatar") String Avatar,@Param("Id") int Id);
//
//    /**通过id修改用户手机
//     *
//     * @param Phone 手机号码
//     * @param Id 用户id
//     */
//    @Update("UPDATE `user` SET `phone`=#{Phone} WHERE `id`=#{Id}")
//    void updatePhoneById(@Param("Phone") String Phone,@Param("Id") int Id);
//
//    /**通过id修改用户性别
//     *
//     * @param Gender 用户性别
//     * @param Id 用户id
//     */
//    @Update("UPDATE `user` SET `gender`=#{Gender} WHERE `id`=#{Id}")
//    void updateGenderById(@Param("Gender") String Gender,@Param("Id") int Id);
//
//    /**通过id修改用户简介
//     *
//     * @param Desc 简介
//     * @param Id 用户id
//     */
//    @Update("UPDATE `user` SET `desc`=#{Desc} WHERE `id`=#{Id}")
//    void updateDescById(@Param("Desc") String Desc,@Param("Id") int Id);
//
//    /**通过id修改用户学院
//     *
//     * @param School 用户学院
//     * @param Id 用户id
//     */
//    @Update("UPDATE `user` SET `school`=#{School} WHERE `id`=#{Id}")
//    void updateSchoolById(@Param("School") String School,@Param("Id") int Id);



}
