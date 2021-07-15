package com.cx.bank.test;

import com.cx.bank.manager.ManagerImpl;
import com.cx.bank.model.MoneyBean;
import com.cx.bank.model.UserBean;
import com.cx.bank.util.AccountOverDrawnException;
import com.cx.bank.util.InvalidDepositException;

import java.util.Scanner;

/**
 * @author Bruce Xu
 * @date 2021/7/14 10:29
 */
public class TestBank {
    /**
     * 业务层对象
     */
    ManagerImpl mpi = ManagerImpl.getInstance();
    /**
     * 用户对象
     */
    UserBean u = new UserBean();
    /**
     * 扫描器
     */
    Scanner sc = new Scanner(System.in);
    /**
     * 选项序号
     */
    private int choNum;
    /**
     * 判断程序能否继续进行
     */
    boolean b = true;
    /**
     * 用户输入的金额
     */
    double money = 0;

    /**
     * 测试函数构造方法，生成测试选择界面并且调用业务层的各个方法
     */
    public TestBank() {
        System.out.println("选择登陆方式：");

        /*
         用户界面生成与条件判断。
         */
        while (b) {
            System.out.println("1.登录    2.注册    3.退出");
            /*
            判断输入选项是否正确
             */
            while (b) {
                try {
                    choNum = sc.nextInt();
                    break;
                } catch (Exception e) {
                    System.out.println("请输入正确的选项！");
                }
            }
            /*
            登录界面生成
             */
            switch (choNum) {
                case 1:
                    System.out.print("请输入您的用户名：");
                    String name = sc.next();
                    System.out.print("请输入您的密码：");
                    String password = sc.next();
                    if ((u=mpi.login(name, password) )!= null) {
                        //记录状态并跳出登录界面循环
                        b = false;
                        break;
                    } else {
                        //回到初始界面，进行重新登录
                        new TestBank();
                    }
                case 2:
                    System.out.print("请输入您的用户名：");
                    String name2 = sc.next();
                    System.out.print("请输入您的密码：");
                    String password2 = sc.next();
                    if (mpi.register(name2, password2)) {
                        //记录状态并跳出登录界面循环
                        u=new UserBean(name2,password2,0.0);
                        b = false;
                        break;
                    } else {
                        //回到初始界面重新登陆
                        new TestBank();
                    }
                case 3:
                    mpi.exitSystem(u.getMoney(),u);
                default:
                    //处理收到非选项数字的情况
                    System.out.println("请输入一个正确的选项！");
                    new TestBank();
                    break;
            }
        }
        b = true;
        System.out.println("欢迎来到测试界面，请选择您要测试的方法：");

        while (b) {
            System.out.println("1.查询    2.取款    3.存款    4.退出系统");
            //判断用户输入数据类型是否正确
            while (b) {
                try {
                    choNum = sc.nextInt();
                    break;
                } catch (Exception e) {
                    System.out.println("请输入正确的选项！");
                }
            }
            //进行选项判断
            switch (choNum) {
                case 1:
                    mpi.inquiry(u);
                    break;
                case 2:
                    System.out.println("请输入您的取款金额：");
                    money = sc.nextDouble();
                    try {
                        if (money>0&&money <= u.getMoney()) {
                            mpi.withdrawals(money, u);
                        } else {
                            throw new AccountOverDrawnException();
                        }
                    } catch (AccountOverDrawnException ignored) {
                    }
                    break;
                case 3:
                    System.out.println("请输入您的存款金额：");
                    money = sc.nextDouble();
                    try {
                        if (money > 0) {
                            mpi.deposit(money, u);
                        } else {
                            throw new InvalidDepositException();
                        }
                    } catch (InvalidDepositException ignored) {
                    }
                    break;
                case 4:
                    mpi.exitSystem(u.getMoney(),u);
                default:
                    System.out.println("请输入一个正确的选项！");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        //创造程序入口，进行测试。
        new TestBank();
    }
}
