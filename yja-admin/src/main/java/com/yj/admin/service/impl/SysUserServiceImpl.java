package com.yj.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yj.admin.mapper.SysUserMapper;
import com.yj.admin.service.SysMenuService;
import com.yj.admin.service.SysRoleService;
import com.yj.admin.service.SysUserService;
import com.yj.admin.util.SecurityUtils;
import com.yj.core.converter.UserConverter;
import com.yj.core.model.dto.UserAuthInfo;
import com.yj.core.model.entity.SysUser;
import com.yj.core.model.form.UserForm;
import com.yj.core.model.vo.UserInfoVO;
import com.yj.core.security.model.SysUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * 用户业务实现
 * @author Yang Jian
 * @date 2024/4/4 17:01
 * @description
 */
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    private final UserConverter userConverter;
    private final SysMenuService menuService;
    private final SysRoleService roleService;

    /**
     * 获取登录用户信息
     *
     * @return {@link UserInfoVO}   用户信息
     */
    @Override
    public UserInfoVO getCurrentUserInfo() {
        SysUserDetails sysUserDetails = SecurityUtils.getUser();
        String username = sysUserDetails.getUsername(); // 登录用户名

        // 获取登录用户基础信息
        SysUser user = this.getOne(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getUsername, username)
                .select(
                        SysUser::getId,
                        SysUser::getUsername,
                        SysUser::getNickName,
                        SysUser::getAvatar
                )
        );
        // entity->VO
        UserInfoVO userInfoVO = userConverter.toUserInfoVo(user);

        // 用户角色集合
        Set<String> roles = SecurityUtils.getRoles();
        userInfoVO.setRoles(roles);

        // 用户权限集合
        if (roles!=null && !roles.isEmpty()) {
            Set<String> perms = new HashSet<>();
            userInfoVO.setPerms(perms);
        }
        return userInfoVO;
    }


    /**
     * 根据用户名获取认证信息
     *
     * @param username 用户名
     * @return 用户认证信息 {@link UserAuthInfo}
     */
    @Override
    public UserAuthInfo getUserAuthInfo(String username) {
        UserAuthInfo userAuthInfo = this.baseMapper.getUserAuthInfo(username);
        System.out.println("userAuthInfo=11333333333333==="+userAuthInfo);

        if (userAuthInfo != null) {
            Set<String> roles = userAuthInfo.getRoles();
            if (roles != null && !roles.isEmpty()) {
                Set<String> perms = menuService.listRolePerms(roles);
                userAuthInfo.setPerms(perms);
            }

            // 获取最大范围的数据权限
            Integer dataScope = roleService.getMaximumDataScope(roles);
            userAuthInfo.setDataScope(dataScope);
        }
        return userAuthInfo;
    }
    /**
     * 获取用户详情
     *
     * @param userId
     * @return
     */
    @Override
    public UserForm getUserFormData(Long userId) {
        //UserFormBO userFormBO = this.baseMapper.getUserDetail(userId);
        //// 实体转换po->form
        //UserForm userForm = userConverter.bo2Form(userFormBO);
        //return userForm;
        return null;
    }

}
