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
    ManagerImpl Mpl=ManagerImpl.getInstance();
    /**
     * 判断程序能否继续进行
     */
    private boolean b = true;
    /**
     * 选项序号
     */
    private int ChoNum;

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
                    ChoNum = sc.nextInt();
                    break;
                } catch (Exception e) {
                    System.out.println("请输入正确的选项！");
                    sc = new Scanner(System.in);
                }
            }
            //进行选项判断
            switch (ChoNum) {
                case 1:
                    Mpl.inquiry();
                    break;
                case 2:
                    Mpl.withdrawals();
                    break;
                case 3:
                    Mpl.deposit();
                    break;
                case 4:
                    Mpl.exitSystem();
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
