package com.cx.bank.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cx.bank.constant.BankConstant;
import com.cx.bank.dao.UserDao;
import com.cx.bank.dao.UserHeaderDao;
import com.cx.bank.entity.UserEntity;
import com.cx.bank.entity.UserHeaderEntity;
import com.cx.bank.exception.*;
import com.cx.bank.service.UserService;
import com.cx.bank.util.PageUtils;
import com.cx.bank.vo.PasswordVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @ClassName LoginServiceImpl
 * @Description
 * @Author Bruce Xu
 * @Date 2021/9/9 20:19
 * @Version 1.0
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {
    final
    UserHeaderDao headerDao;

    public UserServiceImpl(UserHeaderDao headerDao) {
        this.headerDao = headerDao;
    }

    /**
     * 处理登陆业务
     *
     * @param user 用户实体
     * @throws WrongPasswordException    账号密码错误异常
     * @throws UserFrozenException       用户被冻结异常
     * @throws UserUnauthorizedException 用户权限不足异常
     */
    @Override
    public void login(UserEntity user) throws WrongPasswordException, UserFrozenException, UserUnauthorizedException {
        UserEntity userEntity = this.baseMapper.selectOne(new QueryWrapper<UserEntity>().eq("password", user.getPassword()).eq("username", user.getUsername()));
        if (userEntity == null) {
            throw new WrongPasswordException();
        } else if (userEntity.getStatus() == BankConstant.status.FROZEN.getCode()) {
            throw new UserFrozenException();
        } else if (user.getIdentity() == BankConstant.Identity.ADMIN.getCode() && !user.getIdentity().equals(userEntity.getIdentity())) {
            throw new UserUnauthorizedException();
        }
    }

    /**
     * 处理注册业务
     *
     * @param user 用户实体
     * @throws UserNameExistedException 用户名已存在异常
     */
    @Override
    public void register(UserEntity user) throws UserNameExistedException {
        try {
            verifyUserName(user.getUsername());
        } catch (UserNotExistException e) {
            user.setIdentity(BankConstant.Identity.USER.getCode());
            user.setStatus(BankConstant.status.ACTIVE.getCode());
            this.save(user);
        }
    }

    @Override
    public void verifyUserName(String userName) throws UserNameExistedException, UserNotExistException {
        UserEntity userEntity = this.getOne(new QueryWrapper<UserEntity>().eq("username", userName));
        if (userEntity != null) {
            throw new UserNameExistedException();
        } else {
            throw new UserNotExistException();
        }
    }

    @Override
    public PageUtils getUsers(String key) {
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        if (StringUtils.hasLength(key)) {
            wrapper.and(w -> {
                w.eq("id", key).or().like("username", key);
            });
        }
        IPage<UserEntity> page = this.page(new Page<>(), wrapper);
        return new PageUtils(page);
    }

    @Override
    public UserHeaderEntity getHeaderInfo(UserEntity user) {
        UserHeaderEntity headerEntity = headerDao.selectOne(new QueryWrapper<UserHeaderEntity>().eq("user_name", user.getUsername()));
        return headerEntity;
    }

    @Override
    public UserEntity getDetailInfo(String username) {
        UserEntity user = baseMapper.selectOne(new QueryWrapper<UserEntity>().eq("username", username));
        return user;
    }

    @Override
    public void changePassword(String username, PasswordVo passwordVo) throws WrongPasswordException {
        UserEntity userEntity = baseMapper.selectOne(new QueryWrapper<UserEntity>().eq("username", username).eq("password", passwordVo.getOldPassword()));
        if (userEntity != null) {
            baseMapper.updatePassword(username, passwordVo.getNewPassword2());
        } else {
            throw new WrongPasswordException();
        }
    }
}
