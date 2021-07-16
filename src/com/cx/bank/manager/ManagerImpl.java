package com.cx.bank.manager;

import com.cx.bank.dao.BankDaoImpl;
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
    BankDaoImpl bdi = BankDaoImpl.getInstance();
    /**
     * 创建加密方法对象
     */

    /**
     * 创建单例模式
     */
    private static ManagerImpl instance;

    private ManagerImpl() {
    }

    public static ManagerImpl getInstance() {
        if (instance == null) {
            instance = new ManagerImpl();
        }
        return instance;
    }

    @Override
    public double inquiry(UserBean u) {
        return u.getMoney();
    }

    @Override
    public void withdrawals(double amount, UserBean u) {
        u.setMoney(u.getMoney() - amount);
    }

    @Override
    public void deposit(double amount, UserBean u) {
        u.setMoney(u.getMoney() + amount);
    }

    @Override
    public boolean transfer(UserBean u, String name, double amount) {
        u.setMoney(u.getMoney() - amount);
        return bdi.updateMoney(name, amount);
    }

    @Override
    public void exitSystem(double amount, UserBean u) {
        bdi.saveMoney(amount, u);
        sc.close();
        System.exit(0);
    }

    @Override
    public void exitSystem() {
        sc.close();
        System.exit(0);
    }

    @Override
    public boolean register(String name, String password) {
        if (!bdi.findByName(name)) {
            UserBean u = new UserBean(name, password, 0);
            bdi.insertUser(u);
            return true;
        }
        return false;
    }

    @Override
    public UserBean login(String name, String password) {
        return bdi.getUser(name,password);
    }
}
