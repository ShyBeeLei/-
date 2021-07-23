
package com.cx.bank.model;

/**
 * @author Bruce Xu
 * @date 2021/7/14 9:57
 */
public class MoneyBean {
    /**
     * 剩余金额
     */
    protected double money;


    public MoneyBean() {
    }

    public MoneyBean(double money) {
        this.money = money;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
