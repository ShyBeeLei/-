package com.cx.bank.controller;

import com.cx.bank.constant.BankConstant;
import com.cx.bank.entity.UserEntity;
import com.cx.bank.exception.AccountOverDrawnException;
import com.cx.bank.exception.InvalidDepositException;
import com.cx.bank.exception.UserNotExistException;
import com.cx.bank.exception.WrongPasswordException;
import com.cx.bank.service.Impl.ManagerServiceImpl;
import com.cx.bank.service.ManagerService;
import com.cx.bank.util.MD5;
import com.cx.bank.util.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @ClassName ManagerController
 * @Description 业务逻辑控制层
 * @Author Bruce Xu
 * @Date 2021/8/22 14:41
 * @Version 1.0
 */
@RestController
@RequestMapping("SoulBank")
public class ManagerController {
    final
    ManagerService manager;

    final
    MD5 md5;

    public ManagerController(ManagerServiceImpl manager, MD5 md5) {
        this.manager = manager;
        this.md5 = md5;
    }

    @GetMapping(value = "/inquiry")
    public R inquiry(UserEntity user, HttpSession session) throws WrongPasswordException {
        UserEntity userEntity = (UserEntity) session.getAttribute("user");
        if (userEntity.getPassword().equals(md5.getMD5(user.getPassword()))) {
            double balance = manager.inquiry(userEntity);
            return R.ok().put("data", balance);
        } else {
            throw new WrongPasswordException();
        }
    }

    @PostMapping(value = "/deposit")
    public R deposit(double amount, HttpSession session) throws InvalidDepositException {
        UserEntity userEntity = (UserEntity) session.getAttribute("user");
        manager.deposit(userEntity, amount);
        return R.ok().put("msg", "存款成功！");

    }

    @PostMapping(value = "/withdrawals")
    public R withdrawals(UserEntity user, double amount, HttpSession session) throws InvalidDepositException, AccountOverDrawnException, WrongPasswordException {
        UserEntity userEntity = (UserEntity) session.getAttribute("user");
        if (userEntity.getPassword().equals(md5.getMD5(user.getPassword()))) {
            manager.withdrawals(userEntity, amount);
            return R.ok().put("msg", "取款成功！");
        } else {
            throw new WrongPasswordException();
        }
    }

    @PostMapping(value = "/transfer")
    public R transfer(String targetUser, UserEntity user, double amount, HttpSession session) throws InvalidDepositException, AccountOverDrawnException, WrongPasswordException, UserNotExistException {
        UserEntity userEntity = (UserEntity) session.getAttribute("user");
        if (userEntity.getPassword().equals(md5.getMD5(user.getPassword()))) {
            manager.transfer(userEntity, targetUser, amount);
            return R.ok().put("msg", "转账成功！");
        } else {
            throw new WrongPasswordException();
        }
    }

    @PostMapping(value = "/changeStatus")
    public R changeStatus(UserEntity user) {
        manager.changeStatus(user);
        if (user.getStatus() == BankConstant.status.ACTIVE.getCode()) {
            return R.ok().put("msg", "冻结成功！");
        } else {
            return R.ok().put("msg", "激活成功！");
        }
    }
}
