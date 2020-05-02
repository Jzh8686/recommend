package com.gyj.gx.domain.response;

import lombok.Data;

@Data
public class ResultDTO {
    private Integer pid;

    private String answer;

    private Integer correct;

    private Double point;
}
