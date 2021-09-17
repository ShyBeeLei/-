package com.cx.bank.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cx.bank.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName UserDao
 * @Description
 * @Author Bruce Xu
 * @Date 2021/9/13 9:53
 * @Version 1.0
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
    /**
     * 获取余额
     *
     * @param username 目标用户名
     * @return 获取到的余额
     */
    double getBalance(@Param("username") String username);

    /**
     * 更新余额
     *
     * @param username 目标用户名
     * @param amount   要更新的数额
     */
    void updateBalance(@Param("username") String username, @Param("amount") double amount);

    /**
     * 更新用户状态
     *
     * @param username 目标用户名
     * @param code     要更新到的状态
     */
    void updateStatus(@Param("username") String username, @Param("code") int code);

    /**
     * 更新密码
     *
     * @param username     目标用户名
     * @param newPassword2 新密码
     */
    void updatePassword(@Param("username") String username, @Param("newPassword2") String newPassword2);
}
