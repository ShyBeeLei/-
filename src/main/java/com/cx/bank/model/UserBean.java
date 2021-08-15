
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
    private String username;
    private String password;
    private boolean admin;
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
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        String status;
        if (userFlag == 1) {
            status = "激活";
        } else {
            status = "冻结";
        }
        return "User{" +
                "g1='" + userId + '\'' +
                ", g2='" + username + '\'' +
                ", g3='" + status + '\'' + '}';
    }
}
