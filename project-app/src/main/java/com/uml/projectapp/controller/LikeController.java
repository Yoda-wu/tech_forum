package com.uml.projectapp.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.uml.common.constant.Constant;
import com.uml.common.constant.ErrorCode;
import com.uml.common.po.Article;
import com.uml.common.po.Event;
import com.uml.common.utils.ResultUtil;
import com.uml.projectapp.event.EventProducer;
import com.uml.projectapp.service.ArticleService;
import com.uml.projectapp.service.CommentService;
import com.uml.projectapp.service.LikeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wuyuda
 * @date 2022-04-02 21:45
 */
@RestController
public class LikeController {
    private final Logger logger = LoggerFactory.getLogger(LikeController.class);
    private final ArticleService articleService;
    private final LikeService likeService;
    private final CommentService commentService;
    private final EventProducer eventProducer;
    public LikeController(ArticleService articleService, LikeService likeService,EventProducer eventProducer,CommentService commentService) {
        this.articleService = articleService;
        this.likeService = likeService;
        this.commentService = commentService;
        this.eventProducer = eventProducer;
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
    @PostMapping(value = "/like", produces = "application/json;charset=UTF-8")
    public String like(String type, Long id, Long uid) throws JsonProcessingException {
        logger.info(type+"    "+ id+"   "+uid);
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
        Long articleId = id;
        if(type.equals(Constant.COMMENT)){
            articleId  = commentService.getArticleId(id);
        }
        // 触发事件
        if(userLikeState == 1){
            eventProducer.fireEvent(new Event()
                    .setUserId(uid)
                    .setTopic(Constant.LIKE)
                    .setEntityType(type)
                    .setEntityId(id)
                    .setEntityUserId( commentService.getCommentUserId(type, id))
                    .setData("articleId", articleId)
            );
        }

        // 返回结果。
        return ResultUtil.generateResult(ErrorCode.SUCCESS, map);
    }

    @GetMapping("/like/getState")
    public String getLikeState(String type, Long id, Long uid) throws JsonProcessingException {
        Map<String,Object> map = new HashMap<>();
        int likeState = likeService.userLikeState(type, id, uid);
        map.put("likeState",likeState);
        map.put("commentId",id);
        map.put("uid",uid);
        return ResultUtil.generateResult(ErrorCode.SUCCESS,map );
    }
}
