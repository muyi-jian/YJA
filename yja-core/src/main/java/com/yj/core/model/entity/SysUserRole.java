package com.yj.core.model.entity;

import com.yj.core.common.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 用户和角色关联表
 *
 * @author Ynag Jian
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUserRole extends BaseEntity {
    /**
     * 用户ID
     */
    private String userId;

    /**
     * 角色ID
     */
    private String roleId;

}