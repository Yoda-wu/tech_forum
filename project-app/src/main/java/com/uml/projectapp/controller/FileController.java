package com.uml.projectapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.uml.common.constant.ErrorCode;
import com.uml.common.utils.ResultUtil;
import com.uml.projectapp.service.AliyunOssService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author wuyuda
 * @date 2022-04-19 16:57
 */
@RestController
@RequestMapping(value = "/file", produces = "application/json;charset=UTF-8")
public class FileController {
    private final AliyunOssService aliyunOssService;
    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    public FileController(AliyunOssService aliyunOssService) {
        this.aliyunOssService = aliyunOssService;
    }

    @PostMapping("/upload/test")
    public String test( MultipartFile picture) throws JsonProcessingException {
        return ResultUtil.generateResult(ErrorCode.SUCCESS, "testUrl");
    }
    @PostMapping("/upload/picture")
    public String uploadFirstPicture(@RequestBody MultipartFile picture) throws JsonProcessingException {
        return aliyunOssService.uploadImage(picture);
    }

    @PostMapping("/delete/picture")
    public String deletePicture(@RequestBody  String url) throws JsonProcessingException {
        logger.info("[FileController]: "+url);
        return aliyunOssService.deleteImage(url);
    }
}
