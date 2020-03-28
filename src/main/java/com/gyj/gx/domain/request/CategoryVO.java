package com.gyj.gx.domain.request;

import com.gyj.gx.base.util.validator.FirstValidator;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotBlank;
@Data

public class CategoryVO {
    @NotBlank(message = "分类名不存在",groups = {FirstValidator.class})
    private String subtype;
    private Integer type;
}
