package com.yj.common.exception;

import com.yj.common.enums.ResponseEnum;
import lombok.Data;

/**
 * 服务异常
 * @author Yang Jian
 * @date 2024/3/26 22:36
 * @description
 */
@Data
public class ServiceException extends RuntimeException {

    private Integer code;

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public ServiceException(ResponseEnum responseStatusEnum) {
        super(responseStatusEnum.getMessage());
        this.code = responseStatusEnum.getCode();
    }

}
