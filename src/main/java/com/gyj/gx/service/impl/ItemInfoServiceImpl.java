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
        List<Long> itemList = latestItem.stream().map(ItemInfoEntity::getItemId).collect(Collectors.toList());
        List<Recommend> avePreference = userDataMapper.getAvePreference(itemList);
        Map<Long, Float> idToValueMap = avePreference.stream().collect(Collectors.toMap(Recommend::getItemId, Recommend::getPreference));
        List<MayLikeDTO> res = ItemInfoServiceImpl.entityToDTO(latestItem, idToValueMap);

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
        List<ItemInfoEntity> records = list.getRecords();
        List<Long> idList = records.stream().map(x -> x.getItemId()).collect(Collectors.toList());
        List<Recommend> avePreference = userDataMapper.getAvePreference(idList);
        Map<Long, Float> idToValueMap = avePreference.stream().collect(Collectors.toMap(x -> x.getItemId(), x -> x.getPreference()));
        List<MayLikeDTO> res = ItemInfoServiceImpl.entityToDTO(records, idToValueMap);
        return new ItemInfoDTO(res,list.getTotal());
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
        List<ItemInfoEntity> records = list.getRecords();
        List<Long> idList = records.stream().map(x -> x.getItemId()).collect(Collectors.toList());
        List<Recommend> avePreference = userDataMapper.getAvePreference(idList);
        Map<Long, Float> idToValueMap = avePreference.stream().collect(Collectors.toMap(x -> x.getItemId(), x -> x.getPreference()));
        List<MayLikeDTO> res = ItemInfoServiceImpl.entityToDTO(records, idToValueMap);
        return new ItemInfoDTO(res,list.getTotal());
    }

    public static List<MayLikeDTO> entityToDTO(List<ItemInfoEntity> itemInfoEntityList,Map<Long,Float> idToPreferenceMap){
        List<MayLikeDTO> res = new ArrayList<>();
        itemInfoEntityList.forEach(x->{
            MayLikeDTO mayLikeDTO = new MayLikeDTO();
            BeanUtils.copyProperties(x,mayLikeDTO);
            mayLikeDTO.setPreference(idToPreferenceMap.get(x.getItemId()));
            res.add(mayLikeDTO);
        });
        return res;
    }
}
