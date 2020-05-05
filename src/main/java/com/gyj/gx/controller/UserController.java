package com.gyj.gx.controller;

import com.gyj.gx.base.page.PageModule;
import com.gyj.gx.base.returns.RespCode;
import com.gyj.gx.base.returns.RespEntity;
import com.gyj.gx.domain.request.UserVO;
import com.gyj.gx.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    /**
     * 带分页用户列表
     * @param pageModule
     * @param userVO
     * @return
     */
    @GetMapping("page")
    public RespEntity getPageList(PageModule pageModule, UserVO userVO){
        return new RespEntity(RespCode.SUCCESS, userService.getPageList(pageModule, userVO));
    }
    /**
     * 管理员添加账号
     * @param userVO
     * @return
     */
    @PostMapping("role")
    public RespEntity updateRole(@RequestBody UserVO userVO) {
        return new RespEntity(RespCode.SUCCESS, userService.updateRole(userVO));
    }
    /**
     * 修改状态
     * @param userVO
     * @return
     */
    @PostMapping("state")
    public RespEntity alterState(@RequestBody UserVO userVO) {
        return new RespEntity(RespCode.SUCCESS, userService.alterState(userVO));
    }
}
