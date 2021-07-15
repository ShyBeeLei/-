package com.cx.bank.util;

/**
 * @ClassName WrongDataException
 * @Description 数据类型错误
 * @Author Bruce Xu
 * @Date 2021/7/14 17:04
 * @Version 1.0
 */
public class WrongDataException extends Exception{
    public WrongDataException(){
        System.out.println("请输入正确的数据！");
    }
}
