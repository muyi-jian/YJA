package com.yj.core.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yj.core.common.base.BaseEntity;
import lombok.Data;

/**
 * 角色表
 */
@TableName(value ="yja_role")
@Data
public class SysRole extends BaseEntity {

    /**
     * 主键
     */
    private String id;
    /**
     * 角色名称
     */
    private String rolename;

    /**
     * 角色编码
     */
    private String roleno;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 角色状态(1-正常；0-停用)
     */
    private Integer status;
    /**
     * 简介
     */
    private Integer summary;

    /**
     * 逻辑删除标识(0-未删除；1-已删除)
     */
    private Integer deleted;

    /**
     * 数据权限
     */
    //private Integer dataScope;
}