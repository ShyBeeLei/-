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
 * @ClassName TransferServlet
 * @Description 处理转账请求
 * @Author Bruce Xu
 * @Date 2021/8/8 22:37
 * @Version 1.0
 */
@WebServlet(urlPatterns = "/TransferServlet", name = "转账处理")
public class TransferServlet extends HttpServlet {
    String targetUser;
    String password;
    double amount;
    boolean flag = true;
    ManagerImpl manager;

    @Override
    public void init() throws ServletException {
        manager = ManagerImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        targetUser = req.getParameter("targetUser");
        if (manager.verifyUserName(targetUser)) {
            resp.getWriter().write("true");
            flag = true;
        } else {
            resp.getWriter().write("false");
            flag = false;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        targetUser = req.getParameter("targetUser");
        password = req.getParameter("password");
        amount = Double.parseDouble(req.getParameter("amount"));
        HttpSession session = req.getSession();
        UserBean userBean = (UserBean) session.getAttribute("userBean");
        try {
            if (password.equals(userBean.getPassword())) {
                if (flag) {
                    manager.transfer(targetUser, amount);
                    resp.getWriter().write("转账成功");
                } else {
                    resp.getWriter().write("收款人不存在！");
                }
            } else {
                resp.getWriter().write("密码错误！请重试。");
            }
        } catch (InvalidDepositException | AccountOverDrawnException e) {
            resp.getWriter().write(e.getMessage());
        }
    }

    @Override
    public void destroy() {
        manager.exitSystem();
    }
}
