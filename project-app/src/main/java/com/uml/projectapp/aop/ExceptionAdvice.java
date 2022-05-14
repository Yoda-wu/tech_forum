package com.uml.projectapp.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.uml.common.constant.ErrorCode;
import com.uml.common.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 异常统一处理
 * @author wuyuda
 * @date 2022-05-12 16:14
 */
@RestControllerAdvice
public class ExceptionAdvice {

    private final Logger log = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ExceptionHandler({Exception.class})
    public String handleException(HttpServletRequest request, Exception e) throws JsonProcessingException {
        this.logError(request,e);
        return ResultUtil.generateResult(ErrorCode.FAIL,"服务器异常");
    }

    private void logError(HttpServletRequest request, Exception e){
        log.error("path:{}, queryParam:{},服务器发生异常 : {} ", request.getRequestURI(), request.getQueryString(),e.getMessage());
        for (StackTraceElement element : e.getStackTrace()){
            log.error(element.toString());
        }
    }
}
