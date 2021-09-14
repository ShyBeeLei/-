package com.cx.bank.exception;

import com.cx.bank.util.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName BankExceptionControllerAdvice
 * @Description
 * @Author Bruce Xu
 * @Date 2021/9/9 19:23
 * @Version 1.0
 */
@RestControllerAdvice(basePackages = "com.cx.bank.controller")
public class BankExceptionControllerAdvice {

    /**
     * 处理余额不足异常
     *
     * @param e 余额不足异常
     * @return 异常相关信息
     */
    @ExceptionHandler(value = AccountOverDrawnException.class)
    public R handleAccountOverDrawnException(AccountOverDrawnException e) {
        return R.error(BizCodeEnume.ACCOUNT_OVER_DRAWN_EXCEPTION.getCode(), BizCodeEnume.ACCOUNT_OVER_DRAWN_EXCEPTION.getMsg());
    }

    /**
     * 处理无效数值异常
     *
     * @param e 无效数值异常
     * @return 异常相关信息
     */
    @ExceptionHandler(value = InvalidDepositException.class)
    public R handleInvalidDepositException(InvalidDepositException e) {
        return R.error(BizCodeEnume.INVALID_DEPOSIT_EXCEPTION.getCode(), BizCodeEnume.INVALID_DEPOSIT_EXCEPTION.getMsg());
    }

    /**
     * 处理密码错误异常
     *
     * @param e 密码错误异常
     * @return 错误信息
     */
    @ExceptionHandler(value = WrongPasswordException.class)
    public R handleWrongPasswordException(WrongPasswordException e) {
        return R.error(BizCodeEnume.WRONG_PASSWORD_EXCEPTION.getCode(), BizCodeEnume.WRONG_PASSWORD_EXCEPTION.getMsg());
    }

    /**
     * 处理用户被冻结异常
     *
     * @param e 用户被冻结异常
     * @return 错误信息
     */
    @ExceptionHandler(value = UserFrozenException.class)
    public R handleUserFrozenException(UserFrozenException e) {
        return R.error(BizCodeEnume.USER_FROZEN_EXCEPTION.getCode(), BizCodeEnume.USER_FROZEN_EXCEPTION.getMsg());
    }

    /**
     * 处理用户权限不足异常
     *
     * @param e 用户权限不足异常
     * @return 错误信息
     */
    @ExceptionHandler(value = UserUnauthorizedException.class)
    public R handleUserUnauthorizedException(UserUnauthorizedException e) {
        return R.error(BizCodeEnume.USER_UNAUTHORIZED_EXCEPTION.getCode(), BizCodeEnume.USER_UNAUTHORIZED_EXCEPTION.getMsg());
    }

    /**
     * 处理用户名已存在异常
     *
     * @param e 用户名已存在异常
     * @return 错误信息
     */
    @ExceptionHandler(value = UserNameExistedException.class)
    public R handleUsernameExistedException(UserNameExistedException e) {
        return R.error(BizCodeEnume.USER_NAME_EXISTED_EXCEPTION.getCode(), BizCodeEnume.USER_NAME_EXISTED_EXCEPTION.getMsg());
    }

    /**
     * 处理用户不存在异常
     *
     * @param e 用户不存在异常
     * @return 错误信息
     */
    @ExceptionHandler(value = UserNotExistException.class)
    public R handleUserNotExistException(UserNotExistException e) {
        return R.error(BizCodeEnume.USER_NOT_EXIST_EXCEPTION.getCode(), BizCodeEnume.USER_NOT_EXIST_EXCEPTION.getMsg());
    }

    //@ExceptionHandler(value = Throwable.class)
    //public R handleException(Throwable throwable) {
    //    return R.error(BizCodeEnume.UNKNOWN_EXCEPTION.getCode(), BizCodeEnume.UNKNOWN_EXCEPTION.getMsg());
    //}
}
