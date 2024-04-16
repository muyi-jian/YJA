package com.yj.admin.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yj.admin.mapper.SysUserRoleMapper;
import com.yj.admin.service.SysUserRoleService;
import com.yj.core.model.entity.SysUserRole;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户角色业务实现类
 * @author Yang Jian
 * @date 2024/4/4 17:01
 * @description
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    /**
     * 保存用户角色
     *
     * @param userId
     * @param roleIds
     * @return
     */
    @Override
    public boolean saveUserRoles(String userId, List<String> roleIds) {

        if (userId == null || CollectionUtil.isEmpty(roleIds)) {
            return false;
        }

        // 用户原角色ID集合
        List<String> userRoleIds = this.list(new LambdaQueryWrapper<SysUserRole>()
                        .eq(SysUserRole::getUserId, userId))
                .stream()
                .map(SysUserRole::getRoleId)
                .collect(Collectors.toList());

        // 新增用户角色
        List<String> saveRoleIds;
        if (CollectionUtil.isEmpty(userRoleIds)) {
            saveRoleIds = roleIds;
        } else {
            saveRoleIds = roleIds.stream()
                    .filter(roleId -> !userRoleIds.contains(roleId))
                    .collect(Collectors.toList());
        }

        List<SysUserRole> saveUserRoles = saveRoleIds
                .stream()
                .map(roleId -> new SysUserRole(userId, roleId))
                .collect(Collectors.toList());
        this.saveBatch(saveUserRoles);

        // 删除用户角色
        if (CollectionUtil.isNotEmpty(userRoleIds)) {
            List<String> removeRoleIds = userRoleIds.stream()
                    .filter(roleId -> !roleIds.contains(roleId))
                    .collect(Collectors.toList());

            if (CollectionUtil.isNotEmpty(removeRoleIds)) {
                this.remove(new LambdaQueryWrapper<SysUserRole>()
                        .eq(SysUserRole::getUserId, userId)
                        .in(SysUserRole::getRoleId, removeRoleIds)
                );
            }
        }
        return true;

    }

    /**
     * 判断角色是否存在绑定的用户
     *
     * @param roleId 角色ID
     * @return true：已分配 false：未分配
     */
    @Override
    public boolean hasAssignedUsers(String roleId) {
        int count = this.baseMapper.countUsersForRole(roleId);
        return count > 0;
    }
}
