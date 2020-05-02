package com.gyj.gx.domain.response;

import lombok.Data;

import java.util.List;

@Data
public class ReviewDTO {

    private Integer cid;//竞赛id

    private String contestName;//竞赛名

    private List<ProblemsDTO> problemsDTO;

}
