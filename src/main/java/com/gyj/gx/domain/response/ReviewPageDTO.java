package com.gyj.gx.domain.response;

import lombok.Data;

@Data
public class ReviewPageDTO {
    private Integer cid;// 表主键ID也是竞赛id

    private String contestName; //竞赛名

    private Integer toBeReviewed;//当前进度

    private Integer totalReview;//总进度

}
