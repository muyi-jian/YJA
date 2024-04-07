package com.yj.admin.security;

import com.yj.common.enums.ResponseEnum;
import com.yj.common.resp.ResponseUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Spring Security访问异常处理器
 * @author Yang Jian
 * @date 2024/4/4 21:42
 * @description
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        ResponseUtils.writeErrMsg(response, ResponseEnum.ACCESS_UNAUTHORIZED);
    }
}
