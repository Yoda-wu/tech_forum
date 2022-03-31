package com.uml.projectapp.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uml.common.constant.Constant;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wuyuda
 * @date 2022-03-20 14:42
 */
@Component
public class HttpClientUtil {

    public static final String CHARSET = "UTF-8";

    public static final String CODE_2_SESSION_URL = "https://api.weixin.qq.com/sns/jscode2session";


    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);


    public static String appId;


    public static String appSecret;

    @Value("${wechat.miniprogram.appId}")
    public void setAppId(String appId) {
        HttpClientUtil.appId = appId;
    }

    @Value("${wechat.miniprogram.appSecret")
    public void setAppSecret(String appSecret) {
        HttpClientUtil.appSecret = appSecret;
    }

    /**
     * 发起Get请求
     *
     * @param url        请求地址
     * @param jsonString 请求参数
     * @return 返回响应值
     */
    @SuppressWarnings("all")
    public static String doGet(String url, String jsonString) {
        HttpClient httpClient = null;
        HttpGet httpGet = null;
        String result = null;

        httpClient = HttpClients.createDefault();
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            ObjectMapper objectMapper = new ObjectMapper();
            if (jsonString != null) {
                Map map = objectMapper.readValue(jsonString, Map.class);
                map.forEach((key, value) -> {
                    uriBuilder.addParameter((String) key, (String) value);
                });
            }
            URI targetUri = uriBuilder.build();
            httpGet = new HttpGet(targetUri);
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String string = EntityUtils.toString(entity, CHARSET);
                logger.info(string);
                return string;
            }
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }

        return "Error";
    }

    /**
     * 微信登录验证校验接口
     *
     * @param jsonCode 微信登录code
     * @return 返回session_key & open_id
     */
    public static String wxLoginCode2Session(String jsonCode) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put(Constant.JS_CODE, jsonCode);
        requestMap.put(Constant.APP_ID, appId);
        requestMap.put(Constant.SECRET, appSecret);
        requestMap.put(Constant.GRANT_TYPE, Constant.GRANT_TYPE_VALUE);
        String jsonString = mapper.writeValueAsString(requestMap);
        logger.info("=======" + jsonString);
        return doGet(CODE_2_SESSION_URL, jsonString);
    }

    /**
     * 保存图片到本地
     *
     * @param avatarUrl 图片链接
     * @return 服务器地址
     */
    public static String saveAvatar(String avatarUrl) {
        String filename = "";
        // 构造URL
        try {
            URL url = new URL(avatarUrl);
            // 打开连接
            URLConnection con = url.openConnection();
            // 输入流
            InputStream is = con.getInputStream();
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流
            //下载路径及下载图片名称
            filename = "D:\\TestImg/" + 1 + ".jpg";
            File file = new File(filename);
            FileOutputStream os = new FileOutputStream(file, true);
            // 开始读取
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            System.out.println(1);
            // 完毕，关闭所有链接
            os.close();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filename;
    }


}
