package com.yj.admin.util;

import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.base.Captcha;
import com.yj.core.common.constant.SecurityConstants;
import com.yj.core.model.dto.CaptchaResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.UUID;


/**
 * @author Yang Jian
 * @date 2024/4/9 10:01
 * @description
 */
public class CaptchaUtil {
    //private final RedisTemplate<String, Object> redisTemplate;

    public static CaptchaResult generateVerifyCode() {
        // 创建验证码对象
        Captcha captcha = new ArithmeticCaptcha();

        // 生成验证码编号
        String captchaKey = UUID.randomUUID().toString();
        String verifyCode = captcha.text();

        // 获取验证码图片，构造响应结果
        //CaptchaResult imageBase64Data =  new CaptchaResult(captchaKey, captcha.toBase64(), verifyCode);

        // 存入Redis，设置120s过期
        //redisTemplate.opsForValue().set(verifyCodeKey, verifyCode, 120, TimeUnit.SECONDS);
        // 存入session，设置120s过期
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpSession session = attributes.getRequest().getSession();
        session.setAttribute(SecurityConstants.CAPTCHA_CODE_PREFIX+captchaKey, verifyCode);
        // 超时后删除验证码缓存

        return CaptchaResult.builder()
                .captchaKey(captchaKey)
                .captchaBase64( captcha.toBase64())
                .text(verifyCode)
                .build();
    }
}
