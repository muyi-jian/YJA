package com.yj.core.model.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.yj.core.common.base.BaseEntity;
import lombok.Data;

/**
 * 部门表
 */
@Data
@TableName(value ="yja_dept")
public class SysDept extends BaseEntity {
    /**
     * 主键
     */
    private String id;
    /**
     * 部门名称
     */
    private String name;

    /**
     * 父节点id
     */
    private Long parentId;

    /**
     * 父节点id路径
     */
    private String treePath;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 状态(1:正常;0:禁用)
     */
    private Integer status;

    /**
     * 逻辑删除标识(1:已删除;0:未删除)
     */
    private Integer deleted;

}