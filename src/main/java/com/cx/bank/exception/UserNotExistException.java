package com.cx.bank.exception;

/**
 * @ClassName UserNotExisitedException
 * @Description 用户不存在异常
 * @Author Bruce Xu
 * @Date 2021/9/9 22:24
 * @Version 1.0
 */
public class UserNotExistException extends Exception {
    public UserNotExistException() {
        super(BizCodeEnume.USER_NOT_EXIST_EXCEPTION.getMsg());
    }

    public UserNotExistException(String message) {
        super(message);
    }
}
