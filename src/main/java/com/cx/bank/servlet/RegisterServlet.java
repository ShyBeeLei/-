package com.cx.bank.servlet;

import com.cx.bank.manager.ManagerImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName RegisterServlet
 * @Description 处理注册请求
 * @Author Bruce Xu
 * @Date 2021/8/4 21:39
 * @Version 1.0
 */
@WebServlet(urlPatterns = "/RegisterServlet", name = "注册处理")
public class RegisterServlet extends HttpServlet {
    ManagerImpl manager = ManagerImpl.getInstance();
    String username;
    String password;
    String confirmPassword;
    boolean flag = true;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        username = req.getParameter("username");
        password = req.getParameter("password");
        confirmPassword = req.getParameter("confirmPassword");
        if (manager.verifyUserName(username)) {
            //用户名重复
            resp.getWriter().write("true");
            flag = true;
        } else {
            //无重复
            resp.getWriter().write("false");
            flag = false;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (manager.register(username, password) && !flag) {
            resp.sendRedirect("MainMenu.jsp");
        } else {
            PrintWriter out = resp.getWriter();
            out.println("<script>alert('请输入正确的值！'); window.location='http://localhost:8080/banksys_war_exploded/Register.jsp';</script>");

        }
    }
}
