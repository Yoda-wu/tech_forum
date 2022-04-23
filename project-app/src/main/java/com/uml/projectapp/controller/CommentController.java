package com.uml.projectapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.uml.common.constant.ErrorCode;
import com.uml.common.po.Comment;
import com.uml.common.utils.ResultUtil;
import com.uml.common.vo.CommentListVo;
import com.uml.projectapp.service.CommentService;
import net.bytebuddy.implementation.bytecode.constant.IntegerConstant;
import org.springframework.web.bind.annotation.*;

/**
 * @author wuyuda
 * @date 2022-04-23 12:32
 */
@RestController
@RequestMapping(value = "comment", produces = "application/json;charset=UTF-8")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * 添加评论
     *
     * @param comment 评论
     * @return 成功添加的消息
     * @throws JsonProcessingException json处理异常
     */
    @PostMapping("add")
    public String addComment(@RequestBody Comment comment) throws JsonProcessingException {
        int commentNumber = commentService.addComment(comment);
        return ResultUtil.generateResult(ErrorCode.SUCCESS, commentNumber);
    }

    @GetMapping("get")
    public String getComment(Long articleId, Integer current, Integer size) throws JsonProcessingException {
        CommentListVo commentListVo = commentService.listCommentByArticleId(articleId, current, size);
        return ResultUtil.generateResult(ErrorCode.SUCCESS, commentListVo);
    }

    @GetMapping("getSub")
    public String getSubComment(Long parentId, Integer current, Integer size) throws JsonProcessingException {
        CommentListVo commentListVo = commentService.listCommentByParentId(parentId, current, size);
        return ResultUtil.generateResult(ErrorCode.SUCCESS, commentListVo);
    }

    @PostMapping("delete")
    public String deleteComment(@RequestBody Comment comment) throws JsonProcessingException {
        int commentNumber = commentService.deleteComment(comment);
        return ResultUtil.generateResult(ErrorCode.SUCCESS, commentNumber);
    }

    @GetMapping("getNum")
    public String getArticleCommentNumber(Long articleId) throws JsonProcessingException {
        int commentNumber = commentService.getArticleCommentNumber(articleId);
        return ResultUtil.generateResult(ErrorCode.SUCCESS, commentNumber);
    }

    @GetMapping("getSubNum")
    public String getSubCommentNumber(Long parentId) throws JsonProcessingException {
        int commentNumber = commentService.getSubCommentNumber(parentId);
        return ResultUtil.generateResult(ErrorCode.SUCCESS, commentNumber);
    }
}
