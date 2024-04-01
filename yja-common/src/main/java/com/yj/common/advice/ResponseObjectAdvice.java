package com.yj.common.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yj.common.annotation.ResponseResultBody;
import com.yj.common.resp.ResponseObject;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.annotation.Annotation;
import java.util.Objects;

/**
 * 统一响应结果通知
 * @author Yang Jian
 * @date 2024/3/26 21:24
 * @description
 */
@RestControllerAdvice
public class ResponseObjectAdvice implements ResponseBodyAdvice<Object> {
    @Resource
    private ObjectMapper objectMapper;

    private static final Class<? extends Annotation> ANNOTATION_TYPE = ResponseResultBody.class;

    /**
     * 判断类或者方法是否使用了 @ResponseResultBody
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {

        return AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), ANNOTATION_TYPE) || returnType.hasMethodAnnotation(ANNOTATION_TYPE);
        //return true;
    }

    /**
     * 当类或者方法使用了 @ResponseResultBody 就会调用这个方法
     */
    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        System.out.println("111111111111111111");
        //如果返回类型是string，那么springmvc是直接返回的，此时需要手动转化为json
        // 当body都为null时，下面的if判断条件都不满足，如果接口返回类似为String，会报错com.shepherd.fast.global.ResponseVO cannot be cast to java.lang.String
        Class<?> returnClass = Objects.requireNonNull(returnType.getMethod()).getReturnType();
        System.out.println("body===="+body);
        if (body instanceof String || Objects.equals(returnClass, String.class)) {
            System.out.println("body1===="+body);
            return objectMapper.writeValueAsString(ResponseObject.success(body));
        }
        // 防止重复包裹的问题出现
        if (body instanceof ResponseObject) {
            System.out.println("body2===="+body);
            return body;
        }
        System.out.println("body3===="+body);
        return ResponseObject.success(body);
    }
}
