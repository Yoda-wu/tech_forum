package com.uml.projectapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.uml.common.constant.ErrorCode;
import com.uml.common.utils.ResultUtil;
import com.uml.projectapp.service.AliyunOssService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author wuyuda
 * @date 2022-04-19 16:57
 */
@RestController
@RequestMapping("file")
public class FileController {
    private final AliyunOssService aliyunOssService;
    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    public FileController(AliyunOssService aliyunOssService) {
        this.aliyunOssService = aliyunOssService;
    }

    @PostMapping("/upload/picture")
    public String uploadFirstPicture(MultipartFile picture) throws JsonProcessingException {
        return ResultUtil.generateResult(ErrorCode.SUCCESS, aliyunOssService.uploadImage(picture));
    }

    @PostMapping("/delete/picture")
    public String deletePicture(String url) throws JsonProcessingException {
        return aliyunOssService.deleteImage(url);
    }
}
