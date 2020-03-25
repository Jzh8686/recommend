package com.gyj.gx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gyj.gx.domain.UserEntity;
import com.gyj.gx.domain.request.UserVO;

public interface UserService extends IService<UserEntity> {

    UserEntity getUserByUsername(String username);

    boolean register(UserVO userVO);
}
