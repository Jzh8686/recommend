create table t_book
(
    id      int auto_increment
    primary key,
    name    varchar(255)       null,
    author  varchar(255)       null,
    deleted int(255) default 0 null
    );

create table t_user
(
    id       int auto_increment comment '主键'
    primary key,
    username varchar(12)                   not null comment '用户名',
    password varchar(70)                   not null comment '密码',
    sex      int         default 0         null comment '性别 0:未知 1:男 2:女',
    realname varchar(6)                    null comment '真名',
    role     varchar(20) default 'STUDENT' not null comment '角色',
    state    int         default 0         not null comment '状态 0:激活 1:冻结',
    deleted  int(255)    default 0         not null comment '删除位 0:未删除 1:已删除',
    constraint t_user_username_uindex
    unique (username)
    )
    comment '用户';

