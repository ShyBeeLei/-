package com.cx.bank.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cx.bank.dao.LogDao;
import com.cx.bank.entity.LogEntity;
import com.cx.bank.entity.UserEntity;
import com.cx.bank.service.LogService;
import com.cx.bank.service.ManagerService;
import com.cx.bank.util.PageUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @ClassName LogServiceImpl
 * @Description 记录和查询日志
 * @Author Bruce Xu
 * @Date 2021/9/13 13:44
 * @Version 1.0
 */
@Aspect
@Component
public class LogServiceImpl extends ServiceImpl<LogDao, LogEntity> implements LogService {

    final
    ManagerService managerService;

    public LogServiceImpl(ManagerService managerService) {
        this.managerService = managerService;
    }

    @AfterReturning(value = "execution(* com.cx.bank.service.Impl.ManagerServiceImpl.*(com.cx.bank.entity.UserEntity,double ,..))&&args (user,amount) ",
            argNames = "point,user,amount")
    @Override
    public void log(JoinPoint point, UserEntity user, double amount) {
        LogEntity logEntity = new LogEntity();
        logEntity.setDate(new Date());
        UserEntity userEntity = managerService.getOne(new QueryWrapper<UserEntity>().eq("username", user.getUsername()));
        logEntity.setUserId(userEntity.getId());
        String type = point.getSignature().getName();
        logEntity.setType(type);
        logEntity.setAmount(amount);
        this.save(logEntity);
    }

    @AfterReturning(value = "execution(* com.cx.bank.service.Impl.ManagerServiceImpl.*(com.cx.bank.entity.UserEntity,String,double ,..))&&args (user,name,amount) ", argNames = "point,user,name,amount")
    @Override
    public void logTransfer(JoinPoint point, UserEntity user, String name, double amount) {
        LogEntity logEntity = new LogEntity();
        logEntity.setDate(new Date());
        UserEntity userEntity = managerService.getOne(new QueryWrapper<UserEntity>().eq("username", user.getUsername()));
        logEntity.setUserId(userEntity.getId());
        String type = point.getSignature().getName();
        if ("transfer".equals(type)) {
            type = type + " to " + name;
        }
        logEntity.setType(type);
        logEntity.setAmount(amount);
        this.save(logEntity);
    }

    @Override
    public PageUtils getLogs(String key) {
        QueryWrapper<LogEntity> wrapper = new QueryWrapper<>();
        if (StringUtils.hasLength(key)) {
            wrapper.and(w -> {
                w.eq("id", key).or().like("type", key).or().eq("user_id", key);
            });
        }
        IPage<LogEntity> page = this.page(new Page<>(), wrapper);
        return new PageUtils(page);
    }


}
