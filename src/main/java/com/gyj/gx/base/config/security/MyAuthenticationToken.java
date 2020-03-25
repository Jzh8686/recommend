package com.gyj.gx.base.config.security;

import com.gyj.gx.domain.UserEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class MyAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private UserEntity userEntity;

    public MyAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public MyAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    public MyAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities,UserEntity userEntity) {
        super(principal, credentials, authorities);
        this.userEntity = userEntity;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }
}
