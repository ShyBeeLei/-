package com.cx.bank.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @ClassName LoginInterceptor
 * @Description
 * @Author Bruce Xu
 * @Date 2021/9/3 18:47
 * @Version 1.0
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object loginUser = session.getAttribute("user");
        if (loginUser != null) {
            return true;
        }
        request.setAttribute("msg", "请先登录！");
        request.getRequestDispatcher("/").forward(request, response);
        return false;
    }
}
