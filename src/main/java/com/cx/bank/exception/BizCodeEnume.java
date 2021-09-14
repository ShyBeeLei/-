package com.cx.bank.exception;

/**
 * @ClassName BizCodeEnume
 * @Description 存放各个异常状态的枚举类
 * @Author Bruce Xu
 * @Date 2021/9/8 15:59
 * @Version 1.0
 */
public enum BizCodeEnume {
    /**
     * 当其他异常处理器均未捕获该异常时，使用此信息
     */
    UNKNOWN_EXCEPTION(10000, "系统未知异常"),
    /**
     * 余额不足异常
     */
    ACCOUNT_OVER_DRAWN_EXCEPTION(10001, "您的余额不足！"),
    /**
     * 数值错误异常
     */
    INVALID_DEPOSIT_EXCEPTION(10002, "请输入正确的数额！"),
    /**
     * 密码错误异常
     */
    WRONG_PASSWORD_EXCEPTION(20001, "密码错误！"),
    /**
     * 用户被冻结异常
     */
    USER_FROZEN_EXCEPTION(20002, "账户被冻结！"),
    /**
     * 用户权限不足异常
     */
    USER_UNAUTHORIZED_EXCEPTION(20003, "用户权限不足"),
    /**
     * 用户名已存在异常
     */
    USER_NAME_EXISTED_EXCEPTION(20004, "用户名已存在！"),
    /**
     * 用户不存在异常
     */
    USER_NOT_EXIST_EXCEPTION(20005, "用户不存在！");

    private int code;
    private String msg;

    BizCodeEnume(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
