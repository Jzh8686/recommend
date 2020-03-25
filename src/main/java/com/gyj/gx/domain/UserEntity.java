package com.gyj.gx.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("t_user")
@Data
public class UserEntity {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;// 表主键ID
    private String username;//用户名
    private String password;//密码
    private Integer sex;//性别 0:未知 1：男 2：女
    private String realname;//真名
    private String role;//角色
    private Integer state;//状态 0：激活 1：冻结

}