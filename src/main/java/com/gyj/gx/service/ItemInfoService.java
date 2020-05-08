package com.gyj.gx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gyj.gx.domain.ItemInfoEntity;
import com.gyj.gx.domain.response.ItemInfoDTO;
import java.util.List;

public interface ItemInfoService extends IService<ItemInfoEntity> {
    List<ItemInfoEntity> getLatestItem();
    void updateCover(String cover,Long userId);
    ItemInfoDTO getMoviceByName(String moviceName,Integer pageIndex, Integer size);
    ItemInfoDTO getMoviceByYear(Integer startYear, Integer endYear,Integer pageIndex,Integer size);
}
