package com.cx.bank.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName Log
 * @Description 日志持久类
 * @Author Bruce Xu
 * @Date 2021/8/25 19:13
 * @Version 1.0
 */
@TableName("t_log")
@Data
public class LogEntity {
    /**
     * 日志标识
     */
    @TableId
    private Long id;
    /**
     * 日志操作类型
     */
    private String type;
    /**
     * 日志操作的金额
     */
    private double amount;
    /**
     * 日志日期
     */
    private Date date;
    /**
     * 日志所属用户
     */
    private Long userId;
}
