
package com.cx.bank.dao;

import com.cx.bank.model.MoneyBean;
import com.cx.bank.model.UserBean;
import com.cx.bank.util.MD5;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SQLDaoImpl
 * @Description 使用SQL处理持久层数据
 * @Author Bruce Xu
 * @Date 2021/7/26 21:06
 * @Version 1.9
 */
public class SqlDaoImpl extends BaseDao implements BankDaoInterface {
    /**
     * 加密
     */
    static MD5 md5 = new MD5();
    /**
     * 用户信息
     */
    UserBean userBean = new UserBean();
    /**
     * 数据库连接
     */
    Connection connection = null;
    /**
     * 预处理语句
     */
    PreparedStatement preparedStatement = null;
    /**
     * 结果集
     */
    ResultSet resultSet = null;

    /**
     * 存储方法
     *
     * @param moneyBean 钱包对象
     * @param userName  用户名
     */
    @Override
    public void saveMoney(MoneyBean moneyBean, String userName) {
        //遍历用户表，搜索符合的用户对象
        connection = getConnection();
        try {
            preparedStatement = connection.prepareStatement("update user.t_user set balance = ? where user_name =?");
            preparedStatement.setDouble(1, moneyBean.getMoney());
            preparedStatement.setString(2, userName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(connection, preparedStatement, resultSet);
        }
    }

    /**
     * 添加用户
     *
     * @param userName 用户名
     * @param password 用户密码
     */
    @Override
    public void insertUser(String userName, String password) {
        connection = getConnection();
        try {
            preparedStatement = connection.prepareStatement("insert into user.t_user(user_id, user_name, user_password, user_flag, balance) values (?,?,?,?,?)");
            preparedStatement.setInt(1, 0);
            preparedStatement.setString(2, userName);
            preparedStatement.setString(3, md5.encode(password.getBytes()));
            preparedStatement.setInt(4, 1);
            preparedStatement.setDouble(5, 0.0);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新金额
     *
     * @param name   转账目标用户名
     * @param amount 转账金额
     * @return 转账目标用户名是否存在
     */
    @Override
    public boolean updateMoney(String name, double amount) {
        connection = getConnection();
        try {
            preparedStatement = connection.prepareStatement("update user.t_user set balance=? where user_name=?");
            preparedStatement.setDouble(1, amount);
            preparedStatement.setString(2, name);
            if (preparedStatement.executeUpdate() >= 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(connection, preparedStatement, resultSet);
        }
        return false;
    }

    /**
     * 按用户名查找
     *
     * @param name 用户名
     * @return 是否成功
     */
    @Override
    public boolean findByName(String name) {
        connection = getConnection();
        try {
            preparedStatement = connection.prepareStatement("select user_id from user.t_user where user_name=?");
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 按用户名以及密码查找
     *
     * @param name     用户名
     * @param password 密码
     * @return 是否成功
     */
    @Override
    public UserBean findUser(String name, String password) {
        connection = getConnection();
        try {
            preparedStatement = connection.prepareStatement("select user_name,Admin,user_flag,user_id,user_password from user.t_user where user_name =? and user_password =?");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, md5.encode(password.getBytes()));
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                userBean.setUsername(resultSet.getString("user_name"));
                userBean.setAdmin(resultSet.getBoolean("Admin"));
                userBean.setUserFlag(resultSet.getInt("user_flag"));
                userBean.setUserId(resultSet.getInt("user_id"));
                userBean.setPassword(resultSet.getString("user_password"));
                return userBean;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(connection, preparedStatement, resultSet);
        }
        return null;
    }

    /**
     * 获取余额信息
     *
     * @param userName 用户名
     * @return 得到用户余额
     */
    @Override
    public double getMoney(String userName) {
        connection = getConnection();
        try {
            preparedStatement = connection.prepareStatement("select balance from user.t_user where user_name=?");
            preparedStatement.setString(1, userName);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble("balance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 通过id查找用户信息
     *
     * @param id 用户编号
     * @return 用户对象
     */
    @Override
    public UserBean findById(int id) {
        connection = getConnection();
        try {
            preparedStatement = connection.prepareStatement("select user_name,user_flag from user.t_user where user_id=?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                userBean.setUsername(resultSet.getString("user_name"));
                userBean.setUserFlag(resultSet.getInt("user_flag"));
            }
            return userBean;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 改变用户状态
     *
     * @param id     用户编号
     * @param status 用户状态
     */
    @Override
    public void setStatus(int id, int status) {
        connection = getConnection();
        if (status == 0) {
            status = 1;
        } else {
            status = 0;
        }
        try {
            preparedStatement = connection.prepareStatement("update user.t_user set user_flag=? where user_id=?");
            preparedStatement.setInt(1, status);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取所有用户信息
     *
     * @return 用户表
     */
    @Override
    public List<UserBean> getUsers() {
        List<UserBean> users = new ArrayList<>();
        connection = getConnection();
        try {
            preparedStatement = connection.prepareStatement("select user_id,user_name,user_flag from user.t_user");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userBean = new UserBean();
                userBean.setUserId(resultSet.getInt("user_id"));
                userBean.setUsername(resultSet.getString("user_name"));
                userBean.setUserFlag(resultSet.getInt("user_flag"));
                users.add(userBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     * 记录信息
     *
     * @param logType 进行的操作
     * @param amount  操作的数额
     * @param id      用户编号
     */
    @Override
    public void log(String logType, Double amount, int id) {
        connection = getConnection();
        try {
            preparedStatement = connection.prepareStatement("insert into user.t_log(log_id, log_type, log_amount, userid) values (?,?,?,?)");
            preparedStatement.setInt(1, 0);
            preparedStatement.setString(2, logType);
            preparedStatement.setDouble(3, amount);
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

