package com.gyj.gx.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_like")
public class LikeEntity {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;// 表主键ID

    private Integer uid;//用户id

    private Integer cid;//评论id
}
