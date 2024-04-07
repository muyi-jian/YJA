package com.yj.core.common.enums;

import com.yj.core.common.base.IBaseEnum;
import lombok.Getter;

/**
 * 状态枚举
 *
 * @author Ynag Jian
 */
public enum StatusEnum implements IBaseEnum<Integer> {

    ENABLE(1, "启用"),
    DISABLE (0, "禁用");

    @Getter
    private Integer value;

    @Getter
    private String label;

    StatusEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }
}
