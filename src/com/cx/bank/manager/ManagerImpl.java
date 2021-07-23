
package com.cx.bank.manager;

import com.cx.bank.dao.BankDaoImpl;
import com.cx.bank.factory.UserDaoFactory;
import com.cx.bank.model.MoneyBean;
import com.cx.bank.model.UserBean;

/**
 * @author Bruce Xu
 * @date 2021/7/14 9:58
 */
public class ManagerImpl implements ManagerInterface {
    /**
     * 创建单例模式
     */
    private static ManagerImpl instance;
    /**
     * 创建UserDaoFactory单例
     */
    UserDaoFactory userDaoFactory = UserDaoFactory.getInstance();
    /**
     * 装配持久层对象
     */
    BankDaoImpl bdi = userDaoFactory.create();


    private ManagerImpl() {
    }

    public static synchronized ManagerImpl getInstance() {
        if (instance == null) {
            instance = new ManagerImpl();
        }
        return instance;
    }

    @Override
    public double inquiry(MoneyBean moneyBean) {
        return moneyBean.getMoney();
    }

    @Override
    public void withdrawals(double amount, MoneyBean moneyBean) {
        moneyBean.setMoney(moneyBean.getMoney() - amount);
    }

    @Override
    public void deposit(double amount, MoneyBean moneyBean) {
        moneyBean.setMoney(moneyBean.getMoney() + amount);
    }

    @Override
    public boolean transfer(MoneyBean moneyBean, String name, double amount) {
        moneyBean.setMoney(moneyBean.getMoney() - amount);
        return bdi.updateMoney(name, amount);
    }

    @Override
    public void exitSystem(MoneyBean moneyBean, UserBean u) {
        bdi.saveMoney(moneyBean, u);
        System.exit(0);
    }

    @Override
    public void exitSystem() {
        System.exit(0);
    }

    @Override
    public boolean register(String name, String password) {
        if (!bdi.findByName(name)) {
            UserBean u = new UserBean(name, password);
            bdi.insertUser(u);
            return true;
        }
        return false;
    }

    @Override
    public MoneyBean login(String name, String password) {
        return bdi.getMoney(name, password);
    }
}
