package com.gyj.gx.base.config.security;

import com.alibaba.fastjson.JSON;
import com.gyj.gx.base.returns.RespCode;
import com.gyj.gx.base.returns.RespEntity;
import com.gyj.gx.domain.UserEntity;
import com.gyj.gx.domain.response.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        UserEntity userEntity = ((MyAuthenticationToken) authentication).getUserEntity();
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userEntity,userDTO);
        RespEntity respEntity = new RespEntity(RespCode.SUCCESS, userDTO);

        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(respEntity));

//        httpServletResponse.addCookie(new Cookie("id",((SecurityUser)authentication.getPrincipal()).getId()+""));
//        httpServletResponse.sendRedirect("/pages/index.html");
    }
}
