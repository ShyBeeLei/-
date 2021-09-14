package com.cx.bank.exception;

/**
 * @ClassName UserFrozenException
 * @Description
 * @Author Bruce Xu
 * @Date 2021/9/9 20:28
 * @Version 1.0
 */
public class UserFrozenException extends Exception {
    public UserFrozenException() {
    }

    public UserFrozenException(String message) {
        super(message);
    }
}
