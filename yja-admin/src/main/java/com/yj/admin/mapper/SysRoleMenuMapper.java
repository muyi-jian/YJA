package com.yj.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yj.core.model.entity.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色菜单访问层
 * @author Yang Jian
 * @date 2024/4/4 16:57
 * @description
 */
@Mapper
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {
}
