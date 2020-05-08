package com.gyj.gx.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gyj.gx.domain.UserDataEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserDataMapper extends BaseMapper<UserDataEntity> {
    void insertUserData(@Param("data") UserDataEntity userDataEntity);
    List<UserDataEntity> getUserData(Integer id);
    List<Long> getUserIdList();
}
