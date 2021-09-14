package com.cx.bank.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName RememberPasswordInteceptor
 * @Description 记住密码功能
 * @Author Bruce Xu
 * @Date 2021/9/9 20:59
 * @Version 1.0
 */
public class RememberPasswordInterceptor implements HandlerInterceptor {
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (request.getParameter("remember") != null) {
            Cookie cookie1 = new Cookie("username", username);
            Cookie cookie2 = new Cookie("password", password);
            cookie1.setMaxAge(2000);
            cookie2.setMaxAge(2000);
            response.addCookie(cookie1);
            response.addCookie(cookie2);
        }
    }
}
