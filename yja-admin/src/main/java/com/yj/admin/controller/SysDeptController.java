package com.yj.admin.controller;

import com.yj.common.annotation.ResponseResultBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 部门控制器
 * @author Yang Jian
 * @date 2024/4/4 16:17
 * @description
 */
@Tag(name = "部门接口")
@RestController
@RequestMapping("/api/dept")
@ResponseResultBody
public class SysDeptController {
}
