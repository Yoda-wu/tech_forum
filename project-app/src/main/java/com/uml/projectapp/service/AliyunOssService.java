package com.uml.projectapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author wuyuda
 * @date 2022-04-17 23:53
 */
public interface AliyunOssService {

    /**
     * 上传图片
     *
     * @param file 图片文件
     * @return 路径
     */
    public String uploadImage(MultipartFile file);

    /**
     * 删除图片
     *
     * @param url 连接
     * @return 删除成功的消息
     * @throws JsonProcessingException json处理异常
     */
    public String deleteImage(String url) throws JsonProcessingException;
}
