package com.gyj.gx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gyj.gx.dao.CategoryProblemMapper;
import com.gyj.gx.domain.CategoryProblemEntity;
import com.gyj.gx.service.CategoryProblemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CategoryProblemServiceImpl extends ServiceImpl<CategoryProblemMapper, CategoryProblemEntity> implements CategoryProblemService {
}
