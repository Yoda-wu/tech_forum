package com.uml.common.constant;

/**
 * @author wuyuda
 * @date 2022-03-19 12:03
 */
public enum ArticleState {

    /**
     * 已发布
     */
    PUBLISHED(1),

    /**
     * 编辑中
     */
    EDITING(2),

    /**
     * 存入草稿
     */
    SAVING(3);

    private final int value;
    ArticleState(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
