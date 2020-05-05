package com.gyj.gx.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("t_comment")
@Data
public class CommentEntity {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;//表主键id

    private Integer fromUid;//评论用户id

    private Date commentTime;//评论时间

    private String content;//内容

    private Integer replyToUid;//回复目标的用户id

    private Integer replyToCid;//回复目标id

    private Integer pid;//题目id

    private Integer likesCount;//点赞数

    @TableLogic
    private Integer deleted;//0：未删除，1：已删除
}
