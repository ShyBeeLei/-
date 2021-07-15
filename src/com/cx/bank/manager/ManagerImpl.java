package com.cx.bank.manager;

import com.cx.bank.model.MoneyBean;

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
    public void inquiry(MoneyBean m) {
        System.out.println("您的余额为：" + m.getMoney() + "元");
    }

    @Override
    public void withdrawals(double amount, MoneyBean m) {
        m.setMoney(m.getMoney() - amount);
        System.out.println("取款成功！您的余额为：" + m.getMoney() + "元");
    }

    @Override
    public void deposit(double amount, MoneyBean m) {
        m.setMoney(m.getMoney() + money);
        System.out.println("存款成功！您的余额为：" + m.getMoney() + "元");
    }

    @Override
    public void exitSystem() {
        System.out.println("欢迎下次使用！");
        sc.close();
        System.exit(0);
    }
}
