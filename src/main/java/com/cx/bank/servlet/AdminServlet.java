package com.cx.bank.servlet;

import com.cx.bank.manager.ManagerImpl;
import com.cx.bank.model.UserBean;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName AdminServlet
 * @Description 处理管理员界面请求
 * @Author Bruce Xu
 * @Date 2021/8/9 21:51
 * @Version 1.0
 */
@WebServlet(urlPatterns = "/AdminServlet", name = "管理员处理")
public class AdminServlet extends HttpServlet {
    ManagerImpl manager = ManagerImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserBean> users = manager.getUsers();
        JSONObject obj = new JSONObject();
        obj.put("code", 0);
        obj.put("msg", "");
        obj.put("count", users.size());
        obj.put("data", users);
        resp.getWriter().write(obj.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("targetUser"));
        int status = Integer.parseInt(req.getParameter("userStatues"));
        manager.changeStatus(userId, status);
        if (status == 1) {
            resp.getWriter().write("冻结成功！");
        } else {
            resp.getWriter().write("激活成功！");
        }
    }

}
