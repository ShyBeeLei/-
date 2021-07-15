package com.cx.bank.manager;

import com.cx.bank.model.MoneyBean;

import java.util.Scanner;

/**
 * @author Bruce Xu
 * @date 2021/7/14 9:58
 */
public class ManagerImpl implements ManagerInterface{
    /**
     * MoneyBean对象
     */
    MoneyBean m = new MoneyBean();
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
    private static ManagerImpl instance=new ManagerImpl();
    private ManagerImpl(){}
    public static ManagerImpl getInstance(){
        return instance;
    }

    @Override
    public void inquiry() {
        System.out.println("您的余额为：" + m.getMoney() + "元");
    }

    @Override
    public void withdrawals() {
        System.out.println("请输入您的取款金额：");
        scannerVerify();
        if (money > 0) {
            if (m.getMoney() > money) {
                //更新用户剩余金额
                m.setMoney(m.getMoney() - money);
                System.out.println("取款成功！您的余额为：" + m.getMoney() + "元");
            } else {
                System.out.println("您的余额不足，请重试。");
            }
        } else {
            System.out.println("请输入一个正确的数值！");
        }
    }

    @Override
    public void deposit() {
        System.out.println("请输入您的存款金额：");
        scannerVerify();
        if (money > 0) {
            //更新用户剩余金额
            m.setMoney(m.getMoney() + money);
            System.out.println("存款成功！您的余额为：" + m.getMoney() + "元");
        } else {
            System.out.println("请输入正确的金额！");
        }
    }

    @Override
    public void exitSystem() {
        System.out.println("欢迎下次使用！");
        sc.close();
        System.exit(0);
    }

    @Override
    public void scannerVerify() {
        while (true) {
            try {
                money = sc.nextDouble();
                break;
            } catch (Exception e) {
                System.out.println("请输入正确的选项！");
                sc = new Scanner(System.in);
            }
        }
    }
}
