package com.yj.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yj.core.model.entity.SysRole;

import java.util.Set;

/**
 * 角色业务接口层
 * @author Yang Jian
 * @date 2024/4/4 16:51
 * @description
 */
public interface SysRoleService extends IService<SysRole> {
    /**
     * 获取最大范围的数据权限
     *
     * @param roles
     * @return
     */
    Integer getMaximumDataScope(Set<String> roles);
}
