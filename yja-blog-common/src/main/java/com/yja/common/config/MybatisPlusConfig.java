package com.yja.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author 木白
 * @date 2024/7/2
 * @description
 */
@Configuration
@MapperScan("com.yja.common.domain.mapper")
public class MybatisPlusConfig {
}