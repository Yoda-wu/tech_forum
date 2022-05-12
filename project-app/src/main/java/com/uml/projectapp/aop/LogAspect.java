package com.uml.projectapp.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日志打印
 *
 * @author wuyuda
 * @date 2022-05-12 17:17
 */
@Aspect
@Component
public class LogAspect {

    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    /**
     * 第一个*代表方法的返回值
     * com.uml.project-app.service 包名
     * 第一个.*表示包下的所有类
     * 第二个.*表示类的所有方法
     * 括号表示所有参数
     */
    @Pointcut("execution(* com.uml.projectapp.service.*.*(..))")
    public void logPointCut() {
    }

    ;


    @Before("logPointCut()")
    public void before(JoinPoint joinPoint) {
        // 用户[xxx.xxx.xxx.xxx] 在xxxx-xx-xx xx:xx:xx 时访问了 com.uml.project-app.service.xxx 这个功能
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletRequest request = requestAttributes.getRequest();
        // 获取ip
        String ip = request.getRemoteHost();
        // 获取时间
        String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        // 获取类名和方法名
        String target = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        log.info("用户:{} 在 {} 时访问了 {} 这个功能", ip, now, target);
    }

}
