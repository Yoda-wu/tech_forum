package com.uml.projectapp.service.impl;

import com.aliyun.oss.OSS;

import com.aliyun.oss.OSSClientBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.uml.common.utils.ResultUtil;
import com.uml.projectapp.service.AliyunOssService;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author wuyuda
 * @date 2022-04-17 23:53
 */
@Service
public class AliyunOssServiceImpl implements AliyunOssService {
    private OSS client;
    private final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);
    @Value("${aliyun.oss.domain}")
    private String aliyunDomain;
    @Value("${aliyun.oss.endPoint}")
    private String aliyunEndPoint;
    @Value("${aliyun.oss.accessKeyId}")
    private String aliyunAccessKeyId;
    @Value("${aliyun.oss.accessKeySecret}")
    private String aliyunAccessKeySecret;
    @Value("${aliyun.oss.bucketName}")
    private String aliyunBucketName;

    @PostConstruct
    public void init() {
        client = new OSSClientBuilder().build(aliyunEndPoint, aliyunAccessKeyId, aliyunAccessKeySecret);
    }


    @Override
    public String uploadImage(MultipartFile file) {
        String url = "";
        logger.info(file.toString());

        try {
            InputStream inputStream = file.getInputStream();
            String fileName = file.getOriginalFilename();
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            fileName = uuid + fileName;

            client.putObject(aliyunBucketName, fileName, inputStream);
            url = "https://" + aliyunBucketName + "." + aliyunEndPoint + "/" + fileName;
        } catch (IOException e) {
            e.printStackTrace();
        }


        logger.info("[uploadImage]=================" + url);
        return url;
    }

    @Override
    public String deleteImage(String url) throws JsonProcessingException {
        client.deleteObject(aliyunBucketName, url);
        return ResultUtil.generateSuccessResult();
    }
}
