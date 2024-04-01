package com.yj.common.exception;

import com.yj.common.enums.ResponseEnum;
import lombok.Data;

/**
 * 内部错误(Internal Server Error)
 * @author Yang Jian
 * @date 2024/3/26 22:36
 * @description 当服务器执行请求过程中，遇到内部处理错误时，会返回该错误信息。遇到这种错误，请检查AWS的错误日志，及时与我们联系
 * 所有JDK或非AWS异常抛出，被系统捕获的
 * 当客户端请求AWS服务时，若处于该场景将以该错误码返回到ResponseObject数据结构
 */
@Data
public class YJAException extends RuntimeException {

    private Integer code;

    public YJAException() {
        super();
    }

    public YJAException(String message) {
        super(message);
    }

    public YJAException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public YJAException(ResponseEnum responseStatusEnum) {
        super(responseStatusEnum.getMessage());
        this.code = responseStatusEnum.getCode();
    }

}
