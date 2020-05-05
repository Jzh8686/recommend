package com.gyj.gx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gyj.gx.base.util.validator.ThirdValidator;
import com.gyj.gx.base.util.validator.ValidatorBeanFactory;
import com.gyj.gx.dao.LikeMapper;
import com.gyj.gx.domain.LikeEntity;
import com.gyj.gx.domain.request.CommentVO;
import com.gyj.gx.service.LikeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LikeServiceImpl extends ServiceImpl<LikeMapper, LikeEntity> implements LikeService {

}
