package com.uml.projectapp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.uml.common.po.Chat;
import org.springframework.stereotype.Component;

/**
 * @author wuyuda
 * @date 2022-03-19 16:08
 */
@Component
public interface ChatDao extends BaseMapper<Chat> {
}
