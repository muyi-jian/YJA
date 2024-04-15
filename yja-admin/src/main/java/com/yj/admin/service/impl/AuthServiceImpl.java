package com.yj.admin.service.impl;

import com.yj.admin.service.AuthService;
import com.yj.admin.util.CaptchaUtil;
import com.yj.admin.util.JWTutil;
import com.yj.core.model.dto.CaptchaResult;
import com.yj.core.model.dto.LoginResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * @author Yang Jian
 * @date 2024/4/4 16:55
 * @description
 */
@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public LoginResult login(String username, String password) {
        log.info("用户名:{},密码:{}", username, password);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username.toLowerCase().trim(), password);
        log.info("authenticationToken33333::{}",authenticationToken);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        log.info("authentication3333::{}",authentication);
        String accessToken = JWTutil.token(authentication);
        return LoginResult.builder()
                .tokenType("Bearer")
                .accessToken(accessToken)
                .build();

    }

    @Override
    public void logout() {

    }

    @Override
    public CaptchaResult captcha() {
        CaptchaResult captchaResult =  CaptchaUtil.generateVerifyCode();
        return captchaResult;
    }
}
