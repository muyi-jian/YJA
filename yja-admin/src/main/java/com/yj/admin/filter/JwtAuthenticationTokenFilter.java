package com.yj.admin.filter;

import com.github.xiaoymin.knife4j.core.util.StrUtil;
import com.yj.admin.util.JWTutil;
import com.yj.common.enums.ResponseEnum;
import com.yj.common.exception.YJAException;
import com.yj.common.resp.ResponseUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author Yang Jian
 * @date 2024/4/4 21:00
 * @description
 */
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取token

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        System.out.println("token============="+token);

        try {
            if (StrUtil.isNotBlank(token)) {
                token= JWTutil.parseToken(token);
                JWTutil.tokenVerify(token);
            }
        } catch (YJAException ex) {
            //this is very important, since it guarantees the user is not authenticated at all
            SecurityContextHolder.clearContext();
            ResponseUtils.writeErrMsg(response, (ResponseEnum) ex.getResultCode());
            return;
        }

        filterChain.doFilter(request,response);
    }
}