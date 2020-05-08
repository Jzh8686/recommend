package com.gyj.gx.domain;

import lombok.Data;

@Data
public class Recommend {

    private Long itemId;
    private Float rating;


    public Recommend(Long itemId, Float rating) {
        this.itemId = itemId;
        this.rating = rating;
    }
}
