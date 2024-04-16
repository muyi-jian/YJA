package com.yj.admin.controller;

import com.yj.admin.service.AuthService;
import com.yj.common.annotation.ResponseResultBody;
import com.yj.core.model.dto.CaptchaResult;
import com.yj.core.model.dto.LoginResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 权限控制器-
 * @author Yang Jian
 * @date 2024/3/26 19:32
 * @description
 */

@Tag(name = "认证中心")
@RestController
@RequestMapping("/api/auth")
@ResponseResultBody
public class AuthController {
    @Autowired
    private AuthService authService;


    @PostMapping("/login")
    public  LoginResult  login(
            @RequestParam(name = "username", required = false) String username,
            @RequestParam(name = "password", required = false) String password
    ){
        System.out.println("进入login================");
        System.out.println("username======"+username);
        System.out.println("password======"+password);
        return authService.login(username, password);
    }

    @GetMapping("/captcha")
    public CaptchaResult captcha(){
        System.out.println("authService.captcha()=="+authService.captcha());
        return authService.captcha();
    }


    @Operation(summary = "注销")
    @DeleteMapping("/logout")
    public boolean logout() {
        authService.logout();
        return true;
    }

    @GetMapping(value = "/test")
    public String test(){
        int i = 10/0;
        return "test";
    }
}
