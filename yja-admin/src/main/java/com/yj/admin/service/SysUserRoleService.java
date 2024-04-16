package com.yj.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yj.core.model.entity.SysUserRole;

import java.util.List;

/**
 * 用户角色业务接口
 * @author Yang Jian
 * @date 2024/4/4 16:51
 * @description
 */
public interface SysUserRoleService extends IService<SysUserRole> {


    /**
     * 保存用户角色
     *
     * @param userId
     * @param roleIds
     * @return
     */
    boolean saveUserRoles(String userId, List<String> roleIds);

    /**
     * 判断角色是否存在绑定的用户
     *
     * @param roleId 角色ID
     * @return true：已分配 false：未分配
     */
    boolean hasAssignedUsers(String roleId);

}
