package com.yja.common.aspect;

import java.lang.annotation.*;

/**
 * @author Yang Jian
 * @date 2024/6/12 8:49
 * @description
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface ApiOperationLog {
    /**
     * 功能描述
     * @return
     */
    String description() default "";
}
