package com.uml.projectapp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.uml.common.po.User;
import org.springframework.stereotype.Component;


/**
 * @author wuyuda
 * @date 2022-03-18 19:32
 */
@Component
public interface UserDao extends BaseMapper<User> {


}
