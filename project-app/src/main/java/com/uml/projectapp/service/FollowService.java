package com.uml.projectapp.service;

import java.util.List;
import java.util.Map;

/**
 * @author wuyuda
 * @date 2022-03-19 16:11
 */
public interface FollowService {

    /**
     * 关注
     *
     * @param uid        用户id
     * @param entityType 实体类型
     * @param entityId   实体id
     * @param remove     是否取消关注
     * @return 关注成功的消息
     */
    public String followOperation(Long uid, String entityType, Long entityId, Boolean remove);

    /**
     * 查看该对象是不是关注的人
     *
     * @param uid 用户id
     * @param entityType 关注的类型
     * @param entityId 关注实体的id
     * @return 是不是关注
     */
    public boolean getFolloweeState(Long uid, String entityType, Long entityId);


    /**
     * 查询用户关注的人或者查询用户的粉丝
     *
     * @param uid      用户id
     * @param offset   实体类型
     * @param limit    实体id
     * @param findFans 区别查询关注列表或者粉丝
     * @return 关注列表
     */
    public List<Map<String, Object>> findFollowLists(Long uid, Integer offset, Integer limit, Boolean findFans);

    /**
     * 获取关注人的数量
     *
     * @param uid 用户id
     * @return 关注人数量
     */
    public Long getFolloweeNumber(Long uid);

    /**
     * 获取粉丝数
     *
     * @param uid 用户id
     * @return 关注人数量
     */
    public Long getFollowerNumber(Long uid);
}
