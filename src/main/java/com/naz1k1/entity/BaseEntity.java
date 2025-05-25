package com.naz1k1.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class BaseEntity implements Serializable {
    @Id(keyType = KeyType.Auto)
    private Long id;
    private Long createBy;
    private Long updateBy;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private int deleted;
}
