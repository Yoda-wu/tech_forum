package com.uml.common.constant;

/**
 * @author wuyuda
 * @date 2022-03-19 14:25
 */
public enum ArticleType {
    /**
     * 其它
     */
    OTHERS(0),

    /**
     * 代码技术类型文章
     */
    CODING(3),

    /**
     * 学习经验分享文章
     */
    STUDY_EXPERIENCE_SHARE(4),

    /**
     * 软件技术类型文章
     */
    SOFTWARE(5)
    ;


    private final int value;

    ArticleType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
