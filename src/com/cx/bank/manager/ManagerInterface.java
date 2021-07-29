package com.cx.bank.manager;

import com.cx.bank.util.AccountOverDrawnException;
import com.cx.bank.util.InvalidDepositException;

/**
 * @ClassName ManagerInterface
 * @Description 声明业务层方法
 * @Author Bruce Xu
 * @Date 2021/7/14 10:00
 * @Version 1.9
 */
public interface ManagerInterface {
    /**
     * 查询账户余额
     *
     * @return 对象钱包的余额
     */
    double inquiry();

    /**
     * 取款方法
     *
     * @param amount 取款金额
     * @throws AccountOverDrawnException 余额不足异常
     * @throws InvalidDepositException   无效金额异常
     */
    void withdrawals(double amount) throws AccountOverDrawnException, InvalidDepositException;

    /**
     * 存款方法
     *
     * @param amount 存款金额
     * @throws InvalidDepositException 无效金额异常
     */
    void deposit(double amount) throws InvalidDepositException;

    /**
     * 转账功能
     *
     * @param name   收款用户名
     * @param amount 转账金额
     * @return 用户输入的账号是否正确
     * @throws AccountOverDrawnException 余额不足异常
     * @throws InvalidDepositException   无效金额异常
     */
    boolean transfer(String name, double amount) throws InvalidDepositException, AccountOverDrawnException;

    /**
     * 重载一个没有参数的退出方法。
     */
    void exitSystem();

    /**
     * 退出系统
     *
     * @param userName 用户名
     */
    void exitSystem(String userName);

    /**
     * 注册账号
     *
     * @param name     用户输入的姓名
     * @param password 用户输入的密码
     * @return 是否注册成功
     */
    boolean register(String name, String password);

    /**
     * 验证用户名是否重复
     *
     * @param name 用户传入的用户名
     * @return 用户名是否存在
     */
    boolean verifyUserName(String name);

    /**
     * 登录账号
     *
     * @param name     用户输入的姓名
     * @param password 用户输入的密码
     * @return 用户对象
     */
    String login(String name, String password);
}
