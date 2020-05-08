package com.gyj.gx.controller;

import com.gyj.gx.base.returns.RespCode;
import com.gyj.gx.base.returns.RespEntity;
import com.gyj.gx.service.UserDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("recommend")
public class RecommendController {
    @Autowired
    UserDataService userDataService;
    @GetMapping("result")
    public RespEntity getRecommend(@RequestParam("userId") Long userId){
        return new RespEntity(RespCode.SUCCESS,userDataService.generateRecommend(userId));
    }
}
