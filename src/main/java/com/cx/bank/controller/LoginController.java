
package com.cx.bank.controller;

import com.cx.bank.entity.UserEntity;
import com.cx.bank.exception.UserFrozenException;
import com.cx.bank.exception.UserNameExistedException;
import com.cx.bank.exception.UserUnauthorizedException;
import com.cx.bank.exception.WrongPasswordException;
import com.cx.bank.service.UserService;
import com.cx.bank.util.MD5;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @ClassName LoginController
 * @Description 控制登陆动作
 * @Author Bruce Xu
 * @Date 2021/8/18 20:02
 * @Version 1.0
 */
@Controller
public class LoginController {
    final
    UserService userService;
    final
    MD5 md5;

    public LoginController(UserService userService, MD5 md5) {
        this.userService = userService;
        this.md5 = md5;
    }


    @RequestMapping(value = {"/", "/login"})
    public String login(HttpSession session) {
        session.invalidate();
        return "Login";
    }

    @GetMapping(value = {"/register"})
    public String register() {
        return "Register";
    }

    @PostMapping(value = "/MainMenu")
    public String login(UserEntity user, HttpSession session) throws UserFrozenException, UserUnauthorizedException, WrongPasswordException {
        String md5 = this.md5.getMD5(user.getPassword());
        user.setPassword(md5);
        session.setAttribute("user", user);
        userService.login(user);
        if (user.getIdentity() == 1) {
            return "redirect:SoulBank/MainMenu";
        }
        return "redirect:SoulBank/AdminMenu";
    }

    @PutMapping(value = "/MainMenu")
    public String register(UserEntity user, HttpSession session) throws UserNameExistedException {
        String md5 = this.md5.getMD5(user.getPassword());
        user.setPassword(md5);
        session.setAttribute("user", user);
        userService.register(user);
        return "redirect:/SoulBank/MainMenu";
    }
}
