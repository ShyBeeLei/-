
package com.cx.bank.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cx.bank.constant.BankConstant;
import com.cx.bank.dao.UserDao;
import com.cx.bank.entity.UserEntity;
import com.cx.bank.exception.AccountOverDrawnException;
import com.cx.bank.exception.InvalidDepositException;
import com.cx.bank.exception.UserNameExistedException;
import com.cx.bank.exception.UserNotExistException;
import com.cx.bank.service.ManagerService;
import com.cx.bank.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName ManagerImpl
 * @Description 调用持久层方法实现业务
 * @Author Bruce Xu
 * @Date 2021/7/14 9:58
 * @Version 1.9
 */
@SuppressWarnings("ALL")
@Service
public class ManagerServiceImpl extends ServiceImpl<UserDao, UserEntity> implements ManagerService {
    final
    UserService userService;

    public ManagerServiceImpl(UserService userService) {
        this.userService = userService;
    }

    /**
     * 查询账户余额
     *
     * @param userEntity 用户实体
     * @return 对象钱包的余额
     */
    @Override
    public double inquiry(UserEntity userEntity) {
        double balance = this.baseMapper.getBalance(userEntity.getUsername());
        return balance;
    }

    /**
     * 存款方法
     *
     * @param user   用户对象
     * @param amount 存款金额
     * @throws InvalidDepositException 无效金额异常
     */
    @Transactional
    @Override
    public void deposit(UserEntity user, double amount) throws InvalidDepositException {
        if (amount <= 0) {
            throw new InvalidDepositException();
        }
        this.baseMapper.updateBalance(user.getUsername(), amount);
    }

    /**
     * 取款方法
     *
     * @param user   用户对象
     * @param amount 取款金额
     * @throws AccountOverDrawnException 余额不足异常
     * @throws InvalidDepositException   无效金额异常
     */
    @Transactional
    @Override
    public void withdrawals(UserEntity user, double amount) throws AccountOverDrawnException, InvalidDepositException {
        UserEntity userEntity = this.getOne(new QueryWrapper<UserEntity>().eq("username", user.getUsername()));
        if (amount <= 0) {
            throw new InvalidDepositException();
        } else if (userEntity.getBalance() < amount) {
            throw new AccountOverDrawnException();
        } else {
            this.baseMapper.updateBalance(user.getUsername(), -amount);
        }
    }

    /**
     * 转账功能
     *
     * @param user   用户对象
     * @param name   收款用户名
     * @param amount 转账金额
     * @throws AccountOverDrawnException 余额不足异常
     * @throws InvalidDepositException   无效金额异常
     */
    @Transactional
    @Override
    public void transfer(UserEntity user, String name, double amount) throws InvalidDepositException, AccountOverDrawnException, UserNotExistException {
        try {
            userService.verifyUserName(name);
        } catch (UserNameExistedException e) {
            UserEntity userEntity = baseMapper.selectOne(new QueryWrapper<UserEntity>().eq("username", user.getUsername()));
            if (amount <= 0) {
                throw new InvalidDepositException();
            }
            if (userEntity.getBalance() < amount) {
                throw new AccountOverDrawnException();
            }
            this.baseMapper.updateBalance(user.getUsername(), -amount);
            this.baseMapper.updateBalance(name, amount);
        }
    }

    @Transactional
    @Override
    public void changeStatus(UserEntity user) {
        Integer status = user.getStatus();
        if (status == BankConstant.status.FROZEN.getCode()) {
            this.baseMapper.updateStatus(user.getUsername(), BankConstant.status.ACTIVE.getCode());
        } else {
            this.baseMapper.updateStatus(user.getUsername(), BankConstant.status.FROZEN.getCode());
        }
    }
}
