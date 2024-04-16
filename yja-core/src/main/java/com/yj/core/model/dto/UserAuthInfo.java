package com.yj.core.model.dto;

import lombok.Data;

import java.util.Set;

/**
 * 用户认证信息
 *
 * @author haoxr
 * @since 2022/10/22
 *
 */
@Data
public class UserAuthInfo {

    private String userId;

    private String username;

    private String nickName;

    private String deptId;

    private String password;

    private Integer status;

    private Set<String> roles;

    private Set<String> perms;

    private Integer dataScope;

}
