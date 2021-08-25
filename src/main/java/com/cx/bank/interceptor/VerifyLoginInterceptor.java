package com.cx.bank.interceptor;

import com.cx.bank.model.UserBean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @ClassName VerifyLoginInterceptor
 * @Description 登陆验证拦截器
 * @Author Bruce Xu
 * @Date 2021/8/22 20:15
 * @Version 1.0
 */
public class VerifyLoginInterceptor implements HandlerInterceptor {
    static final String LOGIN_URL = "/";
    static final String REGISTER_URL = "/register";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        if (url.lastIndexOf(LOGIN_URL) == url.length() - 1 || url.contains(REGISTER_URL)) {
            return true;
        }
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("userBean");
        if (user != null) {
            return true;
        }
        request.setAttribute("errorInfo", "您还没有登录，请先登录！");
        response.sendRedirect(LOGIN_URL);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
