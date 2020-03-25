package com.gyj.gx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gyj.gx.base.exception.BusinessException;
import com.gyj.gx.base.returns.RespCode;
import com.gyj.gx.base.util.validator.FirstValidator;
import com.gyj.gx.base.util.validator.ValidatorBeanFactory;
import com.gyj.gx.dao.UserMapper;
import com.gyj.gx.domain.UserEntity;
import com.gyj.gx.domain.request.UserVO;
import com.gyj.gx.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserEntity getUserByUsername(String username) {
        UserEntity userEntity = getOne(
                new QueryWrapper<UserEntity>().lambda()
                .eq(UserEntity::getUsername,username)
        );

        return userEntity;
    }

    @Override
    public boolean register(UserVO userVO) {
        ValidatorBeanFactory.validate(userVO, FirstValidator.class);

        UserEntity userEntity = getOne(
                new QueryWrapper<UserEntity>().lambda()
                .eq(UserEntity::getUsername,userVO.getUsername())
        );
        if (userEntity!=null)
            throw new BusinessException(RespCode.CUSTOM_ERROR,"存在重复的用户名");

        UserEntity newUser = new UserEntity();
        newUser.setUsername(userVO.getUsername());
        newUser.setPassword(bCryptPasswordEncoder.encode(userVO.getPassword()));

        return save(newUser);
    }
}
