package com.cx.bank.servlet;

import com.cx.bank.manager.ManagerImpl;
import com.cx.bank.model.UserBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @ClassName InquiryServlet
 * @Description 处理查询余额请求
 * @Author Bruce Xu
 * @Date 2021/8/8 17:17
 * @Version 1.0
 */
@WebServlet(urlPatterns = "/InquiryServlet", name = "查询处理")
public class InquiryServlet extends HttpServlet {
    ManagerImpl manager;

    @Override
    public void init() throws ServletException {
        manager = ManagerImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserBean userBean = (UserBean) session.getAttribute("userBean");
        if (req.getParameter("inquiryPassword").equals(userBean.getPassword())) {
            double balance = manager.inquiry(userBean.getPassword());
            resp.getWriter().write("您的余额为：" + balance);
        } else {
            resp.getWriter().write("密码错误！请重试");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    public void destroy() {
        manager.exitSystem();
    }
}
