package com.uml.projectapp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.uml.common.po.Comment;
import org.springframework.stereotype.Component;

/**
 * @author wuyuda
 * @date 2022-03-19 16:09
 */
@Component
public interface CommentDao extends BaseMapper<Comment> {
}
