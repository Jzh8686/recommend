package com.gyj.gx.controller;

import com.gyj.gx.base.returns.RespCode;
import com.gyj.gx.base.returns.RespEntity;
import com.gyj.gx.domain.request.UserVO;
import com.gyj.gx.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 注册
     * @param userVO
     * @return
     */
    @PostMapping("register")
    public RespEntity register(@RequestBody UserVO userVO) {
        return new RespEntity(RespCode.SUCCESS, userService.register(userVO));
    }
}
