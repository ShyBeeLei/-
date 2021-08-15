package com.cx.bank.servlet;

import com.cx.bank.manager.ManagerImpl;
import com.cx.bank.model.UserBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName LoginServlet
 * @Description 处理登录请求
 * @Author Bruce Xu
 * @Date 2021/8/4 21:36
 * @Version 1.0
 */
@WebServlet(urlPatterns = "/LoginServlet", name = "登录处理")
public class LoginServlet extends HttpServlet {
    ManagerImpl manager = ManagerImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String adminUser = req.getParameter("loginAsAdmin");
        //是否作为管理员登陆，是为true。
        boolean flag = adminUser != null;
        UserBean userBean = new UserBean();
        userBean.setUsername(username);
        userBean.setPassword(password);
        userBean.setAdmin(flag);
        HttpSession session = req.getSession();
        PrintWriter out = resp.getWriter();
        session.setAttribute("userBean", userBean);
        if (req.getParameter("remember") != null) {
            Cookie cookie1 = new Cookie("username", username);
            Cookie cookie2 = new Cookie("password", password);
            cookie1.setMaxAge(2000);
            cookie2.setMaxAge(2000);
            resp.addCookie(cookie1);
            resp.addCookie(cookie2);
        }
        String Identity = manager.login(username, password, flag);
        if (Identity != null) {
            if (Identity.equals("FROZEN")) {
                out.println("<script>alert('账号被冻结！详情请咨询本站管理员。'); window.location='http://localhost:8080/banksys_war_exploded/LogIn.jsp';</script>");
            } else if (Identity.equals("ADMIN")) {
                req.getRequestDispatcher("AdminMenu.jsp").forward(req, resp);
            } else if (Identity.equals("USER") && flag) {
                out.println("<script>alert('您不是管理员！'); window.location='http://localhost:8080/banksys_war_exploded/LogIn.jsp';</script>");
            } else {
                req.getRequestDispatcher("MainMenu.jsp").forward(req, resp);
            }
        } else {
            out.println("<script>alert('账号或密码错误！'); window.location='http://localhost:8080/banksys_war_exploded/LogIn.jsp';</script>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
