package com.yj.common.resp;

import com.yj.common.enums.ResponseEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * 统一返回数据结构
 *
 * @author Yang Jian
 * @date 2024/3/26 19:47
 * @description
 */
@Data
public class ResponseObject<T> implements Serializable {

    private String code;

    private String message;

    private T data;

    public ResponseObject() {


    }

    public ResponseObject(String code, String message) {

        this.code = code;
        this.message = message;
    }

    public ResponseObject(String code, T data) {

        this.code = code;
        this.data = data;
    }

    public ResponseObject(String code, String message, T data) {

        this.code = code;
        this.message = message;
        this.data = data;
    }

    private ResponseObject(ResponseEnum resultStatus, T data) {

        this.code = resultStatus.getCode();
        this.message = resultStatus.getMessage();
        this.data = data;
    }

    /**
     * 业务成功返回业务代码和描述信息
     */
    public static ResponseObject<Void> success() {

        return new ResponseObject<Void>(ResponseEnum.SUCCESS, null);
    }

    /**
     * 业务成功返回业务代码,描述和返回的参数
     */
    public static <T> ResponseObject<T> success(T data) {

        return new ResponseObject<T>(ResponseEnum.SUCCESS, data);
    }

    /**
     * 业务成功返回业务代码,描述和返回的参数
     */
    public static <T> ResponseObject<T> success(ResponseEnum resultStatus, T data) {

        if (resultStatus == null) {

            return success(data);
        }
        return new ResponseObject<T>(resultStatus, data);
    }
    public static <T> ResponseObject<T> success(String code, String message) {

        return new ResponseObject<T>(code, message);
    }
    /**
     * 业务异常返回业务代码和描述信息
     */
    public static <T> ResponseObject<T> failure() {

        return new ResponseObject<T>(ResponseEnum.FAILURE, null);
    }

    /**
     * 业务异常返回业务代码,描述和返回的参数
     */
    public static <T> ResponseObject<T> failure(ResponseEnum resultStatus) {

        return failure(resultStatus, null);
    }
    /**
     * 业务异常返回业务代码,描述和返回的参数
     */
    public static <T> ResponseObject<T> failed(IResultCode resultCode) {
        return failure(resultCode.getCode(), resultCode.getMessage(), null);
    }

    /**
     * 业务异常返回业务代码,描述和返回的参数
     */
    public static <T> ResponseObject<T> failure(ResponseEnum resultStatus, T data) {

        if (resultStatus == null) {

            return new ResponseObject<T>(ResponseEnum.FAILURE, null);
        }
        return new ResponseObject<T>(resultStatus, data);
    }

    public static <T> ResponseObject<T> failure(String code, String message) {

        return new ResponseObject<T>(code, message);
    }

    public static <T> ResponseObject<T> failure(String code, String message, T data) {

        return new ResponseObject<T>(code, message, data);
    }
}
