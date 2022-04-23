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


    /**
     * 生成成功的消息
     *
     * @return 成功的消息
     * @throws JsonProcessingException json处理异常
     */
    public static String generateSuccessResult() throws JsonProcessingException {
        return generateResult(ErrorCode.SUCCESS, null);
    }

    /**
     * 生成失败的消息
     *
     * @return 失败的消息
     * @throws JsonProcessingException json处理异常
     */
    public static String generateErrorResult() throws JsonProcessingException {
        return generateResult(ErrorCode.FAIL, null);
    }

    /**
     * 生成结果
     *
     * @param error  错误码
     * @param result 返回的数据实体
     * @return 结果
     * @throws JsonProcessingException json处理异常
     */
    public static String generateResult(ErrorCode error, Object result) throws JsonProcessingException {
        Map<String, Object> res = new HashMap<>();
        if (result instanceof Boolean || result instanceof Number ||
                result instanceof Character || result instanceof String) {
            result = new ResultPackage(result);
        }
        res.put(ErrorCode.ERROR_CODE_KEY, error.getCode());
        res.put(ErrorCode.ERROR_MESSAGE_KEY, error.getMessage());
        res.put(RESULT_KEY, result);
        return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(res);

    }

    /**
     * 结果包装
     */
    public static class ResultPackage {
        private final Object data;

        public ResultPackage(Object result) {
            this.data = result;
        }

        public Object getData() {
            return data;
        }
    }

}
