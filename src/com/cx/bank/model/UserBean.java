
package com.cx.bank.model;

/**
 * @ClassName UserBean
 * @Description 封装用户数据
 * @Author Bruce Xu
 * @Date 2021/7/14 17:44
 * @Version 1.9
 */
public class UserBean {
    public int userId;
    public String userName;
    public String password;


    public UserBean() {
    }

    public UserBean(String userName, String password) {
        super();
        this.userName = userName;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
