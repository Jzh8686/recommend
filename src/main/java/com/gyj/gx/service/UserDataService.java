package com.gyj.gx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gyj.gx.domain.ItemInfoEntity;
import com.gyj.gx.domain.Recommend;
import com.gyj.gx.domain.UserDataEntity;

import java.util.List;

public interface UserDataService extends IService<UserDataEntity> {
    List<ItemInfoEntity> generateRecommend(Long id);
    List<Long> getUserIdList();
    void ratingItem(Long userId,Long itemId,Float preference);
}
