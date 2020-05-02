package com.gyj.gx.domain.request;

import com.gyj.gx.base.util.validator.FirstValidator;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class SubmitVO {
    @NotNull(message = "用户id不能为空",groups = {FirstValidator.class})
    private Integer uid;   //用户id

    @NotNull(message = "竞赛id不能为空",groups = {FirstValidator.class})
    private Integer cid;//竞赛id

    @NotNull(message = "问题不能为空",groups = {FirstValidator.class})
    private List<ProblemVO> problems; //问题对象
}
