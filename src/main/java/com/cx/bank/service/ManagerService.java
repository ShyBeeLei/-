package com.cx.bank.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cx.bank.entity.UserEntity;
import com.cx.bank.exception.AccountOverDrawnException;
import com.cx.bank.exception.InvalidDepositException;
import com.cx.bank.exception.UserNotExistException;

/**
 * @ClassName ManagerInterface
 * @Description 声明业务层方法
 * @Author Bruce Xu
 * @Date 2021/7/14 10:00
 * @Version 1.9
 */
public interface ManagerService extends IService<UserEntity> {
    /**
     * 查询账户余额
     *
     * @param userEntity 用户实体
     * @return 对象钱包的余额
     */
    double inquiry(UserEntity userEntity);

    /**
     * 存款方法
     *
     * @param amount 存款金额
     * @param user   用户对象
     * @throws InvalidDepositException 无效金额异常
     */
    void deposit(UserEntity user, double amount) throws InvalidDepositException;

    /**
     * 取款方法
     *
     * @param amount 取款金额
     * @param user   用户对象
     * @throws AccountOverDrawnException 余额不足异常
     * @throws InvalidDepositException   无效金额异常
     */
    void withdrawals(UserEntity user, double amount) throws AccountOverDrawnException, InvalidDepositException;

    /**
     * 转账功能
     *
     * @param user   用户对象
     * @param name   收款用户名
     * @param amount 转账金额
     * @throws AccountOverDrawnException 余额不足异常
     * @throws InvalidDepositException   无效金额异常
     * @throws UserNotExistException     用户不存在异常
     */
    void transfer(UserEntity user, String name, double amount) throws InvalidDepositException, AccountOverDrawnException, UserNotExistException;

    /**
     * 改变用户状态
     *
     * @param user 用户对象
     */
    void changeStatus(UserEntity user);
}
