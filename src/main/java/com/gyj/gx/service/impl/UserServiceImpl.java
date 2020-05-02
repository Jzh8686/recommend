package com.gyj.gx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gyj.gx.base.exception.BusinessException;
import com.gyj.gx.base.page.PageModule;
import com.gyj.gx.base.returns.RespCode;
import com.gyj.gx.base.util.PageUtil;
import com.gyj.gx.base.util.validator.FirstValidator;
import com.gyj.gx.base.util.validator.SecondValidator;
import com.gyj.gx.base.util.validator.ValidatorBeanFactory;
import com.gyj.gx.dao.UserMapper;
import com.gyj.gx.domain.UserEntity;
import com.gyj.gx.domain.request.UserVO;
import com.gyj.gx.domain.response.UserDTO;
import com.gyj.gx.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserEntity getUserByUsername(String username) {
        UserEntity userEntity = getOne(
                new QueryWrapper<UserEntity>().lambda()
                .eq(UserEntity::getUsername,username)
        );

        return userEntity;
    }

    @Override
    public boolean register(UserVO userVO) {
        ValidatorBeanFactory.validate(userVO, FirstValidator.class);

        UserEntity userEntity = getOne(
                new QueryWrapper<UserEntity>().lambda()
                .eq(UserEntity::getUsername,userVO.getUsername())
        );
        if (userEntity!=null)
            throw new BusinessException(RespCode.CUSTOM_ERROR,"存在重复的用户名");

        UserEntity newUser = new UserEntity();
        newUser.setUsername(userVO.getUsername());
        newUser.setPassword(bCryptPasswordEncoder.encode(userVO.getPassword()));

        return save(newUser);
    }

    @Override
    public PageModule<UserDTO> getPageList(PageModule pageModule, UserVO userVO) {
        Page page = new Page(pageModule.getPageNum(),pageModule.getPageSize());
        LambdaQueryWrapper<UserEntity> query = new QueryWrapper<UserEntity>().lambda();
        if(StringUtils.isNotBlank(userVO.getUsername()))
            query.like(UserEntity::getUsername,userVO.getUsername());
        PageModule pm = PageUtil.transToPageModule(page(page,query));
        Object items = pm.getItems().stream().map(x->{
            UserEntity userEntity = (UserEntity)x;
            UserDTO userDTO = new UserDTO();
            userDTO.setId(userEntity.getId());
            userDTO.setUsername(userEntity.getUsername());
            userDTO.setSex(userEntity.getSex());
            userDTO.setRole(userEntity.getRole());
            return  userDTO;
        }).collect(Collectors.toList());
        pm.setItems((List)items);
        return pm;
    }

    @Override
    public boolean adminRegister(UserVO userVO) {
        ValidatorBeanFactory.validate(userVO, SecondValidator.class);

        UserEntity userEntity = getOne(
                new QueryWrapper<UserEntity>().lambda()
                        .eq(UserEntity::getUsername,userVO.getUsername())
        );
        if (userEntity!=null)
            throw new BusinessException(RespCode.CUSTOM_ERROR,"存在重复的用户名");

        UserEntity newUser = new UserEntity();
        newUser.setUsername(userVO.getUsername());
        newUser.setPassword(bCryptPasswordEncoder.encode(userVO.getPassword()));
        newUser.setRole(userVO.getRole());
        return save(newUser);
    }
}
