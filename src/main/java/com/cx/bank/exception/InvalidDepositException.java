package com.cx.bank.exception;

/**
 * @ClassName InvalidDepositException
 * @Description 存款为负数异常
 * @Author Bruce Xu
 * @Date 2021/7/14 16:28
 * @Version 1.4
 */
public class InvalidDepositException extends Exception {
    public InvalidDepositException() {
        super();
    }

    public InvalidDepositException(String message) {
        super(message);
    }
}
