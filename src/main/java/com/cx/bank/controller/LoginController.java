
package com.cx.bank.controller;

import com.cx.bank.manager.ManagerImpl;
import com.cx.bank.model.UserBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @ClassName LoginController
 * @Description 控制登陆动作
 * @Author Bruce Xu
 * @Date 2021/8/18 20:02
 * @Version 1.0
 */
@Controller
public class LoginController {
    ManagerImpl manager = ManagerImpl.getInstance();

    @RequestMapping(value = "/MainMenu", method = RequestMethod.GET)
    public String toInquiry() {
        return "MainMenu";
    }

    @RequestMapping(value = "/MainMenu", method = RequestMethod.POST)
    public ModelAndView login(UserBean user, String loginAsAdmin, String remember, HttpSession session, HttpServletResponse resp) {
        ModelAndView mov = new ModelAndView();
        String errorInfo = null;
        String username = user.getUsername();
        String password = user.getPassword();
        System.out.println(username + "," + password);
        boolean flag = loginAsAdmin != null;
        session.setAttribute("userBean", user);
        if (remember != null) {
            Cookie cookie1 = new Cookie("username", username);
            Cookie cookie2 = new Cookie("password", password);
            cookie1.setMaxAge(2000);
            cookie2.setMaxAge(2000);
            resp.addCookie(cookie1);
            resp.addCookie(cookie2);
        }
        String identity = manager.login(username, password, flag);
        System.out.println(identity);
        if (identity != null) {
            if ("FROZEN".equals(identity)) {
                errorInfo = "账号被冻结！详情请咨询本站管理员。";
                mov.setViewName("redirect:/");
            } else if ("ADMIN".equals(identity)) {
                mov.setViewName("AdminMenu");
            } else if ("USER".equals(identity) && flag) {
                errorInfo = "您不是管理员！";
                mov.setViewName("redirect:/");
            } else {
                mov.setViewName("MainMenu");
            }
        } else {
            errorInfo = "账号密码错误！";
            mov.setViewName("redirect:/");
        }
        mov.addObject("errorInfo", errorInfo);
        return mov;
    }

    @RequestMapping(value = "/MainMenu", method = RequestMethod.PUT)
    public ModelAndView register(UserBean user, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        String username = user.getUsername();
        String password = user.getPassword();
        session.setAttribute("userBean", user);
        if (manager.register(username, password)) {
            modelAndView.setViewName("MainMenu");
        } else {
            modelAndView.addObject("errorInfo", "用户名重复！");
            modelAndView.setViewName("redirect:/register");
        }
        return modelAndView;
    }

    @RequestMapping("/verifyUsername")
    public void verifyUsername(String username, HttpServletResponse response) throws IOException {
        if (manager.verifyUserName(username)) {
            //用户名重复
            response.getWriter().write("true");
        } else {
            //无重复
            response.getWriter().write("false");
        }
    }

}
