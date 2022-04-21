package com.uml.projectapp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.uml.common.po.Comment;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wuyuda
 * @date 2022-03-19 16:09
 */
@Component
public interface CommentDao extends BaseMapper<Comment> {
    /**
     * 获取该文章的所有一级评论
     *
     * @param articleId 文章id
     * @param current   当前页
     * @param size      页面大小
     * @return 该文章的所所有id
     */
    @Select("select a.* " +
            "from comment as a " +
            "where article_id = #{articleId} and parent_id = #{articleId} " +
            "limit #{current}, #{size} ")
    public List<Comment> listCommentByArticleId(Long articleId, Integer current, Integer size);

    /**
     * 获取该文章的某个评论的子评论
     *
     * @param parentId 父评论的id
     * @param current  当前页
     * @param size     页面大小
     * @return 子评论
     */
    @Select("select a.* " +
            "from comment as a " +
            "where parent_id = #{articleId} " +
            "limit #{current}, #{size} ")
    public List<Comment> listCommentByParentId(Long parentId, Integer current, Integer size);

    /**
     * 统计该文章的评论数包括子评论
     *
     * @param articleId 文章id
     * @return 评论数
     */
    @Select("select count(id) " +
            "from comment " +
            "where article_id = #{articleId} ")
    public int countArticleCommentNum(Long articleId);

    /**
     * 统计该文章的某个评论的子评论数
     *
     * @param parentId 父评论id
     * @return 评论数
     */
    @Select("select count(id) " +
            "from comment " +
            "where parent_id = #{parentId} ")
    public int countSubCommentNum(Long parentId);


}
