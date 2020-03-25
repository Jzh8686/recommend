package com.gyj.gx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gyj.gx.domain.UserEntity;

public interface UserService extends IService<UserEntity> {

    UserEntity getUserByUsername(String username);

}
