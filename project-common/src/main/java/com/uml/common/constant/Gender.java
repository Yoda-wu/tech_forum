package com.uml.common.constant;

/**
 * @author wuyuda
 * @date 2022-03-18 21:37
 */
public enum Gender {

    // 女性
    FEMALE(0),
    // 男性
    MALE(1);


    public final int value;

    Gender(int value){
        this.value = value;

    }

    @Override
    public String toString() {
        return "Gender{" +
                "value=" + value + '\'' +
                '}';
    }

    public int getValue() {
        return value;
    }


}
