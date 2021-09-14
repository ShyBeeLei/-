package com.cx.bank.controller;

import com.cx.bank.exception.UserNameExistedException;
import com.cx.bank.exception.UserNotExistException;
import com.cx.bank.service.LogService;
import com.cx.bank.service.UserService;
import com.cx.bank.util.PageUtils;
import com.cx.bank.util.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName AjaxController
 * @Description
 * @Author Bruce Xu
 * @Date 2021/9/9 18:58
 * @Version 1.0
 */
@RestController
@RequestMapping("ajax")
public class AjaxController {
    final
    UserService userService;

    final
    LogService logService;

    public AjaxController(UserService userService, LogService logService) {
        this.userService = userService;
        this.logService = logService;
    }

    @GetMapping("/verifyUsername")
    public R verifyUsername(String username) throws UserNameExistedException, UserNotExistException {
        userService.verifyUserName(username);
        return R.ok();
    }

    @RequestMapping("/getUsers")
    public R getUsers(String key) {
        PageUtils users = userService.getUsers(key);
        return R.ok().put("data", users.getList()).put("count", users.getTotalCount());
    }

    @RequestMapping("/getLogs")
    public R getLogs(String key) {
        PageUtils logs = logService.getLogs(key);
        return R.ok().put("data", logs.getList()).put("count", logs.getTotalCount());
    }
}
