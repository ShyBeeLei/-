package com.cx.bank.vo;

import lombok.Data;

/**
 * @ClassName PasswordVo
 * @Description 密码数据接收类
 * @Author Bruce Xu
 * @Date 2021/9/16 10:58
 * @Version 1.0
 */
@Data
public class PasswordVo {
    /**
     * 旧密码
     */
    String oldPassword;
    /**
     * 新密码1
     */
    String newPassword;
    /**
     * 新密码2
     */
    String newPassword2;
}
