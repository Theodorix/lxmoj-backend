package com.yupi.lxmoj.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用返回类
 *
 * @param <T>
 */
@Data
public class BaseResponse<T> implements Serializable {

    //@Serial
    private static final long serialVersionUID = 4188582262475809162L;
    private int code;

    private T data;

    private String message;

    // 无参构造函数
    public BaseResponse() {

    }

    public BaseResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public BaseResponse(int code, T data) {
        this(code, data, "");
    }

    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), null, errorCode.getMessage());
    }

    // 可选的静态工厂方法
    public static <T> BaseResponse<T> of(int code, T data, String message) {
        return new BaseResponse<>(code, data, message);
    }
}
