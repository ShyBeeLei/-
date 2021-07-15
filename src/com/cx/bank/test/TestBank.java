package com.cx.bank.test;

import com.cx.bank.manager.ManagerImpl;

import java.util.Scanner;

/**
 * @author Bruce Xu
 * @date 2021/7/14 10:29
 */
public class TestBank {
    /**
     * 生成业务层对象
     */
    ManagerImpl mpl = ManagerImpl.getInstance();
    /**
     * 选项序号
     */
    private int choNum;
    /**
     * 判断程序能否继续进行
     */
    boolean b = true;

    /**
     * 测试函数构造方法，生成测试选择界面并且调用业务层的各个方法
     */
    public TestBank() {
        System.out.println("欢迎来到测试界面，请选择您要测试的方法：");

        while (b) {
            System.out.println("1.查询    2.取款    3.存款    4.退出系统");
            Scanner sc = new Scanner(System.in);
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
                    mpl.inquiry();
                    break;
                case 2:
                    mpl.withdrawals();
                    break;
                case 3:
                    mpl.deposit();
                    break;
                case 4:
                    mpl.exitSystem();
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
