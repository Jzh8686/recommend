package com.gyj.gx.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("t_category")
@Data
public class CategoryEntity {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;// 表主键ID

    private Integer type;// 一级分类 0:人物 1：时间 2：作品

    private String subtype;// 二级分类

}
