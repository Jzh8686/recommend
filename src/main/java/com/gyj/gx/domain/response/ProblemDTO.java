package com.gyj.gx.domain.response;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.util.List;

@Data
public class ProblemDTO {
    /**
     * id
     */
    private Integer id;

    /**
     * 题型
     */
    private Integer problemType;// 一级分类 0:客观题 1:主观题

    private String description ; //题目描述即题干

    private String optionA; //选项A

    private String optionB; //选项B

    private String optionC; //选项C

    private String optionD; //选项D

    private String answer; //参考答案

    private String explanation;//解析

    private List<CategoryDTO> categories;//分类列表

}
