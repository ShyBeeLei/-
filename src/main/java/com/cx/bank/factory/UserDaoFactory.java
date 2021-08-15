
package com.cx.bank.factory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @ClassName UserDaoFactory
 * @Description 进行业务层和持久层的动态装配
 * @Author Bruce Xu
 * @Date 2021/7/22 20:21
 * @Version 1.0
 */
public class UserDaoFactory {
    /**
     * 创建bankDao对象存储反射创建的实例
     */
    private static Object bankDao;
    /**
     * 创建UserDaoFactory单例模式
     */
    private static UserDaoFactory instance;

    private UserDaoFactory() {
    }

    public static synchronized UserDaoFactory getInstance() {
        if (instance == null) {
            instance = new UserDaoFactory();
        }
        return instance;
    }

    /**
     * 装配方法
     *
     * @return bankDao对象
     */
    public Object create() {
        try {
            FileInputStream fileInputStream = new FileInputStream("C:\\My file\\Practice\\banksys\\classInfo.properties");
            Properties properties = new Properties();
            properties.load(fileInputStream);
            String className = properties.getProperty("className");
            bankDao = Class.forName(className).newInstance();
        } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return bankDao;
    }
}
