package com.gyj.gx.domain.request;

import com.gyj.gx.base.util.validator.FirstValidator;
import com.gyj.gx.base.util.validator.SecondValidator;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class ContestVO {
    @NotNull(message = "id不能为空",groups = {SecondValidator.class})
    private Integer id; //表主键ID

    @NotBlank(message = "竞赛名不能为空",groups = {FirstValidator.class})
    @Length(min=1,max=30,message = "竞赛名长度超过限制",groups = {FirstValidator.class})
    private String contestName;//竞赛名

    @NotNull(message = "试卷不能为空",groups = {FirstValidator.class})
    private Integer paper; //试卷id

    @NotNull(message = "限制时间不能为空",groups = {FirstValidator.class})
    private Integer limitTime;//限制时间

    @NotNull(message = "开始时间不能为空",groups = {FirstValidator.class})
    private Date startTime;//开始时间

    @NotNull(message = "结束时间不能为空",groups = {FirstValidator.class})
    private Date endTime;//结束时间

    private Integer state;//状态，0：未开始，1：进行中，2：已结束，等待批阅，3：已批阅
}