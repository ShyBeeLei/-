package com.cx.bank.manager;

/**
 * @author Bruce Xu
 * @date 2021/7/14 10:00
 */
public interface ManagerInterface {
    /**
     * 查询余额方法
     */
    void inquiry();
    /**
     * 取款方法
     */
    void withdrawals();
    /**
     * 存款方法
     */
    void deposit();
    /**
     * 退出系统方法
     */
    void exitSystem();
    /**
     * 检查用户所输入的数据类型是否正确
     */
    void scannerVerify();
}
