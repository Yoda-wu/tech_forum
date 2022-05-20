package com.uml.projectapp.service;

import com.uml.common.po.Comment;
import com.uml.common.vo.CommentListVo;

/**
 * @author wuyuda
 * @date 2022-03-19 16:11
 */
public interface CommentService {

    /**
     * 添加评论
     *
     * @param comment 评论
     * @return 添加结果
     */
    public int addComment(Comment comment);

    /**
     * 获取该文章的所有一级评论
     *
     * @param parentId 文章id
     * @param current  当前页
     * @param size     页面大小
     * @return CommentListVo
     */
    public CommentListVo listCommentByParentId(Long parentId, Integer current, Integer size);

    /**
     * 获取该文章的所有一级评论
     *
     * @param articleId 文章id
     * @param current   当前页
     * @param size      页面大小
     * @return CommentListVo
     */
    public CommentListVo listCommentByArticleId(Long articleId, Integer current, Integer size);

    /**
     * 删除评论
     *
     * @param comment 评论
     * @return int 删除结果
     */
    public int deleteComment(Comment comment);

    /**
     * 统计该文章的某个评论的子评论数
     *
     * @param parentId 父评论id
     * @return 评论数
     */
    public int getSubCommentNumber(Long parentId);

    /**
     * 统计该文章的评论数
     *
     * @param articleId 文章id
     * @return 评论数
     */
    public int getArticleCommentNumber(Long articleId);

    /**
     * 通过父id获取父评论的对象id
     *
     * @param type 类型
     * @param parentId 父id
     * @return 对象id
     */
    public Long getCommentUserId(String type ,Long parentId);


    /**
     * 通过评论id获取文章id
     * @param id 评论id
     * @return 获取文章id
     */
    public Long getArticleId(Long id);
}
