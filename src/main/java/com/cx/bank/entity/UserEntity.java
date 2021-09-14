package com.cx.bank.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Repository;

/**
 * @ClassName User
 * @Description 用户持久类
 * @Author Bruce Xu
 * @Date 2021/8/25 19:15
 * @Version 1.0
 */
@TableName("t_user")
@Data
@Repository
public class UserEntity {
    /**
     * 用户的id
     */
    @TableId
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户身份【0-管理员，1-普通用户】
     */
    private Integer identity;
    /**
     * 用户状态【0-激活状态，1-冻结状态】
     */
    private Integer status;
    /**
     * 用户余额
     */
    private double balance;
}
