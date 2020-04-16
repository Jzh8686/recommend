package com.gyj.gx.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("t_contest")
@Data
public class ContestEntity {
    @TableId(value = "id",type= IdType.AUTO)
    private Integer id; //表主键ID

    private String contestName;//竞赛名

    private Integer paper; //试卷id

    private Integer limitTime;//限制时间

    private Date startTime;//开始时间

    private Date endTime;//结束时间

    private Integer state;//状态，0：未开始，1：进行中，2：已结束，等待批阅，3：已批阅

    @TableLogic
    private Integer deleted;//逻辑删除 取消考试 0：未取消，1：已取消
}
