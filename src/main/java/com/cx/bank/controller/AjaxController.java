package com.cx.bank.controller;

import com.cx.bank.entity.UserEntity;
import com.cx.bank.entity.UserHeaderEntity;
import com.cx.bank.exception.UserNameExistedException;
import com.cx.bank.exception.UserNotExistException;
import com.cx.bank.exception.WrongPasswordException;
import com.cx.bank.service.LogService;
import com.cx.bank.service.UserService;
import com.cx.bank.util.MD5;
import com.cx.bank.util.PageUtils;
import com.cx.bank.util.R;
import com.cx.bank.vo.PasswordVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Objects;

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

    final
    MD5 md5;

    public AjaxController(UserService userService, LogService logService, MD5 md5) {
        this.userService = userService;
        this.logService = logService;
        this.md5 = md5;
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

    @GetMapping("/getHead")
    public R getHead(HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("user");
        UserHeaderEntity userHeaderEntity = userService.getHeaderInfo(user);
        return R.ok().put("data", userHeaderEntity);
    }

    @GetMapping("/Info")
    public R getInfo(String username) {
        UserEntity user = userService.getDetailInfo(username);
        return R.ok().put("data", user);
    }

    @PostMapping("/password")
    public R changePassword(PasswordVo passwordVo, HttpSession session) throws WrongPasswordException {
        if (session.isNew()) {
            return R.error("请先登录！");
        } else {
            UserEntity user = (UserEntity) session.getAttribute("user");
            String username = user.getUsername();

            if (!Objects.equals(passwordVo.getNewPassword(), passwordVo.getNewPassword2())) {
                return R.error("密码不一致！");
            } else {
                passwordVo.setOldPassword(md5.getMD5(passwordVo.getOldPassword()));
                passwordVo.setNewPassword2(md5.getMD5(passwordVo.getNewPassword2()));
                userService.changePassword(username, passwordVo);
                return R.ok("修改成功！");
            }
        }
    }
}
