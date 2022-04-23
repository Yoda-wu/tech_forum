package com.uml.projectapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.uml.common.constant.Constant;
import com.uml.common.constant.ErrorCode;
import com.uml.common.po.User;
import com.uml.common.utils.ResultUtil;
import com.uml.projectapp.service.FollowService;
import com.uml.projectapp.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author wuyuda
 * @date 2022-04-23 12:39
 */
@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;
    private final FollowService followService;

    public UserController(UserService userService, FollowService followService) {
        this.userService = userService;
        this.followService = followService;
    }

    @PostMapping("edit")
    public String updateUserInfo(User user) throws JsonProcessingException {
        userService.userUpdate(user);
        return ResultUtil.generateSuccessResult();
    }

    @PostMapping("follow")
    public String followUser(Long uid, Long entityId) throws JsonProcessingException {
        followService.followOperation(uid, Constant.USER, entityId, false);
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
}
