package com.gyj.gx.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gyj.gx.domain.ItemInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ItemInfoMapper extends BaseMapper<ItemInfoEntity> {
    List<ItemInfoEntity> getLatestItem();
    void updateCover(@Param("cover") String cover,@Param("itemId") Long itemId);
    List<ItemInfoEntity> getItemByItemId(@Param("list") List<Long> list);
}
