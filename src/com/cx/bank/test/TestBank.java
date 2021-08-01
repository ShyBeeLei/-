
package com.cx.bank.test;

import com.cx.bank.manager.ManagerImpl;
import com.cx.bank.util.AccountOverDrawnException;
import com.cx.bank.util.InvalidDepositException;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @ClassName TestBank
 * @Description 测试界面
 * @Author Bruce Xu
 * @Date 2021/7/14 10:29
 * @Version 1.9
 */
public class TestBank {
    /**
     * 业务层对象
     */
    ManagerImpl mpi = ManagerImpl.getInstance();
    /**
     * 用户ID
     */
    String userName;
    /**
     * 扫描器
     */
    Scanner sc = new Scanner(System.in);
    /**
     * 判断程序能否继续进行
     */
    boolean flag = true;
    /**
     * 用户输入的金额
     */
    double money = 0;
    /**
     * 选项序号
     */
    private int choNum;

    /**
     * 测试函数构造方法，生成测试选择界面并且调用业务层的各个方法
     */
    public TestBank() {
        System.out.println("---------欢迎使用银行管理系统--------");
        /*
         用户界面生成与条件判断。
         */
        while (flag) {
            System.out.println("          请选择登录方式：          ");
            System.out.println("    1.注册    2.登录    3.退出     ");
            /*
            判断输入选项是否正确
             */
            while (flag) {
                try {
                    choNum = sc.nextInt();
                    break;
                } catch (Exception e) {
                    System.out.println("请输入正确的选项！");
                    sc = new Scanner(System.in);
                }
            }
            switch (choNum) {
                case 1:
                    System.out.println("-------------请注册---------------");
                    System.out.print("请输入您的用户名：");
                    String name2 = sc.next();
                    System.out.print("请输入您的密码：");
                    String password2 = sc.next();
                    if (mpi.register(name2, password2)) {
                        //记录状态并进入登录界面
                        System.out.println("注册成功！");
                        break;
                    } else {
                        //回到初始界面重新登陆
                        System.out.println("用户名已被注册，请换一个吧！");
                        continue;
                    }
                case 2:
                    System.out.println("-------------请登录---------------");
                    System.out.print("请输入您的用户名：");
                    String name = sc.next();
                    System.out.print("请输入您的密码：");
                    String password = sc.next();
                    mpi.login(name, password);
                    if (userName != null) {
                        //记录状态并跳出登录界面循环
                        System.out.println("登陆成功！");
                        flag = false;
                        break;
                    } else {
                        System.out.println("账号密码错误，请重试！");
                        continue;
                    }
                case 3:
                    System.out.println("欢迎下次使用！");
                    mpi.exitSystem();
                default:
                    //处理收到非选项数字的情况
                    System.out.println("请输入正确的选项！");
                    break;
            }
        }
        flag = true;
        System.out.println("欢迎来到测试界面，请选择您要测试的方法：");

        while (flag) {
            System.out.println("1.查询    2.取款    3.存款    4.转账    5.退出系统");
            //判断用户输入数据类型是否正确
            while (flag) {
                try {
                    choNum = sc.nextInt();
                    break;
                } catch (Exception e) {
                    System.out.println("请输入正确的选项！");
                    sc = new Scanner(System.in);
                }
            }
            //进行选项判断
            switch (choNum) {
                case 1:
                    System.out.println("您的余额为：" + mpi.inquiry());
                    break;
                case 2:
                    System.out.println("请输入您的取款金额：");
                    try {
                        money = sc.nextDouble();
                        mpi.withdrawals(money);
                        System.out.println("取款成功！");

                    } catch (AccountOverDrawnException | InvalidDepositException e) {
                        System.out.println(e.getMessage());
                    } catch (InputMismatchException e) {
                        System.out.println("请输入正确的数额！");
                        sc = new Scanner(System.in);
                    }
                    break;
                case 3:
                    System.out.println("请输入您的存款金额：");
                    try {
                        money = sc.nextDouble();
                        mpi.deposit(money);
                        System.out.println("存款成功！");
                    } catch (InvalidDepositException e) {
                        System.out.println(e.getMessage());
                        sc = new Scanner(System.in);
                    } catch (InputMismatchException e) {
                        System.out.println("请输入正确的金额！");
                        sc = new Scanner(System.in);
                    }
                    break;
                case 4:
                    System.out.println("请输入您的密码：");
                    String password = sc.next();
                    System.out.println("请再输入一次您的密码");
                    if (password.equals(sc.next())) {
                        if (mpi.login(userName, password) != null) {
                            System.out.println("请输入收款方账号：");
                            String name = sc.next();
                            System.out.println("请输入您的转账金额：");
                            try {
                                money = sc.nextDouble();
                                if (mpi.transfer(name, money)) {
                                    System.out.println("转账成功！");
                                    break;
                                } else {
                                    System.out.println("收款方不存在！");
                                    continue;
                                }
                            } catch (AccountOverDrawnException | InvalidDepositException e) {
                                System.out.println(e.getMessage());
                            } catch (InputMismatchException e) {
                                System.out.println("请输入正确的金额！");
                                sc = new Scanner(System.in);
                            }
                        }
                    } else {
                        System.out.println("密码错误！请重试。");
                        continue;
                    }
                case 5:
                    System.out.println("欢迎下次使用！");
                    mpi.exitSystem();
                default:
                    System.out.println("请输入正确的选项！");
                    break;
            }
        }

    }

    public static void main(String[] args) {
        //创造程序入口，进行测试。
        new TestBank();
    }
}
