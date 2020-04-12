package com.gyj.gx.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gyj.gx.base.page.MyPage;
import com.gyj.gx.domain.ProblemEntity;
import com.gyj.gx.domain.request.PaperVO;
import com.gyj.gx.domain.request.ProblemVO;
import com.gyj.gx.domain.response.ProblemDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProblemMapper extends BaseMapper<ProblemEntity> {
    List<ProblemDTO> selectPageVO(@Param("pg") MyPage myPage, @Param("map") ProblemVO problemVO); //分页题目列表

    Integer getTotalSearchCount(@Param("map") ProblemVO problemVO);

    List<Integer> getGroupCount(@Param("map") ProblemVO problemVO);

    Integer getRelevantPaperCount(@Param("map") ProblemVO problemVO);
}
