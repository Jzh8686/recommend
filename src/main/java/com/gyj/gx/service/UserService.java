package com.gyj.gx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gyj.gx.base.page.PageModule;
import com.gyj.gx.domain.UserEntity;
import com.gyj.gx.domain.request.PaperVO;
import com.gyj.gx.domain.request.UserVO;
import com.gyj.gx.domain.response.UserDTO;

public interface UserService extends IService<UserEntity> {

    UserEntity getUserByUsername(String username);

    boolean register(UserVO userVO);

    PageModule<UserDTO> getPageList(PageModule pageModule, UserVO userVO);

    boolean adminRegister(UserVO userVO);
}
