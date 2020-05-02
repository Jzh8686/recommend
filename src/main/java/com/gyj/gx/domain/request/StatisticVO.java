package com.gyj.gx.domain.request;

import com.gyj.gx.base.util.validator.FirstValidator;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class StatisticVO {
    @NotNull(message = "竞赛id不能为空",groups = {FirstValidator.class})
    private Integer cid;

    private Integer pid;

    private Integer aNum;

    private Integer bNum;

    private Integer cNum;

    private Integer dNum;
}
