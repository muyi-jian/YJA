package com.yj.common.exception;

import com.yj.common.enums.ResponseEnum;
import lombok.Data;

/**
 * 业务异常类
 * @author Yang Jian
 * @date 2024/3/26 22:36
 * @description
 */
@Data
public class BizException extends RuntimeException {

    private Integer code;

    public BizException() {
        super();
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BizException(ResponseEnum responseStatusEnum) {
        super(responseStatusEnum.getMessage());
        this.code = responseStatusEnum.getCode();
    }

}
