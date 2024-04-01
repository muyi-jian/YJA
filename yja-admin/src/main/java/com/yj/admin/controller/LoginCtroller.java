package com.yj.admin.controller;

import com.yj.common.annotation.ResponseResultBody;
import com.yj.common.enums.ResponseEnum;
import com.yj.common.exception.ServiceException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yang Jian
 * @date 2024/3/26 19:32
 * @description
 */
@RestController
@RequestMapping("/admin")
@ResponseResultBody
public class LoginCtroller {

    @PostMapping("/login")
    public  Map<String, Object>  login(
            @RequestParam(name = "username", required = false) String username,
            @RequestParam(name = "password", required = false) String password
    ){
        System.out.println("进入login================");
        System.out.println("username======"+username);
        System.out.println("password======"+password);
        if (!"admin".equals(username)){
            throw new ServiceException(ResponseEnum.LOGIN_MOBILE_ERROR);
        }
        if ("admin".equals(username) && !"123456".equals(password)){
            throw new ServiceException(ResponseEnum.LOGIN_PASSWORD_ERROR);
        }
        Map<String, Object> result = new HashMap<>();
        result.put("tokenType", "jwt");
        result.put("accessToken", "1213333333333333");
        return result;
    }

    @GetMapping(value = "/test")
    public String test(){
        int i = 10/0;
        return "test";
    }
}
