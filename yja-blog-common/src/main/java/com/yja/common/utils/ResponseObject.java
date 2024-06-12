package com.yja.common.utils;

import com.yja.common.exception.BaseExceptionInterface;
import com.yja.common.exception.BizException;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Yang Jian
 * @date 2024/6/12 10:48
 * @description
 */
@Data
public class ResponseObject<T> implements Serializable {

    // 是否成功 默认为true
    private boolean success = true;
    // 响应信息
    private String message;
    // 异常码
    private String errorCode;
    // 响应数据
    private T data;

    // ========================================= 成功 ========================================
    public static <T> ResponseObject<T> success(){
        ResponseObject<T> responseObject = new ResponseObject<>();
        return responseObject;
    }
    public static <T> ResponseObject<T> success(T data){
        ResponseObject<T> responseObject = new ResponseObject<>();
        responseObject.setData(data);
        return responseObject;
    }

    // ========================================= 失败 ========================================
    public static <T> ResponseObject<T> fail(){
        ResponseObject<T> responseObject = new ResponseObject<>();
        responseObject.setSuccess(false);
        return responseObject;
    }

    public static <T> ResponseObject<T> fail(String message){
        ResponseObject<T> responseObject = new ResponseObject<>();
        responseObject.setSuccess(false);
        responseObject.setMessage(message);
        return responseObject;
    }
    public static <T> ResponseObject<T> fail(String errorCode, String message){
        ResponseObject<T> responseObject = new ResponseObject<>();
        responseObject.setSuccess(false);
        responseObject.setErrorCode(errorCode);
        responseObject.setMessage(message);
        return responseObject;
    }

    public static <T> ResponseObject<T> fail(BizException bizException) {
        ResponseObject<T> response = new ResponseObject<>();
        response.setSuccess(false);
        response.setErrorCode(bizException.getErrorCode());
        response.setMessage(bizException.getErrorMessage());
        return response;
    }

    public static <T> ResponseObject<T> fail(BaseExceptionInterface baseExceptionInterface) {
        ResponseObject<T> response = new ResponseObject<>();
        response.setSuccess(false);
        response.setErrorCode(baseExceptionInterface.getErrorCode());
        response.setMessage(baseExceptionInterface.getErrorMessage());
        return response;
    }
}
