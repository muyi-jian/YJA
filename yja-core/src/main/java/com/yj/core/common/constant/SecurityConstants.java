package com.yj.core.common.constant;

/**
 * 缓存常量
 *
 * @author Ynag Jian
 */
public interface SecurityConstants {

    /**
     * 验证码缓存前缀
     */
    String CAPTCHA_CODE_PREFIX = "captcha_code:";

    /**
     * 角色和权限缓存前缀
     */
    String ROLE_PERMS_PREFIX = "role_perms:";

    /**
     * 黑名单Token缓存前缀
     */
    String BLACKLIST_TOKEN_PREFIX = "token:blacklist:";

    /**
     * 登录接口路径
     */
    String LOGIN_PATH = "/api/admin/login";
}
