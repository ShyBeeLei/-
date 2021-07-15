package com.cx.bank.dao;

import com.cx.bank.model.MoneyBean;
import com.cx.bank.model.UserBean;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * @ClassName BankDaoImpl
 * @Description 实现对存储介质的操作
 * @Author Bruce Xu
 * @Date 2021/7/14 17:56
 * @Version 1.4
 */
public class BankDaoImpl implements BankDaoInterface {
    /**
     * 文件后缀名
     */
    static final String SUFFIX = ".properties";
    /**
     * 用户对象表
     */
    private static List<UserBean> users = new ArrayList<>();
    /**
     * 创建单例模式，进行封装。
     */
    private static BankDaoImpl instance = new BankDaoImpl();

    private BankDaoImpl() {
    }

    public static BankDaoImpl getInstance() {
        return instance;
    }

    /**
     * 将项目目录下所有的properties文件筛选并扫描其中内容存入用户表中。
     * @return  list<UserBean>
     */
    public static List<UserBean> getUsers() throws IOException {
        File dir = new File("");
        String projectName = dir.getCanonicalPath();
        File project = new File(projectName);
        /*
        筛选properties文件。
         */
        File[] files = project.listFiles(pathname -> {
            String name = pathname.getName();
            return name.endsWith(SUFFIX);
        });
        /*
          遍历文件进行扫描。
         */
        for (File f : files) {
            InputStream is = new FileInputStream(f);
            Properties p = new Properties();
            p.load(is);
            UserBean u = new UserBean(p.getProperty("userName"), p.getProperty("password"), Double.parseDouble(p.getProperty("money")));
            users.add(u);
            is.close();
        }
        /*
        清除重复内容
         */
        users = users.stream().distinct().collect(Collectors.toList());
        return users;
    }

    @Override
    public void saveMoney(double amount, UserBean u) {
        try {
            //遍历用户表，搜索符合的用户对象
            for (UserBean temp : getUsers()) {
                if (temp.getUserName().equals(u.getUserName())) {
                    temp.setMoney(u.getMoney());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        propertiesWriteIn(u);
    }

    @Override
    public void insertUser(UserBean u) {
        try {
            OutputStream os = new FileOutputStream(u.getUserName() + SUFFIX);
            DataOutputStream dos = new DataOutputStream(os);
            dos.writeBytes("userName=" + u.getUserName() + "\n");
            dos.writeBytes("password=" + u.getPassword() + "\n");
            dos.writeBytes("money=" + u.getMoney());
            dos.close();
            //更新用户表数据
            users = getUsers();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateMoney(UserBean u, MoneyBean m) {
        u.setMoney(u.getMoney() - m.getMoney());
        for (UserBean temp : users) {
            if (u.getUserName().equals(temp.getUserName())) {
                temp.setMoney(u.getMoney());
            }
        }
        propertiesWriteIn(u);
        System.out.println("转账成功！您的余额为：" + u.getMoney());
    }


    @Override
    public boolean findByName(String name) {
        try {
            List<UserBean> users = getUsers();
            for (UserBean temp : users) {
                if (temp.getUserName().equals(name)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean findUser(String name, String password) {
        try {
            List<UserBean> users = getUsers();
            for (UserBean temp : users) {
                if (temp.getUserName().equals(name) && temp.getPassword().equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public UserBean getUser(String name, String password) {
        for (UserBean temp : users) {
            if (name.equals(temp.getUserName()) && password.equals(temp.getPassword())) {
                return temp;
            }
        }
        return null;
    }

    /**
     * 将新获取到的UserBean对象写入properties文件
     * @param u：要写入的UserBean对象
     */
    public void propertiesWriteIn(UserBean u) {
        try {
            InputStream is = new FileInputStream(u.getUserName() + SUFFIX);
            Properties p = new Properties();
            p.load(is);
            p.setProperty("money", String.valueOf(u.getMoney()));
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(u.getUserName() + SUFFIX));
            dos.writeBytes("userName=" + p.getProperty("userName") + "\n");
            dos.writeBytes("password=" + p.getProperty("password") + "\n");
            dos.writeBytes("money=" + p.getProperty("money"));
            dos.close();
            //更新用户表
            users = getUsers();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
