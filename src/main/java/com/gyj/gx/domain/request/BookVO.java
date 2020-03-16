package com.gyj.gx.domain.request;

import com.gyj.gx.base.util.validator.FirstValidator;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class BookVO {

    @NotBlank(message = "书名不存在",groups = {FirstValidator.class})
    @Length(min = 1, max = 255, message = "书名长度超过限制", groups = {FirstValidator.class})
    private String name;

    @NotBlank(message = "作者不存在",groups = {FirstValidator.class})
    @Length(min = 1, max = 255, message = "作者长度超过限制", groups = {FirstValidator.class})
    private String author;
}
