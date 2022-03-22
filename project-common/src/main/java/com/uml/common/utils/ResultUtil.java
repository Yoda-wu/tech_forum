package com.uml.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uml.common.constant.ErrorCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wuyuda
 * @date 2022-03-22 15:39
 */
public class ResultUtil {

    public static final String RESULT_KEY = "result";


    public static String generateSuccessResult() throws JsonProcessingException {
        return generateResult(ErrorCode.SUCCESS,new Object());
    }

    public static String generateResult(ErrorCode error, Object result) throws JsonProcessingException {
        Map<String,Object> res = new HashMap<>();
        res.put(ErrorCode.ERROR_CODE_KEY,error.getCode());
        res.put(ErrorCode.ERROR_MESSAGE_KEY,error.getMessage());
        res.put(RESULT_KEY,result);
        return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(res);

    }


}
