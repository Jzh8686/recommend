package com.gyj.gx.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gyj.gx.domain.Recommend;
import com.gyj.gx.domain.UserEntity;
import com.gyj.gx.domain.request.UserVO;
import com.gyj.gx.domain.response.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
    boolean updateAvatar(@Param("avatar") String avatar,@Param("userId")Long userId);

}
