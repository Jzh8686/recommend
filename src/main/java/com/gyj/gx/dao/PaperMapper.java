package com.gyj.gx.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gyj.gx.domain.PaperEntity;
import com.gyj.gx.domain.request.PaperVO;
import com.gyj.gx.domain.response.ProblemDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper

public interface PaperMapper extends BaseMapper<PaperEntity> {
    List<ProblemDTO> getProblemRelatedToPaper(@Param("map") PaperVO paperVO);//试卷题目列表


}
