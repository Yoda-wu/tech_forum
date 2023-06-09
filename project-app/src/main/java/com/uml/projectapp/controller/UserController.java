package com.uml.projectapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.uml.common.constant.Constant;
import com.uml.common.constant.ErrorCode;
import com.uml.common.po.Event;
import com.uml.common.po.User;
import com.uml.common.utils.ResultUtil;
import com.uml.projectapp.event.EventProducer;
import com.uml.projectapp.service.FollowService;
import com.uml.projectapp.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author wuyuda
 * @date 2022-04-23 12:39
 */
@RestController
@RequestMapping(value = "/user", produces = "application/json;charset=UTF-8")
public class UserController {
    private final UserService userService;
    private final FollowService followService;
    private final EventProducer eventProducer;

    public UserController(UserService userService, FollowService followService, EventProducer eventProducer) {
        this.userService = userService;
        this.eventProducer = eventProducer;
        this.followService = followService;
    }

    @PostMapping("/edit")
    public String updateUserInfo(@RequestBody User user) throws JsonProcessingException {
        if (user == null) {
            return ResultUtil.generateResult(ErrorCode.FAIL, "所传的用户信息为空");
        }
        return userService.userUpdate(user);
    }

    @PostMapping("follow")
    public String followUser(Long uid, Long entityId) throws JsonProcessingException {
        followService.followOperation(uid, Constant.USER, entityId, false);
        eventProducer.fireEvent(
                new Event().setTopic(Constant.FOLLOW)
                        .setUserId(uid)
                        .setEntityType(Constant.USER)
                        .setEntityId(entityId)
                        .setEntityUserId(entityId)
        );
        return ResultUtil.generateResult(ErrorCode.SUCCESS, null);
    }

    @PostMapping("unfollow")
    public String unFollowUser(Long uid, Long entityId) throws JsonProcessingException {
        followService.followOperation(uid, Constant.USER, entityId, true);
        return ResultUtil.generateResult(ErrorCode.SUCCESS, null);
    }

    /**
     * 查询关注列表
     *
     * @param uid    用户id
     * @param offset 实体类型
     * @param limit  实体id
     * @return 关注列表
     * @throws JsonProcessingException json处理异常
     */
    @GetMapping("followeeList")
    public String followeeList(Long uid, Integer offset, Integer limit) throws JsonProcessingException {
        List<Map<String, Object>> followees = followService.findFollowLists(uid, offset, limit, false);
        return ResultUtil.generateResult(ErrorCode.SUCCESS, followees);
    }

    /**
     * 查询粉丝列表
     *
     * @param uid    用户id
     * @param offset 实体类型
     * @param limit  实体id
     * @return 关注列表
     * @throws JsonProcessingException json处理异常
     */
    @GetMapping("followerList")
    public String followerList(Long uid, Integer offset, Integer limit) throws JsonProcessingException {
        List<Map<String, Object>> followees = followService.findFollowLists(uid, offset, limit, true);
        return ResultUtil.generateResult(ErrorCode.SUCCESS, followees);
    }

    /**
     * 获取关注人的数量
     *
     * @param uid 用户id
     * @return 关注人的数量
     * @throws JsonProcessingException json处理异常
     */
    @GetMapping("followeeNumber")
    public String getFolloweeNumber(Long uid) throws JsonProcessingException {
        Long followeeNumber = followService.getFolloweeNumber(uid);
        return ResultUtil.generateResult(ErrorCode.SUCCESS, followeeNumber);
    }

    /**
     * 获取粉丝的数量
     *
     * @param uid 用户id
     * @return 粉丝的数量
     * @throws JsonProcessingException json处理异常
     */
    @GetMapping("followerNumber")
    public String getFollowerNumber(Long uid) throws JsonProcessingException {
        Long followeeNumber = followService.getFollowerNumber(uid);
        return ResultUtil.generateResult(ErrorCode.SUCCESS, followeeNumber);
    }

    @GetMapping("followeeState")
    public String getFolloweeState(Long uid, Long entityId) throws JsonProcessingException {
        return ResultUtil.generateResult(ErrorCode.SUCCESS, followService.getFolloweeState(uid, Constant.USER, entityId));
    }
}
