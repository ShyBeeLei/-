package com.cx.bank.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cx.bank.entity.UserEntity;
import com.cx.bank.entity.UserHeaderEntity;
import com.cx.bank.exception.*;
import com.cx.bank.util.PageUtils;
import com.cx.bank.vo.PasswordVo;

/**
 * @ClassName LoginService
 * @Description 处理登陆与注册业务
 * @Author Bruce Xu
 * @Date 2021/9/9 20:19
 * @Version 1.0
 */
public interface UserService extends IService<UserEntity> {
    /**
     * 处理登陆业务
     *
     * @param user 用户实体
     * @throws WrongPasswordException    账号密码错误异常
     * @throws UserFrozenException       用户被冻结异常
     * @throws UserUnauthorizedException 用户权限不足异常
     */
    void login(UserEntity user) throws WrongPasswordException, UserFrozenException, UserUnauthorizedException;

    /**
     * 处理注册业务
     *
     * @param user 用户实体
     * @throws UserNameExistedException 用户名已存在异常
     */
    void register(UserEntity user) throws UserNameExistedException;

    /**
     * 验证用户名是否存在
     *
     * @param userName 目标用户名
     * @throws UserNameExistedException 用户名已存在异常
     * @throws UserNotExistException    用户名不存在异常
     */
    void verifyUserName(String userName) throws UserNameExistedException, UserNotExistException;

    /**
     * 获取所有用户
     *
     * @param key 搜索的关键字
     * @return 用户表
     */
    PageUtils getUsers(String key);

    /**
     * 获取用户头像与用户名
     *
     * @param user 用户实体
     * @return 用户头像对象
     */
    UserHeaderEntity getHeaderInfo(UserEntity user);

    /**
     * 获取用户详细信息
     *
     * @param username 用户名
     * @return 用户实体
     */
    UserEntity getDetailInfo(String username);

    /**
     * 修改成功方法
     *
     * @param username   用户名
     * @param passwordVo 密码Vo类
     */
    void changePassword(String username, PasswordVo passwordVo) throws WrongPasswordException;
}
