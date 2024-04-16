package com.yj.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yj.core.model.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户角色访问层
 * @author Yang Jian
 * @date 2024/4/4 16:57
 * @description
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
    /**
     * 获取角色绑定的用户数
     *
     * @param roleId 角色ID
     */
    int countUsersForRole(String roleId);
}
