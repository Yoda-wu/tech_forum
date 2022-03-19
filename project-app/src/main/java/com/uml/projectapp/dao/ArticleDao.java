package com.uml.projectapp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.uml.common.po.Article;
import org.springframework.stereotype.Component;

/**
 * @author wuyuda
 * @date 2022-03-19 14:42
 */
@Component
public interface ArticleDao extends BaseMapper<Article> {
}
