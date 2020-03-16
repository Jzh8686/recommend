package com.gyj.gx.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("t_book")
@Data
public class BookEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;// 表主键ID

    private String name;// 书名

    private String author;// 作者

}
