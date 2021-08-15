package com.cx.bank.dao;

import java.sql.*;

/**
 * @ClassName BaseDao
 * @Description 用于数据库的开启与关闭
 * @Author Bruce Xu
 * @Date 2021/7/26 20:02
 * @Version 1.0
 */
public class BaseDao {
    /**
     * 数据库链接
     */
    private static final String URL = "jdbc:mysql://localhost:3306?serverTimezone=GMT%2B8";
    /**
     * 数据库用户名
     */
    private static final String USERNAME = "admin";
    /**
     * 数据库密码
     */
    private static final String PASSWORD = "1234";
    /**
     * 连接对象
     */
    Connection connection = null;
    ResultSet resultSet = null;

    protected Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }


    protected void closeAll(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        try {
            if (connection != null) {
                connection.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
