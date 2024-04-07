package com.yj.admin.controller;

import com.yj.admin.service.SysUserService;
import com.yj.common.annotation.ResponseResultBody;
import com.yj.common.resp.ResponseObject;
import com.yj.core.model.vo.UserInfoVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制器
 * @author Yang Jian
 * @date 2024/4/4 16:17
 * @description
 */
@Tag(name = "用户接口")
@RestController
@RequestMapping("/api/users")
@ResponseResultBody
public class SysUserController {

    @Autowired
    private SysUserService userService;

    @Operation(summary = "获取当前登录用户信息")
    @GetMapping("/me")
    public ResponseObject<UserInfoVO> getCurrentUserInfo() {
        UserInfoVO userInfoVO = userService.getCurrentUserInfo();
        return ResponseObject.success(userInfoVO);
    }
}
