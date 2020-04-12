package com.gyj.gx.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("t_paper")
@Data
public class PaperEntity {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;// 表主键ID

    private String paperName; //试卷名

    private Date modifyDate; //修改日期

    @TableLogic
    private Integer deleted;// 逻辑删除 0 未删除 1 已删除
}
