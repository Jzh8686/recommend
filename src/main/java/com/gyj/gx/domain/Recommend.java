package com.gyj.gx.domain;

import lombok.Data;

@Data
public class Recommend {

    private Long itemId;
    private Float preference;


    public Recommend(Long itemId, Float rating) {
        this.itemId = itemId;
        this.preference = rating;
    }
}
