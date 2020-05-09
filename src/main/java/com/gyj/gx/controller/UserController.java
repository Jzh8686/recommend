package com.gyj.gx.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gyj.gx.base.page.PageModule;
import com.gyj.gx.base.returns.RespCode;
import com.gyj.gx.base.returns.RespEntity;
import com.gyj.gx.base.util.FileUtil;
import com.gyj.gx.domain.UserEntity;
import com.gyj.gx.domain.request.UserVO;
import com.gyj.gx.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("user")
public class UserController extends BaseController {
    @Value("${web.upload-path}")

    private String path;
    @Autowired
    private UserService userService;
    @PostMapping("upload")
    public RespEntity upload(@RequestParam("file")MultipartFile file,@RequestParam("userId")Long userId){
        Map<String,Object> map = new HashMap<>();
        String filename = FileUtil.upload(file,path,file.getOriginalFilename());
        QueryWrapper<UserEntity> searchWrapper = new QueryWrapper<>();
        searchWrapper.eq("user_id",userId);
        UserEntity userEntity = userService.getOne(searchWrapper);
        userEntity.setAvatar(filename);
        userService.update(userEntity,searchWrapper);
        return new RespEntity(RespCode.SUCCESS,filename);
    }
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
    @GetMapping("info")
    public RespEntity getInfo(@RequestParam("userId")Long userId){
        return new RespEntity(RespCode.SUCCESS,userService.getPeopleInfo(userId));
    }
    @PostMapping("updateInfo")
    public RespEntity updateInfo(@RequestBody() UserEntity userEntity){
        return new RespEntity(RespCode.SUCCESS,userService.updateInfo(userEntity));
    }
}
