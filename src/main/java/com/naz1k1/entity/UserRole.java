package com.naz1k1.entity;

import com.mybatisflex.annotation.Table;

import lombok.Data;

@Data
@Table("sys_user_role")
public class UserRole {
    private Long userId;
    
    private Long roleId;
}
