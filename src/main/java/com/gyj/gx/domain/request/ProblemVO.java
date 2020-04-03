package com.gyj.gx.domain.request;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.gyj.gx.base.util.validator.FirstValidator;
import com.gyj.gx.base.util.validator.SecondValidator;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ProblemVO {

    @NotNull(message = "id不能为空",groups = {FirstValidator.class})
    private Integer id;

    @NotNull(message = "题型必须选择",groups = {SecondValidator.class})
    private Integer problemType;// 题型 0:客观题 1:主观题 2:全部

    @NotBlank(message = "题干不能为空",groups = {SecondValidator.class})
    @Length(min=1,max=200,message = "题干长度超过限制",groups = {SecondValidator.class})
    private String description ; //题目描述即题干

    private String optionA; //选项A

    private String optionB; //选项B

    private String optionC; //选项C

    private String optionD; //选项D

    @NotBlank(message = "答案不能为空",groups = {SecondValidator.class})
    @Length(min=1,max=500,message = "答案长度超过限制",groups = {SecondValidator.class})
    private String answer; //参考答案

    @Length(min=1,max=500,message = "答案长度超过限制",groups = {SecondValidator.class})
    private String explanation;//解析

    @NotNull(message = "分类必须选择",groups = {SecondValidator.class})
    private List<Integer> categories;//二级分类

    private Integer cid;

    @TableLogic
    private Integer deleted;// 逻辑删除 0 未删除 1 已删除
}
