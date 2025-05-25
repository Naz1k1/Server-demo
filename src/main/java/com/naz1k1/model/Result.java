package com.naz1k1.model;

import lombok.Data;
import java.io.Serializable;

import com.naz1k1.model.enums.ResultCode;

@Data
public class Result<T> implements Serializable {
    private Integer code;   // 状态码（使用枚举值）
    private String msg;     // 提示消息（从枚举中获取）
    private T data;         // 返回数据（泛型）

    // 快速成功（无数据）
    public static <T> Result<T> success() {
        return success(null);
    }

    // 快速成功（带数据）
    public static <T> Result<T> success(T data) {
        return build(ResultCode.SUCCESS, data);
    }

    // 快速失败（默认错误码）
    public static <T> Result<T> error() {
        return error(ResultCode.INTERNAL_ERROR);
    }

    // 快速失败（指定枚举错误码）
    public static <T> Result<T> error(ResultCode resultCode) {
        return build(resultCode, null);
    }

    // 快速失败（自定义消息）
    public static <T> Result<T> error(String msg) {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.INTERNAL_ERROR.getCode());
        result.setMsg(msg);
        return result;
    }

    // 通用构建方法（枚举 + 数据）
    public static <T> Result<T> build(ResultCode resultCode, T data) {
        Result<T> result = new Result<>();
        result.setCode(resultCode.getCode());
        result.setMsg(resultCode.getMsg());
        result.setData(data);
        return result;
    }
}