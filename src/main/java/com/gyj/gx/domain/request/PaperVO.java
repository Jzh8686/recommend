package com.gyj.gx.domain.request;

import com.gyj.gx.base.util.validator.FirstValidator;
import com.gyj.gx.base.util.validator.SecondValidator;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class PaperVO {
    @NotNull(message = "id不能为空",groups = {SecondValidator.class})
    private Integer id;// 表主键ID

    @NotNull(message = "试卷名为空",groups = {FirstValidator.class})
    @Length(min=1,max=30,message = "试卷超过限制",groups = {FirstValidator.class})
    private String paperName; //试卷名

    @NotNull(message = "题目列表不能为空",groups = {FirstValidator.class})
    private List<ProblemVO> problems;//问题列表
}
