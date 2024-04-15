package com.yj.admin.service;

import com.yj.core.model.dto.CaptchaResult;
import com.yj.core.model.dto.LoginResult;

/**
 * 认证服务接口
 * @author Yang Jian
 * @date 2024/4/4 16:54
 * @description
 */
public interface AuthService {
    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录结果
     */
    LoginResult login(String username, String password);

    /**
     * 登出
     */
    void logout();

    CaptchaResult captcha();
}
