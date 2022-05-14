package com.uml.projectapp.service;

/**
 * @author wuyuda
 * @date 2022-03-19 16:11
 */
public interface LikeService {

    /**
     * 点赞
     *
     * @param type 类型（可以为文章 / 评论 等可以被点赞的类型）
     * @param id   点赞对象的id
     * @param uid  点赞的用户id；
     */
    public void like(String type, Long id, Long uid);

    /**
     * 统计对象的点赞数
     *
     * @param type 类型（可以为文章 / 评论 等可以被点赞的类型）
     * @param id   点赞对象的id
     * @return 点赞数
     */
    public long countLike(String type, Long id);

    /**
     * 统计某个用户对某个可点赞对象的点赞状态
     *
     * @param type 类型（可以为文章 / 评论 等可以被点赞的类型）
     * @param id   点赞对象的id
     * @param uid  点赞的用户id；
     * @return 0 --- 未点赞  1 --- 已点赞
     */
    public int userLikeState(String type, Long id, Long uid);
}
