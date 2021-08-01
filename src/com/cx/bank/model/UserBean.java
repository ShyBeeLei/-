
package com.cx.bank.model;

/**
 * @ClassName UserBean
 * @Description 封装用户数据
 * @Author Bruce Xu
 * @Date 2021/7/14 17:44
 * @Version 1.9
 */
public class UserBean {
    private int userId;
    private String userName;
    private String password;
    private boolean Admin;
    private int userFlag;


    public UserBean() {
    }

    public int getUserFlag() {
        return userFlag;
    }

    public void setUserFlag(int userFlag) {
        this.userFlag = userFlag;
    }

    public boolean isAdmin() {
        return Admin;
    }

    public void setAdmin(boolean admin) {
        Admin = admin;
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
