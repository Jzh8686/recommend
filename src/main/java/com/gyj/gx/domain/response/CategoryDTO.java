package com.gyj.gx.domain.response;

import lombok.Data;

@Data
public class CategoryDTO {
    /**
     * id
     */
    private Integer id;

    /**
     * 一级分类 0:人物 1：时间 2：作品
     */
    private Integer type;

    /**
     * 二级分类
     */

    private String subtype;

    /**
     * 题目数量
     */
    private Integer num;
}
