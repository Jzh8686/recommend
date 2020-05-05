package com.gyj.gx.domain.request;

import com.gyj.gx.base.util.validator.FirstValidator;
import com.gyj.gx.base.util.validator.SecondValidator;
import com.gyj.gx.base.util.validator.ThirdValidator;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserVO {
    @NotNull(message = "用户id不存在",groups = {ThirdValidator.class,SecondValidator.class})
    private Integer id;

    @NotBlank(message = "用户名不存在",groups = {FirstValidator.class})
    @Length(min =4,max=12,message = "用户名长度超过限制", groups = {FirstValidator.class})
    private String username;

    @NotBlank(message = "密码不存在",groups = {FirstValidator.class})
    @Length(min =4,max=12,message = "密码长度超过限制", groups = {FirstValidator.class})
    private String password;

    @NotBlank(message = "角色必须选择",groups = {SecondValidator.class})
    private String role;

    @NotNull(message = "状态不存在",groups = {ThirdValidator.class})
    private Integer state;
}
