package com.yj.core.model.entity;

import com.yj.core.common.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 角色和菜单关联表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysRoleMenu extends BaseEntity {
    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 菜单ID
     */
    private String menuId;
}