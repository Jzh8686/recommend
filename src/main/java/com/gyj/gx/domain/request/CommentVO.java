package com.gyj.gx.domain.request;

import com.gyj.gx.base.util.validator.FirstValidator;
import com.gyj.gx.base.util.validator.SecondValidator;
import com.gyj.gx.base.util.validator.ThirdValidator;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class CommentVO {

    @NotNull(message = "id不能为空",groups = {ThirdValidator.class})
    private Integer id;//表主键id

    private Date commentTime;//评论时间

    @NotNull(message = "评论者不能为空",groups = {FirstValidator.class,ThirdValidator.class})
    private Integer fromUid;//评论用户id

    @NotNull(message = "内容不能为空",groups = {FirstValidator.class})
    @Length(min=1,max=500,message = "内容长度超过限制",groups = {FirstValidator.class})
    private String content;//内容

    private Integer replyToUid;//回复目标的用户id

    private Integer replyToCid;//回复目标id

    @NotNull(message = "问题id不能为空",groups = {SecondValidator.class})
    private Integer pid;//题目id

    private Integer likeCount;//点赞数
}
