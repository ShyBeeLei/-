
package com.cx.bank.dao;

import com.cx.bank.model.MoneyBean;
import com.cx.bank.model.UserBean;
import com.cx.bank.util.MD5;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * @ClassName BankDaoImpl
 * @Description 实现对存储介质的操作
 * @Author Bruce Xu
 * @Date 2021/7/14 17:56
 * @Version 1.4
 */
public class FileDaoImpl implements BankDaoInterface {
    /**
     * 文件后缀名
     */
    static final java.lang.String SUFFIX = ".properties";
    /**
     * 创建加密方法对象
     */
    static MD5 md5 = new MD5();
    /**
     * 钱包对象
     */
    static MoneyBean moneyBean = new MoneyBean();
    /**
     * 用户对象表
     */
    private static List<UserBean> users = new ArrayList<>();

    /**
     * 将项目目录下所有的properties文件筛选并扫描其中内容存入用户表中。
     *
     * @return list<UserBean>
     */
    public static List<UserBean> getUsers() {
        try {
            File dir = new File("");
            java.lang.String projectName = dir.getCanonicalPath();
            File project = new File(projectName);
        /*
        筛选properties文件。
         */
            File[] files = project.listFiles(pathname -> {
                java.lang.String name = pathname.getName();
                return name.endsWith(SUFFIX);
            });
        /*
          遍历文件进行扫描。
         */
            for (File f : Objects.requireNonNull(files)) {
                InputStream is = new FileInputStream(f);
                Properties p = new Properties();
                p.load(is);
                UserBean userBean = new UserBean(p.getProperty("userName"), p.getProperty("password"));
                users.add(userBean);
                is.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
        清除重复内容
         */
        users = users.stream().distinct().collect(Collectors.toList());
        return users;
    }

    @Override
    public void saveMoney(MoneyBean moneyBean, String userName) {
        //遍历用户表，搜索符合的用户对象
        for (UserBean temp : getUsers()) {
            if (temp.getUserName().equals(userName)) {
                //搜索到之后执行存款操作
                try {
                    DataOutputStream dos = new DataOutputStream(new FileOutputStream(userName + SUFFIX));
                    dos.writeBytes("userName=" + temp.getUserName() + "\n");
                    dos.writeBytes("password=" + temp.getPassword() + "\n");
                    dos.writeBytes("money=" + moneyBean.getMoney());
                    dos.close();
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void insertUser(String userName, String password) {
        try {
            OutputStream os = new FileOutputStream(userName + SUFFIX);
            DataOutputStream dos = new DataOutputStream(os);
            dos.writeBytes("userName=" + userName + "\n");
            dos.writeBytes("password=" + md5.encode(password.getBytes()) + "\n");
            dos.writeBytes("money=0.0");
            dos.close();
            //更新用户表数据
            users = getUsers();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean updateMoney(java.lang.String name, double amount) {
        for (UserBean temp : getUsers()) {
            if (temp.getUserName().equals(name)) {
                try {
                    DataOutputStream dos = new DataOutputStream(new FileOutputStream(name + SUFFIX));
                    dos.writeBytes("userName=" + temp.getUserName() + "\n");
                    dos.writeBytes("password=" + temp.getPassword() + "\n");
                    dos.writeBytes("money=" + amount);
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean findByName(java.lang.String name) {
        for (UserBean temp : getUsers()) {
            if (temp.getUserName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String findUser(java.lang.String name, java.lang.String password) {
        for (UserBean temp : getUsers()) {
            if (temp.getUserName().equals(name) && temp.getPassword().equals(md5.encode(password.getBytes()))) {
                return temp.userName;
            }
        }
        return null;
    }

    @Override
    public double getMoney(String name) {
        try {
            File dir = new File("");
            java.lang.String projectName = dir.getCanonicalPath();
            File project = new File(projectName);
        /*
        筛选properties文件。
         */
            File[] files = project.listFiles(pathname -> {
                java.lang.String fileName = pathname.getName();
                return fileName.endsWith(SUFFIX);
            });
        /*
          遍历文件进行扫描。
         */
            for (File f : Objects.requireNonNull(files)) {
                InputStream is = new FileInputStream(f);
                Properties p = new Properties();
                p.load(is);
                if (p.getProperty("userName").equals(name)) {
                    return Double.parseDouble(p.getProperty("money"));
                }
                is.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }
}