package com.yja.web.controller;

import com.yja.common.aspect.ApiOperationLog;
import com.yja.common.domain.dos.UserDO;
import com.yja.common.domain.mapper.UserMapper;
import com.yja.web.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * @author Yang Jian
 * @date 2024/6/11 11:12
 * @description
 */
@RestController
@Slf4j
@Tag(name = "测试接口")
public class TestController {
    @Autowired
    private UserMapper userMapper;


    @GetMapping ("/test")
    @ApiOperationLog(description = "测试接口GET")
    @Operation(summary = "测试接口GET", description = "测试接口GET-test")
    public String test2() {
        // 手动抛异常，入参是前面定义好的异常码枚举，返参统一交给全局异常处理器搞定
        //throw new BizException(ResponseCodeEnum.PRODUCT_NOT_FOUND);
        // int n = 10/0;
        // 构建数据库实体类
        UserDO userDO = UserDO.builder()
                .username("yja博客")
                .password("123456")
                .createTime(new Date())
                .updateTime(new Date())
                .isDeleted(false)
                .build();

        userMapper.insert(userDO);
        return "GET---TEST";
    }

    @PostMapping("/test1")
    @ApiOperationLog(description = "测试接口")
    public User test1(@RequestBody User user) {
        // 设置三种日期字段值
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateDate(LocalDate.now());
        user.setTime(LocalTime.now());
        return user;
    }
}

