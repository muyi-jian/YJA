package com.yj.core.model.bo;

import lombok.Data;

import java.util.List;

/**
 * user表单持久化对象
 *
 * @author Yang Jian
 */
@Data
public class UserFormBO {

    /**
     * 用户ID
     */
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 性别(1:男;2:女)
     */
    private Integer gender;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 状态(1:启用;0:禁用)
     */
    private Integer status;

    /**
     * 部门ID
     */
    private String deptId;

    /**
     * 角色ID集合
     */
    private List<String> roleIds;

}
