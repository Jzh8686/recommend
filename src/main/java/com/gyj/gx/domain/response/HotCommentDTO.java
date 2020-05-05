package com.gyj.gx.domain.response;

import lombok.Data;

@Data
public class HotCommentDTO {
    private Integer id;

    private Integer likesCount;

    private String content;

    private Integer pid;

    private String description;
}
