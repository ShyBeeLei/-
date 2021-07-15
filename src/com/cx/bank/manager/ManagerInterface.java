package com.cx.bank.manager;

import com.cx.bank.model.MoneyBean;

/**
 * @author Bruce Xu
 * @date 2021/7/14 10:00
 */
public interface ManagerInterface {
    /**
     * 查询余额方法
     */
    void inquiry(MoneyBean m);
    /**
     * 取款方法
     * @param amount:取款金额
     * @param m:取款用户
     */
    void withdrawals(double amount, MoneyBean m);

    /**
     * 存款方法
     * @param amount：存款金额
     * @param m：存款用户
     */
    void deposit(double amount,MoneyBean m);
    /**
     * 退出系统方法
     */
    void exitSystem();
}
