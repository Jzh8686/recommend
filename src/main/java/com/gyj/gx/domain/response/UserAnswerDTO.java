package com.gyj.gx.domain.response;

import lombok.Data;

@Data
public class UserAnswerDTO {
    private Integer uid; //用户id

    private String ans;//用户答案

    private Integer correct;//状态 -1：未批阅 0：错误 1：正确

    private Double score;//得分

    private Integer rid;//记录id
}
