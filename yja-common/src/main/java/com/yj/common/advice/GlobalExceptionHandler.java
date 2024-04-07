package com.yj.common.advice;

import com.yj.common.enums.ResponseEnum;
import com.yj.common.exception.BizException;
import com.yj.common.exception.ServiceException;
import com.yj.common.resp.ResponseObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yang Jian
 * @date 2024/3/26 22:17
 * @description
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    /**
     * 全局异常处理 接口全部设置正常状态（200）返回
     * @param e 异常
     * @return ResponseEntity<ResponseObject>
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseObject>  exceptionHandler(Exception e){

        // 处理业务异常
        if (e instanceof BizException) {

            BizException bizException = (BizException) e;
            if (bizException.getResultCode() == null) {

                bizException.setResultCode(ResponseEnum.SYSTEM_BAD_REQUEST);
            }
            //return ResponseObject.failure(bizException.getCode(), bizException.getMessage());
            return new ResponseEntity<>(ResponseObject.failure((ResponseEnum) bizException.getResultCode(), bizException.getMessage()), HttpStatus.OK);
        }else if (e instanceof ServiceException) {

            ServiceException serviceException = (ServiceException) e;
            if (serviceException.getResultCode() == null) {

                serviceException.setResultCode(ResponseEnum.SYSTEM_BAD_REQUEST);
            }
            return new ResponseEntity<>(ResponseObject.failure((ResponseEnum) serviceException.getResultCode(), serviceException.getMessage()), HttpStatus.OK);
            //return ResponseObject.failure(serviceException.getCode(), serviceException.getMessage());
        } else if (e instanceof MethodArgumentNotValidException) {

            // 参数检验异常
            MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) e;
            Map<String, String> map = new HashMap<>();
            BindingResult result = methodArgumentNotValidException.getBindingResult();
            result.getFieldErrors().forEach((item)->{

                String message = item.getDefaultMessage();
                String field = item.getField();
                map.put(field, message);
            });
            log.error("数据校验出现错误：", e);
            return new ResponseEntity<>(ResponseObject.failure(ResponseEnum.SYSTEM_BAD_REQUEST, map), HttpStatus.OK);
            //return ResponseObject.failure(ResponseEnum.SYSTEM_BAD_REQUEST, map);
        } else if (e instanceof HttpRequestMethodNotSupportedException) {

            log.error("请求方法错误：", e);
            //return ResponseObject.failure(ResponseEnum.SYSTEM_BAD_REQUEST.getCode(), "请求方法不正确");
            return new ResponseEntity<>(ResponseObject.failure(ResponseEnum.SYSTEM_BAD_REQUEST.getCode(), "请求方法不正确"), HttpStatus.OK);
        } else if (e instanceof MissingServletRequestParameterException) {

            log.error("请求参数缺失：", e);
            MissingServletRequestParameterException ex = (MissingServletRequestParameterException) e;
            //return ResponseObject.failure(ResponseEnum.SYSTEM_BAD_REQUEST.getCode(), "请求参数缺少: " + ex.getParameterName());
            return new ResponseEntity<>(ResponseObject.failure(ResponseEnum.SYSTEM_BAD_REQUEST.getCode(), "请求参数缺少: " + ex.getParameterName()), HttpStatus.OK);
        } else if (e instanceof MethodArgumentTypeMismatchException) {

            log.error("请求参数类型错误：", e);
            MethodArgumentTypeMismatchException ex = (MethodArgumentTypeMismatchException) e;
            //return ResponseObject.failure(ResponseEnum.SYSTEM_BAD_REQUEST.getCode(), "请求参数类型不正确：" + ex.getName());
            return new ResponseEntity<>(ResponseObject.failure(ResponseEnum.SYSTEM_BAD_REQUEST.getCode(), "请求参数类型不正确：" + ex.getName()), HttpStatus.OK);
        } else if (e instanceof NoHandlerFoundException) {

            NoHandlerFoundException ex = (NoHandlerFoundException) e;
            log.error("请求地址不存在：", e);
            //return ResponseObject.failure(ResponseEnum.NOT_EXIST, ex.getRequestURL());
            return new ResponseEntity<>(ResponseObject.failure(ResponseEnum.REQUEST_NOT_EXIST, ex.getRequestURL()), HttpStatus.OK);
        } else {

            //如果是系统的异常，比如空指针这些异常
            log.error("【系统异常】", e);
            //return ResponseObject.failure(ResponseEnum.FAILURE.getCode(), ResponseEnum.FAILURE.getMessage());
            return new ResponseEntity<>(ResponseObject.failure(ResponseEnum.FAILURE.getCode(), ResponseEnum.FAILURE.getMessage()), HttpStatus.OK);
        }
    }

}