package com.yj.admin.security;

import com.yj.common.enums.ResponseEnum;
import com.yj.common.resp.ResponseUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 认证异常处理
 * @author Yang Jian
 * @date 2024/4/4 21:36
 * @description
 */
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        int status = response.getStatus();
        if (status == HttpServletResponse.SC_NOT_FOUND) {
            // 资源不存在
            ResponseUtils.writeErrMsg(response, ResponseEnum.REQUEST_RESOURCE_NOT_FOUND);
        } else {

            if(authException instanceof BadCredentialsException){
                // 用户名或密码错误
                ResponseUtils.writeErrMsg(response, ResponseEnum.USERNAME_OR_PASSWORD_ERROR);
            }else {
                // 未认证或者token过期
                ResponseUtils.writeErrMsg(response, ResponseEnum.TOKEN_INVALID);
            }
        }
    }
}

