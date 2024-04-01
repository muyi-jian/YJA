package com.yj.common.config;

import com.yj.common.advice.GlobalExceptionHandler;
import com.yj.common.advice.ResponseObjectAdvice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Yang Jian
 * @date 2024/3/26 23:37
 * @description
 */
@Configuration
public class YjaWebAutoConfiguration {
    static Logger logger = LoggerFactory.getLogger(YjaWebAutoConfiguration.class);

    public YjaWebAutoConfiguration() {
        logger.info("YjaWebAutoConfiguration is starting instantiating");
    }
    @Bean
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

    @Bean
    public ResponseObjectAdvice responseObjectAdvice() {
        return new ResponseObjectAdvice();
    }
}
