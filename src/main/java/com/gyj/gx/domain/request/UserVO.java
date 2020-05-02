package com.gyj.gx.domain.request;

import com.gyj.gx.base.util.validator.FirstValidator;
import com.gyj.gx.base.util.validator.SecondValidator;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class UserVO {

    private Integer id;

    @NotBlank(message = "用户名不存在",groups = {FirstValidator.class,SecondValidator.class})
    @Length(min =4,max=12,message = "用户名长度超过限制", groups = {FirstValidator.class,SecondValidator.class})
    private String username;

    @NotBlank(message = "密码不存在",groups = {FirstValidator.class,SecondValidator.class})
    @Length(min =4,max=12,message = "密码长度超过限制", groups = {FirstValidator.class,SecondValidator.class})
    private String password;

    @NotBlank(message = "角色必须选择",groups = {SecondValidator.class})
    private String role;
}
