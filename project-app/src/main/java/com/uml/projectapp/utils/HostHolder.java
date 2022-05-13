package com.uml.projectapp.utils;

import com.uml.common.po.User;
import org.springframework.stereotype.Component;

/**
 * 持有用户的信息
 * 用于代替session对象
 * @author wuyuda
 * @date 2022-05-12 20:01
 */
@Component
public class HostHolder {

    private final ThreadLocal<User> users = new ThreadLocal<>();

    public void setUser(User user){
        users.set(user);
    }

    public User getUser(){
        return users.get();
    }

    public void clear(){
        users.remove();
    }

}
