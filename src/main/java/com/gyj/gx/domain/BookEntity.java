package com.gyj.gx.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("t_book")
@Data
public class BookEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;// 表主键ID

    private String name;// 书名

    private String author;// 作者

    @TableLogic
    private Integer deleted;// 逻辑删除 0 未删除 1 已删除
}
