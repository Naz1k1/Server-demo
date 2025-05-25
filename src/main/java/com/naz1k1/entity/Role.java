package com.naz1k1.entity;

import com.mybatisflex.annotation.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Table("sys_role")
public class Role extends BaseEntity{
    
    private String roleName;

    private String roleKey;

    private Integer status;

}
