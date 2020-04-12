package com.gyj.gx.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gyj.gx.domain.CategoryEntity;
import com.gyj.gx.domain.request.CategoryVO;
import com.gyj.gx.domain.request.ProblemVO;
import com.gyj.gx.domain.response.CategoryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapper extends BaseMapper<CategoryEntity> {

    IPage<CategoryDTO> selectPageVO(Page page, @Param("map") CategoryVO categoryVO);

    Integer getProblemNum(@Param("map") CategoryVO categoryVO);

    List<CategoryDTO> categoriesRelatedToProblem(@Param("map")ProblemVO problemVO);

}
