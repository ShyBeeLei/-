package com.cx.bank.exception;

/**
 * @ClassName UserUnthorizedException
 * @Description
 * @Author Bruce Xu
 * @Date 2021/9/9 20:34
 * @Version 1.0
 */
public class UserUnauthorizedException extends Exception {
    public UserUnauthorizedException() {
    }

    public UserUnauthorizedException(String message) {
        super(message);
    }
}
