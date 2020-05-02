package com.gyj.gx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gyj.gx.dao.UserContestMapper;
import com.gyj.gx.domain.UserContestEntity;
import com.gyj.gx.service.UserContestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserContestServiceImpl extends ServiceImpl<UserContestMapper, UserContestEntity> implements UserContestService {

}
