package com.gyj.gx.domain.response;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PaperDTO {
    private Integer id;// 表主键ID

    private String paperName; //试卷名

    private Date modifyDate; //修改日期

    private List<ProblemDTO> problems; //问题列表
}
