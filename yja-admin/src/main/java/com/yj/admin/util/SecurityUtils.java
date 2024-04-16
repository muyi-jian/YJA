package com.yj.admin.util;

import com.yj.core.common.constant.SystemConstants;
import com.yj.core.security.model.SysUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Yang Jian
 * @date 2024/4/4 18:17
 * @description
 */
public class SecurityUtils {


    /**
     * 获取当前登录人信息
     *
     * @return SysUserDetails
     */
    public static SysUserDetails getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("SysUserDetails-getUser: " + authentication);
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof SysUserDetails) {
                return (SysUserDetails) authentication.getPrincipal();
            }
        }
        return null;
    }

    /**
     * 获取用户ID
     *
     * @return Long
     */
    public static String getUserId() {
        String userId = getUser().getUserId(); // 直接将 Integer 转换为 Long
        return userId;
    }

    /**
     * 获取部门ID
     *
     * @return
     */
    public static String getDeptId() {
        return  getUser().getDeptId(); // 直接将 Integer 转换为 Long;
    }

    /**
     * 获取数据权限范围
     *
     * @return DataScope
     */
    public static Integer getDataScope() {
        return Integer.valueOf(getUser().getDataScope());
    }


    /**
     * 获取用户角色集合
     *
     * @return 角色集合
     */
    public static Set<String> getRoles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            if (authorities!=null && !authorities.isEmpty()) {
                return authorities.stream().filter(item -> item.getAuthority().startsWith("ROLE_"))
                        .map(item ->item.getAuthority().substring("ROLE_".length()))
                        .collect(Collectors.toSet());
            }
        }
        return Collections.EMPTY_SET;
    }

    /**
     * 是否超级管理员
     * <p>
     * 超级管理员忽视任何权限判断
     *
     * @return
     */
    public static boolean isRoot() {
        Set<String> roles = getRoles();
        return roles.contains(SystemConstants.ROOT_ROLE_CODE);
    }


}
