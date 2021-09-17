package com.cx.bank.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cx.bank.entity.UserHeaderEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName UserHeaderDao
 * @Description 用户头像持久层
 * @Author Bruce Xu
 * @Date 2021/9/14 14:48
 * @Version 1.0
 */
@Mapper
public interface UserHeaderDao extends BaseMapper<UserHeaderEntity> {
}
