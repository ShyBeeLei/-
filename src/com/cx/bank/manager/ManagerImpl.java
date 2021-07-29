
package com.cx.bank.manager;

import com.cx.bank.dao.SqlDaoImpl;
import com.cx.bank.factory.UserDaoFactory;
import com.cx.bank.model.MoneyBean;
import com.cx.bank.util.AccountOverDrawnException;
import com.cx.bank.util.InvalidDepositException;

/**
 * @ClassName ManagerImpl
 * @Description 调用持久层方法实现业务
 * @Author Bruce Xu
 * @Date 2021/7/14 9:58
 * @Version 1.9
 */
public class ManagerImpl implements ManagerInterface {
    /**
     * 创建单例模式
     */
    private static ManagerImpl instance;
    /**
     * 用户是否已登录
     */
    boolean flag = false;
    /**
     * 钱包对象
     */
    MoneyBean moneyBean = new MoneyBean();
    /**
     * 创建UserDaoFactory单例
     */
    UserDaoFactory userDaoFactory = UserDaoFactory.getInstance();
    /**
     * 装配持久层对象
     */
    SqlDaoImpl bdi = (SqlDaoImpl) userDaoFactory.create();


    private ManagerImpl() {
    }

    public static synchronized ManagerImpl getInstance() {
        if (instance == null) {
            instance = new ManagerImpl();
        }
        return instance;
    }

    /**
     * 查询账户余额
     *
     * @return 账户的余额
     */
    @Override
    public double inquiry() {
        return moneyBean.getMoney();
    }

    /**
     * 取款方法
     *
     * @param amount 取款金额
     * @throws AccountOverDrawnException 余额不足异常
     * @throws InvalidDepositException   无效金额异常
     */
    @Override
    public void withdrawals(double amount) throws AccountOverDrawnException, InvalidDepositException {
        if (amount < 0) {
            throw new InvalidDepositException("请输入正确的金额！");
        } else {
            if (amount > moneyBean.getMoney()) {
                throw new AccountOverDrawnException("余额不足！");
            } else {
                moneyBean.setMoney(moneyBean.getMoney() - amount);
            }
        }
    }

    /**
     * 存款方法
     *
     * @param amount 存款金额
     */
    @Override
    public void deposit(double amount) throws InvalidDepositException {
        if (amount < 0) {
            throw new InvalidDepositException("请输入正确的金额！");
        } else {
            moneyBean.setMoney(moneyBean.getMoney() + amount);
        }
    }

    /**
     * 转账功能
     *
     * @param name   收款用户名
     * @param amount 转账金额
     * @return 用户输入的账号是否正确
     */
    @Override
    public boolean transfer(String name, double amount) throws InvalidDepositException, AccountOverDrawnException {
        if (amount < 0) {
            throw new InvalidDepositException("请输入正确的金额！");
        } else {
            if (amount > moneyBean.getMoney()) {
                throw new AccountOverDrawnException("余额不足！");
            } else {
                moneyBean.setMoney(moneyBean.getMoney() - amount);
            }
        }
        return bdi.updateMoney(name, amount);
    }

    /**
     * 退出系统
     *
     * @param userName 用户名
     */
    @Override
    public void exitSystem(String userName) {
        bdi.saveMoney(moneyBean, userName);
        System.exit(0);
    }

    /**
     * 重载一个没有参数的退出方法。
     */
    @Override
    public void exitSystem() {
        System.exit(0);
    }

    /**
     * 注册账号
     *
     * @param name     用户输入的姓名
     * @param password 用户输入的密码
     * @return 是否注册成功
     */
    @Override
    public boolean register(String name, String password) {
        if (!bdi.findByName(name)) {
            bdi.insertUser(name, password);
            return true;
        }
        return false;
    }

    /**
     * 验证用户名是否重复
     *
     * @param name 用户传入的用户名
     * @return 用户名是否存在
     */
    @Override
    public boolean verifyUserName(String name) {
        return bdi.findByName(name);
    }

    /**
     * 登录账号
     *
     * @param name     用户输入的姓名
     * @param password 用户输入的密码
     * @return 用户对象
     */
    @Override
    public String login(String name, String password) {
        if (!flag) {
            moneyBean.setMoney(bdi.getMoney(name));
            flag = true;
        }
        return bdi.findUser(name, password);
    }
}
