package com.gyj.gx.base.config.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gyj.gx.domain.UserDataEntity;
import com.gyj.gx.domain.UserEntity;
import com.gyj.gx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        List<GrantedAuthority> authorities = new ArrayList<>();
        QueryWrapper<UserEntity> wrapper = new QueryWrapper();
        wrapper.eq("user_id", username);
        UserEntity userEntity = userService.getOne(wrapper);
        if (userEntity == null)
            throw new BadCredentialsException("用户名或密码错误");
        if (userEntity.getInit()==1){
            if (Objects.equals(password,"1")){
                authorities.add(new SimpleGrantedAuthority("ADMIN"));
                return new MyAuthenticationToken(username, password, authorities, userEntity);
            }

        }
        if(userEntity.getState()==1)
            throw new LockedException("该账号已被冻结");
        if (!bCryptPasswordEncoder.matches(password,userEntity.getPassword()))
            throw new BadCredentialsException("用户名或密码错误");

        for (String role : userEntity.getRole().split("[|]")) {
            authorities.add(new SimpleGrantedAuthority(role));
        }

        return new MyAuthenticationToken(username, password, authorities, userEntity);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
