package com.uml.projectapp.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.uml.common.constant.Constant;
import com.uml.common.constant.ErrorCode;
import com.uml.common.po.Article;
import com.uml.common.utils.ResultUtil;
import com.uml.projectapp.service.ArticleService;
import com.uml.projectapp.service.LikeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wuyuda
 * @date 2022-04-02 21:45
 */
@RestController
public class LikeController {

    private final ArticleService articleService;
    private final LikeService likeService;

    public LikeController(ArticleService articleService, LikeService likeService) {
        this.articleService = articleService;
        this.likeService = likeService;
    }

    /**
     * 点赞的api
     *
     * @param type 点赞对象的类型取值为 {article, comment}
     * @param id   点赞对象的id
     * @param uid  点赞的用户
     * @return 点赞结果
     * @throws JsonProcessingException json处理异常
     */
    @PostMapping("/like")
    @ResponseBody
    public String like(String type, Long id, Long uid) throws JsonProcessingException {
        // 调用service层like方法进行点赞。
        likeService.like(type, id, uid);
        // 从redis中获取点赞数量以及用户点赞状态。
        Long likeNumber = likeService.countLike(type, id);
        int userLikeState = likeService.userLikeState(type, id, uid);
        // 准备一个Map讲上述两个属性加入到Map中。
        HashMap<String, Object> map = new HashMap<>(2);
        map.put(Constant.LIKE_NUM, likeNumber);
        map.put(Constant.LIKE_STATE, userLikeState);
        // 更新数据库中的点赞数。
        articleService.updateById(new UpdateWrapper<Article>().eq("id", id).set("likes", likeNumber));
        // 返回结果。
        return ResultUtil.generateResult(ErrorCode.SUCCESS, map);
    }
}
