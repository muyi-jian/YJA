package com.yj.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yj.admin.mapper.SysRoleMapper;
import com.yj.admin.service.SysRoleService;
import com.yj.core.model.entity.SysRole;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * 角色业务实现类
 * @author Yang Jian
 * @date 2024/4/4 17:01
 * @description
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    /**
     * 获取最大范围的数据权限
     *
     * @param roles 角色编码集合
     * @return {@link Integer} – 数据权限范围
     */
    @Override
    public Integer getMaximumDataScope(Set<String> roles) {
        Integer dataScope = this.baseMapper.getMaximumDataScope(roles);
        return dataScope;
    }

}
