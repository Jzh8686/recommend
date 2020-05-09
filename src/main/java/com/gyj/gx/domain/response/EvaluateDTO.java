package com.gyj.gx.domain.response;

import lombok.Data;

@Data
public class EvaluateDTO {
    private Double MAE;
    private Double recall;
    private Double precision;
}
