package com.cx.bank.servlet;

import com.cx.bank.manager.ManagerImpl;
import com.cx.bank.util.InvalidDepositException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName Deposit
 * @Description 存款请求处理器
 * @Author Bruce Xu
 * @Date 2021/8/8 22:02
 * @Version 1.0
 */
@WebServlet(urlPatterns = "/DepositServlet", name = "存款处理")
public class DepositServlet extends HttpServlet {
    ManagerImpl manager;

    @Override
    public void init() throws ServletException {
        manager = ManagerImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        double amount = Double.parseDouble(req.getParameter("DepositAmount"));
        try {
            manager.deposit(amount);
            resp.getWriter().write("存款成功！");
        } catch (InvalidDepositException e) {
            resp.getWriter().write(e.getMessage());
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
