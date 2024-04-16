package com.yj.admin.plugin.dupsubmit.aspect;

import com.yj.admin.plugin.dupsubmit.annotation.PreventDuplicateSubmit;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 处理重复提交的切面
 *
 * @author haoxr
 * @since 2.3.0
 */
//@Aspect
//@Component
//@Slf4j
//@RequiredArgsConstructor
public class DuplicateSubmitAspect {

    private static final String RESUBMIT_LOCK_PREFIX = "LOCK:RESUBMIT:";

    /**
     * 防重复提交切点
     */
    @Pointcut("@annotation(preventDuplicateSubmit)")
    public void preventDuplicateSubmitPointCut(PreventDuplicateSubmit preventDuplicateSubmit) {
        //log.info("定义防重复提交切点");
    }

    @Around("preventDuplicateSubmitPointCut(preventDuplicateSubmit)")
    public Object doAround(ProceedingJoinPoint pjp, PreventDuplicateSubmit preventDuplicateSubmit) throws Throwable {

        String resubmitLockKey = generateResubmitLockKey();

        return pjp.proceed();
    }


    /**
     * 获取重复提交锁的 key
     */
    private String generateResubmitLockKey() {
        String resubmitLockKey = null;
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        return resubmitLockKey;
    }

}
