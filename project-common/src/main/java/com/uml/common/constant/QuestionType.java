package com.uml.common.constant;

/**
 * @author wuyuda
 * @date 2022-05-01 15:47
 */
public enum QuestionType {

    /**
     * 简答题
     */
    SAQ(1),
    /**
     * 单选题
     */
    SINGLE_CHOICE(2),
    /**
     * JAVA相关的题
     */
    JAVA(3),
    /**
     * 计算机网络相关的题
     */
    NETWORK(4),
    /**
     * 操作系统相关的题
     */
    OS(5);
    private final int value;

    QuestionType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
