package com.uml.projectapp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.uml.common.po.Article;
import com.uml.common.vo.ArticleVo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wuyuda
 * @date 2022-03-19 14:42
 */
@Component
public interface ArticleDao extends BaseMapper<Article> {

    /**
     * 分页获取发布的文章
     *
     * @param current 当前页
     * @param size    大小
     * @return 文章list
     */
    @Select("SELECT a.*,u.name,u.avatar " +
            "FROM `article` a, `user` u " +
            "WHERE a.uid = u.id AND a.delete = false AND a.state = 'PUBLISHED' " +
            "ORDER BY RAND() " +
            "LIMIT #{current}, #{size} "
    )
    List<ArticleVo> listPublishedArticle(Integer current, Integer size);


}
