package com.gyj.gx.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gyj.gx.domain.ProblemEntity;
import com.gyj.gx.domain.request.ProblemVO;
import com.gyj.gx.domain.response.ProblemDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProblemMapper extends BaseMapper<ProblemEntity> {
    IPage<ProblemDTO> selectPageVO(Page page, @Param("map") ProblemVO problemVO);

    Integer getRelevantPaperCount(@Param("map") ProblemVO problemVO);
}
