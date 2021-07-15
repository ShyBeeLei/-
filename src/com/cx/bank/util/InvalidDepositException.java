package com.cx.bank.util;

import com.cx.bank.manager.ManagerImpl;

/**
 * @ClassName InvalidDepositException
 * @Description 存款为负数异常
 * @Author Bruce Xu
 * @Date 2021/7/14 16:28
 * @Version 1.4
 */
public class InvalidDepositException extends Exception{
    public InvalidDepositException(){
        System.out.println("请输入正确的存款金额！");;
    }
}
