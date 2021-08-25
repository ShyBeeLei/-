package com.cx.bank.controller;

import com.cx.bank.manager.ManagerImpl;
import com.cx.bank.model.UserBean;
import com.cx.bank.util.AccountOverDrawnException;
import com.cx.bank.util.InvalidDepositException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName ManagerController
 * @Description 业务逻辑控制层
 * @Author Bruce Xu
 * @Date 2021/8/22 14:41
 * @Version 1.0
 */
@Controller
public class ManagerController {
    ManagerImpl manager = ManagerImpl.getInstance();

    @RequestMapping(value = "/inquiry", method = RequestMethod.GET)
    public void inquiry(String password, HttpSession session, HttpServletResponse response) throws IOException {
        UserBean user = (UserBean) session.getAttribute("userBean");
        System.out.println(user.getPassword() + "," + password);
        if (user.getPassword().equals(password)) {
            double balance = manager.inquiry(password);
            response.getWriter().write("您的余额为：" + balance);
        } else {
            response.getWriter().write("密码错误！请重试!");
        }
    }

    @RequestMapping(value = "/deposit", method = RequestMethod.POST)
    public void deposit(double amount, HttpServletResponse response) throws IOException {
        try {
            manager.deposit(amount);
            response.getWriter().write("存款成功！");
        } catch (InvalidDepositException e) {
            response.getWriter().write(e.getMessage());
        }
    }

    @RequestMapping(value = "/withdrawals", method = RequestMethod.POST)
    public void withdrawals(double amount, String password, HttpServletResponse response, HttpSession session) throws IOException {
        UserBean user = (UserBean) session.getAttribute("userBean");
        if (password.equals(user.getPassword())) {
            try {
                manager.withdrawals(amount);
                response.getWriter().write("取款成功！");
            } catch (InvalidDepositException | AccountOverDrawnException e) {
                response.getWriter().write(e.getMessage());
            }
        } else {
            response.getWriter().write("密码错误！请重试。");
        }
    }

    @RequestMapping(value = "/transfer", method = RequestMethod.POST)
    public void transfer(String targetUser, String password, double amount, HttpServletResponse response, HttpSession session) throws IOException {
        UserBean user = (UserBean) session.getAttribute("userBean");
        try {
            if (password.equals(user.getPassword())) {
                manager.transfer(targetUser, amount);
                response.getWriter().write("转账成功");
            } else {
                response.getWriter().write("密码错误！请重试。");
            }
        } catch (InvalidDepositException | AccountOverDrawnException e) {
            response.getWriter().write(e.getMessage());
        }
    }

    @RequestMapping("/getUsers")
    public void getUsers(HttpServletResponse response) throws IOException {
        List<UserBean> users = manager.getUsers();
        JSONObject obj = new JSONObject();
        obj.put("code", 0);
        obj.put("msg", "");
        obj.put("count", users.size());
        obj.put("data", users);
        response.getWriter().write(obj.toString());
    }

    @RequestMapping(value = "/changeStatus", method = RequestMethod.POST)
    public void changeStatus(int id, int status, HttpServletResponse response) throws IOException {
        manager.changeStatus(id, status);
        if (status == 1) {
            response.getWriter().write("冻结成功！");
        } else {
            response.getWriter().write("激活成功！");
        }
    }
}
