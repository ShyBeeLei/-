
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
public class BankDaoImpl implements BankDaoInterface {
    /**
     * 文件后缀名
     */
    static final String SUFFIX = ".properties";
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
    public void saveMoney(MoneyBean moneyBean, UserBean userBean) {
        //遍历用户表，搜索符合的用户对象
        for (UserBean temp : getUsers()) {
            if (temp.getUserName().equals(userBean.getUserName())) {
                //搜索到之后执行存款操作
                try {
                    DataOutputStream dos = new DataOutputStream(new FileOutputStream(userBean.getUserName() + SUFFIX));
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
    public void insertUser(UserBean u) {
        try {
            OutputStream os = new FileOutputStream(u.getUserName() + SUFFIX);
            DataOutputStream dos = new DataOutputStream(os);
            dos.writeBytes("userName=" + u.getUserName() + "\n");
            dos.writeBytes("password=" + md5.encode(u.getPassword().getBytes()) + "\n");
            dos.writeBytes("money=0.0");
            dos.close();
            //更新用户表数据
            users = getUsers();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean updateMoney(String name, double amount) {
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
    public boolean findByName(String name) {
        for (UserBean temp : getUsers()) {
            if (temp.getUserName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean findUser(String name, String password) {
        for (UserBean temp : getUsers()) {
            if (temp.getUserName().equals(name) && temp.getPassword().equals(md5.encode(password.getBytes()))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public MoneyBean getMoney(String name, String password) {
        try {
            File dir = new File("");
            String projectName = dir.getCanonicalPath();
            File project = new File(projectName);
        /*
        筛选properties文件。
         */
            File[] files = project.listFiles(pathname -> {
                String fileName = pathname.getName();
                return fileName.endsWith(SUFFIX);
            });
        /*
          遍历文件进行扫描。
         */
            for (File f : Objects.requireNonNull(files)) {
                InputStream is = new FileInputStream(f);
                Properties p = new Properties();
                p.load(is);
                if (p.getProperty("userName").equals(name) && p.getProperty("password").equals(md5.encode(password.getBytes()))) {
                    moneyBean.setMoney(Double.parseDouble(p.getProperty("money")));
                    return moneyBean;
                }
                is.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
