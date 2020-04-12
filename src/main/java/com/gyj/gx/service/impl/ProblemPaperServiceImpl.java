package com.gyj.gx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gyj.gx.dao.ProblemPaperMapper;
import com.gyj.gx.domain.ProblemPaperEntity;
import com.gyj.gx.service.ProblemPaperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProblemPaperServiceImpl extends ServiceImpl<ProblemPaperMapper, ProblemPaperEntity>implements ProblemPaperService {
}
