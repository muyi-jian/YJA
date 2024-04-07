package com.yj.admin.controller;

import com.yj.admin.service.SysMenuService;
import com.yj.common.annotation.ResponseResultBody;
import com.yj.common.resp.ResponseObject;
import com.yj.core.model.vo.RouteVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 菜单控制器
 * @author Yang Jian
 * @date 2024/4/4 16:16
 * @description
 */
@Tag(name = "菜单接口")
@RestController
@RequestMapping("/api/menus")
@ResponseResultBody
public class SysMenuController {

    @Autowired
    private SysMenuService menuService;


    @Operation(summary = "路由列表")
    @GetMapping("/routes")
    public ResponseObject<List<RouteVO>> listRoutes() {
        System.out.println("listRoutes-controller");
        List<RouteVO> routeList = menuService.listRoutes();
        return ResponseObject.success(routeList);
    }

}
