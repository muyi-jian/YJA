package com.yj.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yj.admin.mapper.SysMenuMapper;
import com.yj.admin.service.SysMenuService;
import com.yj.common.util.StrUtil;
import com.yj.core.common.constant.SystemConstants;
import com.yj.core.common.enums.MenuTypeEnum;
import com.yj.core.common.enums.StatusEnum;
import com.yj.core.model.bo.RouteBO;
import com.yj.core.model.entity.SysMenu;
import com.yj.core.model.vo.RouteVO;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 菜单业务实现类
 * @author Yang Jian
 * @date 2024/4/4 16:28
 * @description
 */
@Service
@RequiredArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {


    //private final MenuConverter menuConverter;

    //private final SysRoleMenuService roleMenuService;

    /**
     * 获取路由列表
     */
    @Override
    public List<RouteVO> listRoutes() {
        System.out.println("listRoutes-SysMenuServiceImpl");
        List<RouteBO> menuList = this.baseMapper.listRoutes();
        return buildRoutes(SystemConstants.ROOT_NODE_ID, menuList);
    }


    /**
     * 递归生成菜单路由层级列表
     *
     * @param parentId 父级ID
     * @param menuList 菜单列表
     * @return 路由层级列表
     */
    private List<RouteVO> buildRoutes(String parentId, List<RouteBO> menuList) {
        List<RouteVO> routeList = new ArrayList<>();

        for (RouteBO menu : menuList) {
            if (menu.getParentId().equals(parentId)) {
                RouteVO routeVO = toRouteVo(menu);
                List<RouteVO> children = buildRoutes(menu.getId(), menuList);
                if (!children.isEmpty()) {
                    routeVO.setChildren(children);
                }
                routeList.add(routeVO);
            }
        }

        return routeList;
    }

    /**
     * 根据RouteBO创建RouteVO
     */
    private RouteVO toRouteVo(RouteBO routeBO) {
        RouteVO routeVO = new RouteVO();
        String routeName = StringUtils.capitalize(StrUtil.toCamelCase(routeBO.getPath(), '-'));  // 路由 name 需要驼峰，首字母大写
        routeVO.setName(routeName); // 根据name路由跳转 this.$router.push({name:xxx})
        routeVO.setPath(routeBO.getPath()); // 根据path路由跳转 this.$router.push({path:xxx})
        routeVO.setRedirect(routeBO.getRedirect());
        routeVO.setComponent(routeBO.getComponent());

        RouteVO.Meta meta = new RouteVO.Meta();
        meta.setTitle(routeBO.getName());
        meta.setIcon(routeBO.getIcon());
        meta.setRoles(routeBO.getRoles());
        meta.setHidden(StatusEnum.DISABLE.getValue().equals(routeBO.getVisible()));
        // 【菜单】是否开启页面缓存
        if (MenuTypeEnum.MENU.equals(routeBO.getType())
                && routeBO.getKeepAlive() == 1) {
            meta.setKeepAlive(true);
        }
        // 【目录】只有一个子路由是否始终显示
        if (MenuTypeEnum.CATALOG.equals(routeBO.getType())
                && routeBO.getAlwaysShow() == 1) {
            meta.setAlwaysShow(true);
        }

        routeVO.setMeta(meta);
        return routeVO;
    }

    /**
     * 获取角色权限(Code)集合
     *
     * @param roles 角色Code集合
     * @return 权限集合
     */
    @Override
    public Set<String> listRolePerms(Set<String> roles) {
        // 输出 roles
        System.out.println("listRolePerms-roles = " + roles);
        Set<String> rt = this.baseMapper.listRolePerms(roles);
        System.out.println("listRolePerms-roles-rt = " + rt);
        return rt;
    }

}
