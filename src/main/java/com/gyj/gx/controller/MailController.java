package com.gyj.gx.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gyj.gx.base.util.RedisUtils;
import com.gyj.gx.base.util.UUIDUtils;
import com.gyj.gx.domain.UserEntity;
import com.gyj.gx.service.MailService;
import com.gyj.gx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
public class MailController {
    @Autowired
    private MailService mailService;

    @Autowired
    UserService userService;

    @Resource
    private RedisUtils redisUtils;

    @GetMapping("getCheckCode")
    public Map<String, Object> getCheckCode(Long userId, String email) {
        Map<String, Object> map = new HashMap<>();
        String msg;
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        UserEntity oUser = userService.getOne(queryWrapper);
        if (oUser == null)
            msg = "fail1";
        else if (oUser.getEmail() != null && !oUser.getEmail().equals("") && !oUser.getEmail().equals(email))
            msg = "fail2;";
        else {
            Long id = oUser.getUserId();
            String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
            String message = "您的验证码为：" + checkCode;
            try {
                mailService.sendSimpleMail(email, "验证码", message);
                msg = "success";
            } catch (Exception e) {
                msg = "fail3";
                e.printStackTrace();
            }
            redisUtils.set(id + "", checkCode);
//            map.put("checkCode",checkCode);
        }
        map.put("msg", msg);
        return map;
    }


    /* @PostMapping("validateCheckCode")
     public String validateCheckCode(int id,String email,String checkCode){

         String msg =redisUtils.get(id+"").equals(checkCode.trim())?"success":"fail";
         if (msg.equals("success")){
             User user=userRepository.findById(id).get();
             user.setEmail(email);
             userRepository.save(user);
         }
         return msg;
     }*/
    @PostMapping("validateCheckCode")
    public Map<String, Object> validateCheckCode(Long userId, String email, String checkCode) {
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        UserEntity oUser = userService.getOne(queryWrapper);
        long id = oUser.getUserId();
        String msg = redisUtils.get(id + "").equals(checkCode.trim()) ? "success" : "fail";
        redisUtils.delete(id + "");
        if (msg.equals("success")) {
            oUser.setEmail(email);
            userService.update(oUser,queryWrapper);
        }
        map.put("msg", msg);
        return map;
    }

    @PostMapping("resetPassword")
    public Map<String, Object> resetPassword(Long userId, String checkCode) {
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        UserEntity oUser = userService.getOne(queryWrapper);
        Long id = oUser.getUserId();
        String msg = redisUtils.get(id + "").equals(checkCode.trim()) ? "success" : "fail";
        redisUtils.delete(id + "");
        if (msg.equals("success")) {
            String newPassword = UUIDUtils.getUUID();
            oUser.setPassword(new BCryptPasswordEncoder().encode(newPassword));
            userService.update(oUser,queryWrapper);
            map.put("newPassword", newPassword);
        }
        map.put("msg", msg);
        return map;
    }

}
