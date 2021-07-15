package com.cx.bank.model;

/**
 * @ClassName UserBean
 * @Description 封装用户数据
 * @Author Bruce Xu
 * @Date 2021/7/14 17:44
 * @Version 1.4
 */
public class UserBean extends MoneyBean{
    public String userName;
    public String password;

    public UserBean() {
    }

    public UserBean(String userName, String password,double amount) {
        super();
        this.userName = userName;
        this.password = password;
        this.money=amount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
