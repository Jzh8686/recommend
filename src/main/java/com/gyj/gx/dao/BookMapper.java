package com.gyj.gx.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gyj.gx.domain.BookEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookMapper extends BaseMapper<BookEntity> {

}
