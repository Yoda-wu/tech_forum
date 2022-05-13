package com.uml.projectapp.controller.interceptor;

import com.uml.common.po.LoginTicket;
import com.uml.common.po.User;
import com.uml.projectapp.service.UserService;
import com.uml.projectapp.utils.CookieUtil;
import com.uml.projectapp.utils.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author wuyuda
 * @date 2022-05-12 19:50
 */
public class TicketInterceptor implements HandlerInterceptor {


    private final UserService userService;

    private final HostHolder hostHolder;

    public TicketInterceptor(UserService userService, HostHolder hostHolder) {
        this.userService = userService;
        this.hostHolder = hostHolder;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从cookie中获取凭证
        String ticket = CookieUtil.getValue(request,"ticket");
        if(ticket != null){
            // 查询凭证
            LoginTicket loginTicket = userService.getTicket(ticket);
            // 判断凭证是否有效
            if(loginTicket != null && loginTicket.getState() != 0 && loginTicket.getDate().after(new Date())){
                // 根据凭证查询用户
                User user = userService.findUserById(loginTicket.getUid());
                // 在本次请求中持有用户
                hostHolder.setUser(user);
            }
        }
        return  true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        hostHolder.clear();
    }
}
