package com.gyj.gx.domain.response;

import lombok.Data;

@Data
public class UserDTO {
    private Integer id; //表主键id

    private String username;//用户名

    private String sex;//性别

    private Long userId;
    private String nickname;//昵称

    private String role;//角色

    private Integer state;//状态 0：激活 1：冻结

    private String avatar;//头像


}
