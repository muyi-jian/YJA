package com.yj.core.model.entity;


import com.yj.core.common.base.BaseEntity;
import lombok.Data;

/**
 * 字典类型实体
 *
 * @author Ynag Jian
 */
@Data
public class SysDictType extends BaseEntity {

    /**
     * 主键
     */
    private String id;
    /**
     * 类型名称
     */
    private String name;

    /**
     * 类型编码
     */
    private String code;

    /**
     * 状态(0:正常;1:禁用)
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

}