package com.gyj.gx.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@TableName("t_category_problem")
@Data
public class CategoryProblemEntity {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;// 表主键ID

    private Integer cid;

    private Integer pid;
    @TableLogic
    private Integer deleted;// 逻辑删除 0 未删除 1 已删除
}
