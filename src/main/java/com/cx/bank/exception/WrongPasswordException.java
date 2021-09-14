package com.cx.bank.exception;

/**
 * @ClassName WrongPasswordException
 * @Description
 * @Author Bruce Xu
 * @Date 2021/9/9 19:35
 * @Version 1.0
 */
public class WrongPasswordException extends Exception {
    public WrongPasswordException() {
    }

    public WrongPasswordException(String message) {
        super(message);
    }
}
