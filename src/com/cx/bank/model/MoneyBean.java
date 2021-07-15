package com.cx.bank.model;

/**
 * @author Bruce Xu
 * @date 2021/7/14 9:57
 */
public class MoneyBean {
    /**
     * 构造方法以及setter和getter
     *
     * @param money:剩余金额
     */
    private double money;


    public MoneyBean() {
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
