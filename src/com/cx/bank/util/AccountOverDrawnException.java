package com.cx.bank.util;

/**
 * @ClassName AccountOverDrawnException
 * @Description 取款超出余额异常
 * @Author Bruce Xu
 * @Date 2021/7/14 16:55
 * @Version 1.0
 */
public class AccountOverDrawnException extends Exception {
    public AccountOverDrawnException() {
    }
    public AccountOverDrawnException(String message){
        super(message);
    }
}
