package com.yj.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yj.core.model.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * 角色持久层
 * @author Yang Jian
 * @date 2024/4/4 16:57
 * @description
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
    /**
     * 获取最大范围的数据权限
     *
     * @param roles
     * @return
     */
    Integer getMaximumDataScope(@Param("roles")  Set<String> roles);
}
