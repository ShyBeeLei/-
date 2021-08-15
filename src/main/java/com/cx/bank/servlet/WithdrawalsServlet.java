package com.cx.bank.servlet;

import com.cx.bank.manager.ManagerImpl;
import com.cx.bank.model.UserBean;
import com.cx.bank.util.AccountOverDrawnException;
import com.cx.bank.util.InvalidDepositException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @ClassName WithdrawalsServlet
 * @Description 处理取款请求
 * @Author Bruce Xu
 * @Date 2021/8/8 22:19
 * @Version 1.0
 */
@WebServlet(urlPatterns = "/WithdrawalsServlet", name = "取款处理")
public class WithdrawalsServlet extends HttpServlet {
    ManagerImpl manager;

    @Override
    public void init() throws ServletException {
        manager = ManagerImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        double amount = Double.parseDouble(req.getParameter("amount"));
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        UserBean userBean = (UserBean) session.getAttribute("userBean");
        if (password.equals(userBean.getPassword())) {
            try {
                manager.withdrawals(amount);
                resp.getWriter().write("取款成功！");
            } catch (InvalidDepositException | AccountOverDrawnException e) {
                resp.getWriter().write(e.getMessage());
            }
        } else {
            resp.getWriter().write("密码错误！请重试。");
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
