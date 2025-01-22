package com.yupi.argylepicturebackend.exception;

import lombok.Getter;

/**
 * @Author: hjm
 * @Date: 2025/01/21/11:00
 * @Description: 自定义异常类
 */
@Getter
public class BusinessException extends RuntimeException {
    /**
     * 状态码
     */
    private final int code;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
    }

}
