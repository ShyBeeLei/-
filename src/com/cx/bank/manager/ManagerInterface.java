package com.cx.bank.manager;

import com.cx.bank.model.UserBean;

/**
 * @author Bruce Xu
 * @date 2021/7/14 10:00
 */
public interface ManagerInterface {
    /**
     * 查询账户余额
     *
     * @param u User对象
     * @return 对象钱包的余额
     */
    double inquiry(UserBean u);

    /**
     * 取款方法
     *
     * @param amount 取款金额
     * @param u      取款用户
     */
    void withdrawals(double amount, UserBean u);

    /**
     * 存款方法
     *
     * @param amount 存款金额
     * @param u      存款用户
     */
    void deposit(double amount, UserBean u);

    /**
     * 转账功能
     *
     * @param u      转账用户
     * @param name   收款用户名
     * @param amount 转账金额
     * @return 用户输入的账号是否正确
     */
    boolean transfer(UserBean u, String name, double amount);

    /**
     * 重载一个没有参数的退出方法。
     */
    void exitSystem();

    /**
     * 退出系统
     *
     * @param amount 账户余额
     * @param u      用户标记
     */
    void exitSystem(double amount, UserBean u);

    /**
     * 注册账号
     *
     * @param name     用户输入的姓名
     * @param password 用户输入的密码
     * @return 是否注册成功
     */
    boolean register(String name, String password);

    /**
     * 登录账号
     *
     * @param name     用户输入的姓名
     * @param password 用户输入的密码
     * @return 用户对象
     */
    UserBean login(String name, String password);
}
