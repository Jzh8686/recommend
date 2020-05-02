package com.gyj.gx.domain.response;

import lombok.Data;

@Data
public class UserDTO {
    private Integer id; //表主键id

    private String username;//用户名

    private Integer sex;//性别

    private String nickname;//昵称

    private String role;//角色


}
