package com.naz1k1.entity;

import com.mybatisflex.annotation.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Table("sys_user")
public class User extends BaseEntity {
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String phone;
    private int status;
}
