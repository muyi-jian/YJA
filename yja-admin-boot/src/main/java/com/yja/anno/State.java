package com.yja.anno;


import com.yja.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented // 元注解 标注注解是可以被抽取到帮助文档里边的
// 元注解 用在什么位置
@Target({ElementType.FIELD})
// 元注解 在那个阶段被保留
@Retention(RetentionPolicy.RUNTIME)
// 谁来提供校验规则
@Constraint(
        validatedBy = {StateValidation.class}
)
public @interface State {
    // 提供校验失败后的信息
    String message() default "{state参数的值只能是已发布或者草稿}";
    // 指定分组
    Class<?>[] groups() default {};
    // 负载， 获取到state注解的附加信息
    Class<? extends Payload>[] payload() default {};
}
