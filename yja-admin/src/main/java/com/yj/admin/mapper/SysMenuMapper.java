package com.yj.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yj.core.model.bo.RouteBO;
import com.yj.core.model.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 菜单持久层
 * @author Yang Jian
 * @date 2024/4/4 16:30
 * @description
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu>{
    List<RouteBO> listRoutes();
    /**
     * 获取角色权限集合
     *
     * @param roles
     * @return
     */
    Set<String> listRolePerms(@Param("roles") Set<String> roles);
}
