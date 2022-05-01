package com.uml.projectapp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.uml.common.po.Article;
import com.uml.common.po.BaseEntity;
import com.uml.common.po.Comment;
import com.uml.common.utils.SensitiveFilter;
import com.uml.common.vo.CommentListVo;
import com.uml.common.vo.CommentVo;
import com.uml.projectapp.dao.CommentDao;
import com.uml.projectapp.service.ArticleService;
import com.uml.projectapp.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wuyuda
 * @date 2022-04-21 16:18
 */
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentDao commentDao;
    private final ArticleService articleService;
    private final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);
    private final SensitiveFilter sensitiveFilter;

    public CommentServiceImpl(CommentDao commentDao, ArticleService articleService, SensitiveFilter sensitiveFilter) {
        this.commentDao = commentDao;
        this.sensitiveFilter = sensitiveFilter;
        this.articleService = articleService;
    }

    @Override
    public int addComment(Comment comment) {
        if (comment == null || comment.getArticleId() == null) {
            return -1;
        }
        comment.setContent(sensitiveFilter.filter(comment.getContent()));
        // 添加新评论
        int row = commentDao.insert(comment);
        // 获取评论数
        int commentNum = commentDao.countArticleCommentNum(comment.getArticleId());
        // 修改文章的评论数
        articleService.updateById(new UpdateWrapper<Article>()
                .eq("id", comment.getArticleId())
                .set(Article.COMMENT_NUMBER, commentNum)
        );
        // 返回评论数
        return commentNum;
    }

    @Override
    public CommentListVo listCommentByParentId(Long parentId, Integer current, Integer size) {
        List<CommentVo> commentVos = commentDao.listCommentByParentId(parentId, current, size);
        return new CommentListVo(current, size, (long) commentVos.size(), commentVos);
    }

    @Override
    public CommentListVo listCommentByArticleId(Long articleId, Integer current, Integer size) {
        logger.info("[CommentService]: " + size);
        List<CommentVo> commentVos = commentDao.listCommentByArticleId(articleId, current, size);
        return new CommentListVo(current, size, (long) commentVos.size(), commentVos);
    }

    @Override
    public int deleteComment(Comment comment) {
        logger.info(comment.toString());
        commentDao.deleteComment(comment.getId());
        int commentNum = commentDao.countArticleCommentNum(comment.getArticleId());
        articleService.updateById(new UpdateWrapper<Article>().
                eq(BaseEntity.ID, comment.getArticleId()).
                set(Article.COMMENT_NUMBER, commentNum)
        );
        return commentNum;
    }

    @Override
    public int getSubCommentNumber(Long parentId) {
        return commentDao.countSubCommentNum(parentId);
    }

    @Override
    public int getArticleCommentNumber(Long articleId) {
        return commentDao.countArticleCommentNum(articleId);
    }
}
