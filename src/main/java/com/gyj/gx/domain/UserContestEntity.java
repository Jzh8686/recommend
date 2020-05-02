package com.gyj.gx.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("t_user_contest")
@Data
public class UserContestEntity {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;// 表主键ID

    private Integer uid; //用户id

    private Integer cid;//竞赛id

    private Integer pid;//题目id

    private Integer correct;//状态 -1:未批卷 0:错误 1：正确

    private Double point;//得分

    private String answer;//用户输入的答案
}
