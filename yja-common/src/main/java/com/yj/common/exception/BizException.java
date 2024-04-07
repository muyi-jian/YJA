package com.yj.common.exception;

import com.yj.common.resp.IResultCode;
import lombok.Data;

/**
 * 业务异常类
 * @author Yang Jian
 * @date 2024/3/26 22:36
 * @description
 */
@Data
public class BizException extends RuntimeException {


    public IResultCode resultCode;

    public BizException(IResultCode errorCode) {
        super(errorCode.getMessage());
        this.resultCode = errorCode;
    }

    public BizException(String message, Throwable cause){
        super(message, cause);
    }

    public BizException(Throwable cause){
        super(cause);
    }

    public BizException(String message) {
        super(message);
    }
}
