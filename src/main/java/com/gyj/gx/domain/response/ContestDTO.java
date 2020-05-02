package com.gyj.gx.domain.response;

import lombok.Data;

import java.util.Date;

@Data
public class ContestDTO {
    private Integer id; //表主键ID

    private String contestName;//竞赛名

    private Integer paper; //试卷id

    private Integer limitTime;//限制时间

    private Date startTime;//开始时间

    private Date endTime;//结束时间

    private Integer state;//状态，0：未开始，1：进行中，2：已结束，等待批阅，3：已批阅

    private Integer process;//阅卷进度
}
