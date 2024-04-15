package com.yj.admin.filter;


import com.github.xiaoymin.knife4j.core.util.StrUtil;
import com.yj.common.enums.ResponseEnum;
import com.yj.common.resp.ResponseUtils;
import com.yj.core.common.constant.SecurityConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


/**
 * 图形验证码校验过滤器
 *
 * @author haoxr
 * @since 2022/10/1
 */
public class CaptchaValidationFilter extends OncePerRequestFilter {

    private static final AntPathRequestMatcher LOGIN_PATH_REQUEST_MATCHER = new AntPathRequestMatcher(com.yj.core.security.constant.SecurityConstants.LOGIN_PATH, "POST");

    public static final String CAPTCHA_CODE_PARAM_NAME = "captchaCode";
    public static final String CAPTCHA_KEY_PARAM_NAME = "captchaKey";






    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        // 检验登录接口的验证码
        if (LOGIN_PATH_REQUEST_MATCHER.matches(request)) {
            // 请求中的验证码
            String captchaCode = request.getParameter(CAPTCHA_CODE_PARAM_NAME);
            // TODO 兼容没有验证码的版本(线上请移除这个判断)
            if (StrUtil.isBlank(captchaCode)) {
                chain.doFilter(request, response);
                return;
            }
            // 缓存中的验证码
            String verifyCodeKey = request.getParameter(CAPTCHA_KEY_PARAM_NAME);
            System.out.println(SecurityConstants.CAPTCHA_CODE_PREFIX+verifyCodeKey);
            // 从Session读取验证码并删除缓存
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            assert attributes != null;
            HttpSession session = attributes.getRequest().getSession();
            String cacheVerifyCode = (String) session.getAttribute(SecurityConstants.CAPTCHA_CODE_PREFIX+verifyCodeKey);
            session.removeAttribute(SecurityConstants.CAPTCHA_CODE_PREFIX+verifyCodeKey);
            // 比较用户输入的验证码和缓存中的验证码是否一致，不一致则抛错
            if (!StringUtils.hasText(cacheVerifyCode) || !StringUtils.hasText(captchaCode) || !captchaCode.equalsIgnoreCase(cacheVerifyCode)) {
                ResponseUtils.writeErrMsg(response, ResponseEnum.VERIFY_CODE_CAPTCHA_ERROR);
            }
            chain.doFilter(request, response);
        } else {
            // 非登录接口放行
            chain.doFilter(request, response);
        }

    }

}
