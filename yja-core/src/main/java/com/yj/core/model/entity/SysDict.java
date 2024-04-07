package com.yj.core.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * 字典数据表
 *
 * @author Ynag Jian
 */
@Data
public class SysDict implements Serializable {

    /**
     * 主键
     */
    private String id;
    /**
     * 字典类型编码
     */
    private String typeCode;

    /**
     * 字典项名称
     */
    private String name;

    /**
     * 字典项值
     */
    private String value;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 状态(1:正常;0:禁用)
     */
    private Integer status;

    /**
     * 是否默认(1:是;0:否)
     */
    private Integer defaulted;

    /**
     * 备注
     */
    private String remark;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}