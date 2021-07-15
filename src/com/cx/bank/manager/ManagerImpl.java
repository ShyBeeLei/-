package com.cx.bank.manager;

import com.cx.bank.dao.BankDaoImpl;
import com.cx.bank.model.MoneyBean;
import com.cx.bank.model.UserBean;

import java.util.Scanner;

/**
 * @author Bruce Xu
 * @date 2021/7/14 9:58
 */
public class ManagerImpl implements ManagerInterface {
    /**
     * 扫描器
     */
    Scanner sc = new Scanner(System.in);
    /**
     * 持久层接口
     */
    BankDaoImpl bdi =BankDaoImpl.getInstance();
    /**
     * 用户输入的金额。
     */
    double money = 0;

    /**
     * 使用单例模式进行封装
     */
    private static ManagerImpl instance = new ManagerImpl();

    private ManagerImpl() {
    }

    public static ManagerImpl getInstance() {
        return instance;
    }

    @Override
    public void inquiry(UserBean u) {
        System.out.println("您的余额为：" + u.getMoney() + "元");
    }

    @Override
    public void withdrawals(double amount, UserBean u) {
        u.setMoney(u.getMoney() - amount);
        System.out.println("取款成功！您的余额为：" + u.getMoney() + "元");
    }

    @Override
    public void deposit(double amount, UserBean u) {
        u.setMoney(u.getMoney() + amount);
        System.out.println("存款成功！您的余额为：" + u.getMoney() + "元");
    }

    @Override
    public void exitSystem(double amount,UserBean u) {
        bdi.saveMoney(amount,u);
        System.out.println("欢迎下次使用！");
        sc.close();
        System.exit(0);
    }

    @Override
    public boolean register(String name, String password) {
        if (bdi.findByName(name)) {
            System.out.println("用户名已被注册，请换一个吧！");
            return false;
        } else {
            UserBean u = new UserBean(name, password, 0);
            bdi.insertUser(u);
            System.out.println("注册成功！");
            return true;
        }
    }

    @Override
    public UserBean login(String name, String password) {
        if (bdi.findUser(name, password)) {
            System.out.println("登录成功！");
            return bdi.getUser(name, password);
        } else {
            System.out.println("账号密码错误！请重新输入。");
            return null;
        }
    }
}
