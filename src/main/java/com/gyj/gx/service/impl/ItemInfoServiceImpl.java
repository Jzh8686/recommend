package com.gyj.gx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gyj.gx.dao.ItemInfoMapper;
import com.gyj.gx.dao.UserDataMapper;
import com.gyj.gx.domain.ItemInfoEntity;
import com.gyj.gx.domain.Recommend;
import com.gyj.gx.domain.response.ItemInfoDTO;
import com.gyj.gx.domain.response.MayLikeDTO;
import com.gyj.gx.service.ItemInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class ItemInfoServiceImpl extends ServiceImpl<ItemInfoMapper, ItemInfoEntity> implements ItemInfoService {
    @Autowired
    ItemInfoMapper itemInfoMapper;
    @Autowired
    UserDataMapper userDataMapper;
    public List<MayLikeDTO> getLatestItem() {
        List<ItemInfoEntity> latestItem = itemInfoMapper.getLatestItem();
        List<MayLikeDTO> res = new ArrayList<>();
        List<Long> itemList = latestItem.stream().map(ItemInfoEntity::getItemId).collect(Collectors.toList());
        List<Recommend> avePreference = userDataMapper.getAvePreference(itemList);
        Map<Long, Float> idToValueMap = avePreference.stream().collect(Collectors.toMap(Recommend::getItemId, Recommend::getPreference));
        latestItem.forEach(x->{
            MayLikeDTO mayLikeDTO = new MayLikeDTO();
            BeanUtils.copyProperties(x,mayLikeDTO);
            mayLikeDTO.setPreference(idToValueMap.get(x.getItemId()));
            res.add(mayLikeDTO);
        });
        return res;
    }

    public void updateCover(String cover, Long userId) {
        itemInfoMapper.updateCover(cover, userId);
    }


    @Override
    public ItemInfoDTO getMoviceByName(String moviceName,Integer pageIndex, Integer size) {
        QueryWrapper<ItemInfoEntity> wrapper = new QueryWrapper();
        if (moviceName != null && moviceName != "")
            wrapper.like("movice_name", moviceName);
        Page<ItemInfoEntity> page = new Page<>(pageIndex,size);
        Page<ItemInfoEntity> list = itemInfoMapper.selectPage(page,wrapper);
        return new ItemInfoDTO(list.getRecords(),list.getTotal());
    }

    @Override
    public ItemInfoDTO getMoviceByYear(Integer startYear, Integer endYear,Integer pageIndex,Integer size) {
        QueryWrapper<ItemInfoEntity> wrapper = new QueryWrapper();
        Calendar startDate = Calendar.getInstance();
        startDate.set(startYear,1,1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(endYear,1,1);
        wrapper.le("release_date",endDate.getTime());
        wrapper.gt("release_date",startDate.getTime());
        Page<ItemInfoEntity> page = new Page<>(pageIndex,size);
        Page<ItemInfoEntity> list = itemInfoMapper.selectPage(page,wrapper);
        return new ItemInfoDTO(list.getRecords(),list.getTotal());
    }
}
