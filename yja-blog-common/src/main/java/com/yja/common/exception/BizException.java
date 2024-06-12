package com.yja.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Yang Jian
 * @date 2024/6/12 11:33
 * @description
 */
@Getter
@Setter
public class BizException extends RuntimeException {
    // 异常码
    private String errorCode;
    // 错误信息
    private String errorMessage;

    public BizException(BaseExceptionInterface baseExceptionInterface) {
        this.errorCode = baseExceptionInterface.getErrorCode();
        this.errorMessage = baseExceptionInterface.getErrorMessage();
    }
}
