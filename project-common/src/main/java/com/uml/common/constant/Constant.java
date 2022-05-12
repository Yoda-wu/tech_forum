package com.uml.common.constant;

/**
 * 常量类
 *
 * @author wuyuda
 * @date 2022-03-20 15:18
 */
public class Constant {
    /**
     * 微信appid 用于向code2Session发送数据
     */
    public static final String APP_ID = "appid";
    /**
     * 微信 app secret 用于向code2Session发送数据
     */
    public static final String SECRET = "secret";
    /**
     * 微信的授权类型 用于向code2Session发送数据
     */
    public static final String GRANT_TYPE = "grant_type";
    /**
     * 微信的授权类型值 用于向code2Session发送数据
     */
    public static final String GRANT_TYPE_VALUE = "authorization_code";
    /**
     * 用户登录获取的code 用于向code2Session发送数据
     */
    public static final String JS_CODE = "js_code";


    /**
     * 用户id
     */
    public static final String UID = "uid";
    /**
     * 文章标题
     */
    public static final String TITLE = "title";
    /**
     * 文章类型
     */
    public static final String TYPE = "type";
    /**
     * 文章内容
     */
    public static final String CONTENT = "content";
    /**
     * 文章id
     */
    public static final String ARTICLE_ID = "articleId";

    /**
     * 文章，多用于生成redis的键
     */
    public static final String ARTICLE = "article";
    /**
     * 用户，多用于生成redis的键
     */
    public static final String USER = "user";
    /**
     * 评论，多用于生成redis的键
     */
    public static final String COMMENT = "comment";
    /**
     * 点赞数， 用于向前端返回点赞数
     */
    public static final String LIKE_NUM = "likeNumber";
    /**
     * 点赞状态， 用于向前端返回点赞状态
     */
    public static final String LIKE_STATE = "likeState";
    /**
     * 浏览量，多用于生成redis的键
     */
    public static final String VIEW = "views";
    /**
     * 点赞，多用于生成redis的键
     */
    public static final String LIKE = "likes";

    public static final String ID = "id";

    public static final String TICKET = "ticket";
}
