package com.yj.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yj.core.model.entity.SysDept;
import org.apache.ibatis.annotations.Mapper;

/**
 * 部门持久层
 * @author Yang Jian
 * @date 2024/4/4 17:07
 * @description
 */
@Mapper
public interface SysDeptMapper extends BaseMapper<SysDept> {
}
