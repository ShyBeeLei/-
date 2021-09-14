package com.cx.bank.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cx.bank.entity.LogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName LogDao
 * @Description
 * @Author Bruce Xu
 * @Date 2021/9/13 10:26
 * @Version 1.0
 */
@Mapper
public interface LogDao extends BaseMapper<LogEntity> {
}
