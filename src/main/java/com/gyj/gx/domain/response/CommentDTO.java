package com.gyj.gx.domain.response;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CommentDTO {
    private Integer id;//表主键id

    private Integer fromUid;//评论用户id

    private Date commentTime;//评论时间

    private String content;//内容

    private Integer replyToUid;//回复目标的用户id

    private Integer replyToCid;//回复目标id

    private Integer pid;//题目id

    private Integer likesCount;//点赞数

    private List<CommentDTO> children;//子回复

    private String nickname;//用户昵称

    private String avatar;//用户头像

    private String toNickname;//回复目标用户昵称

    private String toAvatar;//回复目标用户头像
}
