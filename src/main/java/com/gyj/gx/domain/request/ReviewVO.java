package com.gyj.gx.domain.request;

import com.gyj.gx.base.util.validator.FirstValidator;
import com.gyj.gx.base.util.validator.SecondValidator;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ReviewVO {

    @NotNull(message = "竞赛id不能为空",groups = {FirstValidator.class})
    private Integer cid;//竞赛id

    @NotNull(message = "竞赛记录id能为空",groups = {SecondValidator.class})
    private Integer rid; //用户做的竞赛记录id

    private Integer correct;//状态 -1:未批卷 0：错误 1：正确

    @NotNull(message = "得分不能为空",groups = {SecondValidator.class})
    private Double point; //得分
}
