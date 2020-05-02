package com.gyj.gx.domain.request;

import com.gyj.gx.base.util.validator.FirstValidator;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ResultVO {
    @NotNull(message = "用户不能为空",groups = {FirstValidator.class})
    private Integer uid;

    @NotNull(message = "竞赛id不能为空",groups = {FirstValidator.class})
    private Integer cid;

    private String answer;

    private Integer correct;

    private Double point;
}
