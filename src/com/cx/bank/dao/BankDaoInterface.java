package com.cx.bank.dao;

import com.cx.bank.model.UserBean;

/**
 * @ClassName BankDaoInterface
 * @Description 对操作存储介质方法进行说明
 * @Author Bruce Xu
 * @Date 2021/7/14 17:51
 * @Version 1.4
 */
public interface BankDaoInterface {
    /**
     * 存储方法
     *
     * @param amount 账户余额
     * @param u      用户对象
     */
    void saveMoney(double amount, UserBean u);

    /**
     * 添加用户
     *
     * @param u 用户对象
     */
    void insertUser(UserBean u);

    /**
     * 更新金额
     *
     * @param name   转账目标用户名
     * @param amount 转账金额
     * @return 转账目标用户名是否存在
     */
    boolean updateMoney(String name, double amount);

    /**
     * 按用户名查找
     *
     * @param name 用户名
     * @return 是否成功
     */
    boolean findByName(String name);

    /**
     * 按用户名以及密码查找
     *
     * @param name     用户名
     * @param password 密码
     * @return 是否成功
     */
    boolean findUser(String name, String password);

    /**
     * 获取用户信息
     *
     * @param name     用户名
     * @param password 密码
     * @return 获取到的用户信息
     */
    UserBean getUser(String name, String password);
}
