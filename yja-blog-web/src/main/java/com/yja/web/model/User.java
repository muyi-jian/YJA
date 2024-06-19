package com.yja.web.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author Yang Jian
 * @date 2024/6/12 10:05
 * @description
 */
@Data
public class User {

    private String username;
    private Integer age;


    // 创建时间
    private LocalDateTime createTime;
    // 更新日期
    private LocalDate updateDate;
    // 时间
    private LocalTime time;
}
