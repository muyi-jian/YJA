package com.yj.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yj.core.model.entity.SysMenu;
import com.yj.core.model.vo.RouteVO;

import java.util.List;
import java.util.Set;

/**
 * 菜单业务接口
 * @author Yang Jian
 * @date 2024/4/4 16:27
 * @description
 */
public interface SysMenuService extends IService<SysMenu> {
    /**
     * 获取路由列表
     *
     * @return
     */
    List<RouteVO> listRoutes();
    /**
     * 获取角色权限集合
     *
     * @param roles
     * @return
     */
    Set<String> listRolePerms(Set<String> roles);
}
