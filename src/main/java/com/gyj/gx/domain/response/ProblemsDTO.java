package com.gyj.gx.domain.response;

import lombok.Data;

import java.util.List;

@Data
public class ProblemsDTO {
    private Integer pid;

    private String description;

    private Double point;

    private String answer;

    private List<UserAnswerDTO> userAnswerDTO;
}
