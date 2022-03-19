package com.uml.common.po;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.uml.common.constant.Gender;

import java.sql.Date;

/**
 * 用户实体
 *
 * @author wuyuda
 * @date 2022-03-18 17:29
 */
@TableName(value = "`user`")
public class User extends BaseEntity {

    /**
     * 常量字符串
     */
    public static final String OPEN_ID = "`open_id`";
    public static final String AVATAR = "`avatar`";
    public static final String NAME = "`name`";
    public static final String PHONE = "`phone`";
    public static final String GENDER = "`gender`";
    public static final String DESC = "`desc`";
    public static final String SCHOOL = "`school`";

    @TableField(value = OPEN_ID)
    private String openId;
    @TableField(value = AVATAR)
    private String avatar;
    @TableField(value = NAME)
    private String name;
    @TableField(value = PHONE)
    private String phone;
    @TableField(value = GENDER)
    private Gender gender;
    @TableField(value = DESC)
    private String desc;
    @TableField(value = SCHOOL)
    private String school;

    public User() {
        super();
    }

    public User(Long id, int version, Date createTime, Date updateTime, boolean delete, String openId, String avatar, String name, String phone, Gender gender, String desc, String school) {
        super(id, version, createTime, updateTime, delete);
        this.openId = openId;
        this.avatar = avatar;
        this.name = name;
        this.phone = phone;
        this.gender = gender;
        this.desc = desc;
        this.school = school;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", openId='" + openId + '\'' +
                ", avatar='" + avatar + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", gender='" + gender + '\'' +
                ", desc='" + desc + '\'' +
                ", school='" + school + '\'' +
                ", version=" + version +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", delete=" + delete +
                '}';
    }
}
