package com.yja.admin.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yang Jian
 * @date 2024/6/12 17:20
 * @description
 */
@RestController
@Tag(name = "测试接口1")
public class TestController1 {

    @GetMapping("/atest")
    @Operation(summary = "admin test")
    public String test() {
        return "test";
    }
}
