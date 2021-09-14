package com.cx.bank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName DefaultController
 * @Description 默认跳转
 * @Author Bruce Xu
 * @Date 2021/9/9 23:04
 * @Version 1.0
 */
@Controller
@RequestMapping("SoulBank")
public class DefaultController {

    @RequestMapping("/MainMenu")
    public String toInquiry() {
        return "Bank/MainMenu";
    }

    @RequestMapping("/Deposit")
    public String toDeposit() {
        return "Bank/Deposit";
    }

    @RequestMapping("/Transfer")
    public String toTransfer() {
        return "Bank/Transfer";
    }

    @RequestMapping("/Withdrawal")
    public String toWithdrawal() {
        return "Bank/Withdrawal";
    }

    @RequestMapping("/AdminMenu")
    public String toAdminMenu() {
        return "Admin/AdminMenu";
    }

    @RequestMapping("/Logs")
    public String toLogMenu() {
        return "Admin/Logs";
    }
}
