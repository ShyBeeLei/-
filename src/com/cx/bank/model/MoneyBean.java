
package com.cx.bank.model;

/**
 * @ClassName MoneyBean
 * @Description 封装钱包数据
 * @Author Bruce Xu
 * @Date 2021/7/14 9:57
 * @Version 1.9
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
