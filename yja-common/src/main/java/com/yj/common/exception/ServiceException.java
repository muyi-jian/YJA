package com.yj.common.exception;

import com.yj.common.resp.IResultCode;
import lombok.Data;

/**
 * 服务异常
 * @author Yang Jian
 * @date 2024/3/26 22:36
 * @description
 */
@Data
public class ServiceException extends RuntimeException {


    public IResultCode resultCode;

    public ServiceException(IResultCode errorCode) {
        super(errorCode.getMessage());
        this.resultCode = errorCode;
    }

    public ServiceException(String message, Throwable cause){
        super(message, cause);
    }

    public ServiceException(Throwable cause){
        super(cause);
    }

    public ServiceException(String message) {
        super(message);
    }
}
