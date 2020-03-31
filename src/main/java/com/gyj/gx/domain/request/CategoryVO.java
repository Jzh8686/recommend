package com.gyj.gx.domain.request;

import com.gyj.gx.base.util.validator.FirstValidator;
import com.gyj.gx.base.util.validator.SecondValidator;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data

public class CategoryVO {

    @NotNull(message = "id不能为空",groups = {SecondValidator.class})
    private Integer id;

    @NotBlank(message = "分类名不存在",groups = {FirstValidator.class})
    @Length(min=1,max=20,message = "分类名长度超过限制",groups = {FirstValidator.class})
    private String subtype;
    private Integer type;
}
