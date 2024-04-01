package com.yj.common.annotation;

import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.*;

/**
 * @author Yang Jian
 * @date 2024/3/26 22:10
 * @description
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({
        ElementType.TYPE, ElementType.METHOD})
@Documented
@ResponseBody
public @interface ResponseResultBody {


}