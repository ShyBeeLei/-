package com.cx.bank.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cx.bank.entity.LogEntity;
import com.cx.bank.entity.UserEntity;
import com.cx.bank.util.PageUtils;
import org.aspectj.lang.JoinPoint;

/**
 * @ClassName LogService
 * @Description
 * @Author Bruce Xu
 * @Date 2021/9/13 13:43
 * @Version 1.0
 */
public interface LogService extends IService<LogEntity> {
    /**
     * 对金额操作功能进行日志记录
     *
     * @param point  连接点
     * @param user   操作用户
     * @param amount 操作金额
     */
    void log(JoinPoint point, UserEntity user, double amount);

    /**
     * 对有目标的金额操作功能进行日志记录
     *
     * @param point  连接点
     * @param user   操作用户
     * @param name   目标用户名
     * @param amount 操作金额
     */
    void logTransfer(JoinPoint point, UserEntity user, String name, double amount);

    /**
     * 管理员界面获取日志信息
     *
     * @param key 搜索关键字
     * @return 分好页的日志表
     */
    PageUtils getLogs(String key);
}
