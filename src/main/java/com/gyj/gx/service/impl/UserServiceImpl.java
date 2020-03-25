package com.gyj.gx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gyj.gx.dao.UserMapper;
import com.gyj.gx.domain.UserEntity;
import com.gyj.gx.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {


    @Override
    public UserEntity getUserByUsername(String username) {
        UserEntity userEntity = getOne(
                new QueryWrapper<UserEntity>().lambda()
                .eq(UserEntity::getUsername,username)
        );

        return userEntity;
    }
}
