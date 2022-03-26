package com.uml.projectapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uml.common.constant.ArticleState;
import com.uml.common.constant.Constant;
import com.uml.common.po.Article;
import com.uml.common.utils.ResultUtil;
import com.uml.common.vo.ArticleVo;
import com.uml.projectapp.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author wuyuda
 * @date 2022-03-22 16:12
 */
@RestController("/article")
@Api("文章相关的接口")
public class ArticleController {

    private final ArticleService articleService;
    private final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private final ObjectMapper objectMapper;

    ArticleController(ArticleService articleService, ObjectMapper objectMapper) {
        this.articleService = articleService;
        this.objectMapper = objectMapper;
    }

    /**
     * 保存文章--未发布
     * @param article 文章实体
     * @return 未发布文章信息
     * @throws JsonProcessingException json处理异常
     */
    @PostMapping("/save")
    @ApiOperation("保存文章")
    public String saveArticle(@RequestBody
                                  @ApiParam(value = "Article实体类", required = true)
                                          Article article) throws JsonProcessingException {
      return articleService.saveArticle(article);

    }

    /**
     * 发布文章
     *
     * @param article 文章实体
     * @return 未发布文章信息
     * @throws JsonProcessingException json处理异常
     */
    @PostMapping("/publish")
    @ApiOperation("发布文章")
    public String publishArticle(@RequestBody
                                 @ApiParam(value = "Article实体类", required = true)
                                         Article article) throws JsonProcessingException {
        return articleService.publishArticle(article);
    }

    /**
     * 分页获取所有已发布的文章
     *
     * @param current 当前页
     * @param size    页面大小
     * @return 已发布的文章列表
     * @throws JsonProcessingException json处理异常
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询已发布的文章", notes = "current变量的起始值是1", response = ArticleVo.class)
    public String getPublishArticle(Integer current, Integer size) throws JsonProcessingException {
        return articleService.listPublishedArticle(current, size);
    }
}
