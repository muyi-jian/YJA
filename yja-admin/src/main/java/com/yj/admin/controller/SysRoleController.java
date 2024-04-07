package com.yj.admin.controller;

import com.yj.common.annotation.ResponseResultBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色控制器
 * @author Yang Jian
 * @date 2024/4/4 16:16
 * @description
 */
@Tag(name = "角色接口")
@RestController
@RequestMapping("/api/roles")
@ResponseResultBody
public class SysRoleController {
}
