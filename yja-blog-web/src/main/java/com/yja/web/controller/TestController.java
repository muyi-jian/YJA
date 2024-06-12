package com.yja.web.controller;

import com.yja.common.aspect.ApiOperationLog;
import com.yja.web.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yang Jian
 * @date 2024/6/11 11:12
 * @description
 */
@RestController
@Slf4j
@Tag(name = "测试接口")
public class TestController {

    @GetMapping ("/test")
    @ApiOperationLog(description = "测试接口GET")
    @Operation(summary = "测试接口GET", description = "测试接口GET-test")
    public String test2() {
        // 手动抛异常，入参是前面定义好的异常码枚举，返参统一交给全局异常处理器搞定
        //throw new BizException(ResponseCodeEnum.PRODUCT_NOT_FOUND);
        int n = 10/0;
        return "GET---TEST";
    }

    @PostMapping("/test1")
    @ApiOperationLog(description = "测试接口")
    public User test1(@RequestBody User user) {
        return user;
    }
}

