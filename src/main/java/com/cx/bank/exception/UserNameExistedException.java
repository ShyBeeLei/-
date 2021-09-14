package com.cx.bank.exception;

/**
 * @ClassName UserNameExistedException
 * @Description 用户名已存在异常
 * @Author Bruce Xu
 * @Date 2021/9/9 21:08
 * @Version 1.0
 */
public class UserNameExistedException extends Exception {
    public UserNameExistedException() {
    }

    public UserNameExistedException(String message) {
        super(message);
    }
}
