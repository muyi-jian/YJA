package com.yj.admin.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yj.admin.mapper.SysUserMapper;
import com.yj.admin.service.SysMenuService;
import com.yj.admin.service.SysRoleService;
import com.yj.admin.service.SysUserRoleService;
import com.yj.admin.service.SysUserService;
import com.yj.admin.util.SecurityUtils;
import com.yj.core.common.constant.SystemConstants;
import com.yj.core.common.util.DateUtils;
import com.yj.core.converter.UserConverter;
import com.yj.core.model.bo.UserBO;
import com.yj.core.model.bo.UserFormBO;
import com.yj.core.model.dto.UserAuthInfo;
import com.yj.core.model.entity.SysUser;
import com.yj.core.model.form.UserForm;
import com.yj.core.model.query.UserPageQuery;
import com.yj.core.model.vo.UserExportVO;
import com.yj.core.model.vo.UserInfoVO;
import com.yj.core.model.vo.UserPageVO;
import com.yj.core.security.model.SysUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    private final SysUserRoleService userRoleService;

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
    public UserForm getUserFormData(String userId) {
        UserFormBO userFormBO = this.baseMapper.getUserDetail(userId);
        // 实体转换po->form
        UserForm userForm = userConverter.bo2Form(userFormBO);
        return userForm;
    }

    /**
     * 获取用户分页列表
     *
     * @param queryParams
     * @return
     */
    @Override
    public IPage<UserPageVO> getUserPage(UserPageQuery queryParams) {

        // 参数构建
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        Page<UserBO> page = new Page<>(pageNum, pageSize);

        // 格式化为数据库日期格式，避免日期比较使用格式化函数导致索引失效
        DateUtils.toDatabaseFormat(queryParams, "startTime", "endTime");

        System.out.println("page==========="+page);
        System.out.println("queryParams==========="+queryParams);

        // 查询数据
        Page<UserBO> userPage = this.baseMapper.getUserPage(page, queryParams);
        System.out.println("userPage=="+userPage);
        // 实体转换
        return userConverter.toPageVo(userPage);
    }


    /**
     * 新增用户
     *
     * @param userForm 用户表单对象
     * @return
     */
    @Override
    public boolean saveUser(UserForm userForm) {

        String username = userForm.getUsername();

        long count = this.count(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, username));
        Assert.isTrue(count == 0, "用户名已存在");

        // 实体转换 form->entity
        SysUser entity = userConverter.form2Entity(userForm);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // 设置默认加密密码
        String defaultEncryptPwd = passwordEncoder.encode(SystemConstants.DEFAULT_PASSWORD);
        entity.setPassword(defaultEncryptPwd);

        // 新增用户
        boolean result = this.save(entity);

        if (result) {
            // 保存用户角色
            userRoleService.saveUserRoles(entity.getId(), userForm.getRoleIds());
        }
        return result;
    }

    /**
     * 更新用户
     *
     * @param userId   用户ID
     * @param userForm 用户表单对象
     * @return
     */
    @Override
    @Transactional
    public boolean updateUser(String userId, UserForm userForm) {

        String username = userForm.getUsername();

        long count = this.count(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getUsername, username)
                .ne(SysUser::getId, userId)
        );
        Assert.isTrue(count == 0, "用户名已存在");

        // form -> entity
        SysUser entity = userConverter.form2Entity(userForm);

        // 修改用户
        boolean result = this.updateById(entity);

        if (result) {
            // 保存用户角色
            userRoleService.saveUserRoles(entity.getId(), userForm.getRoleIds());
        }
        return result;
    }

    /**
     * 删除用户
     *
     * @param idsStr 用户ID，多个以英文逗号(,)分割
     * @return
     */
    @Override
    public boolean deleteUsers(String idsStr) {
        Assert.isTrue(StrUtil.isNotBlank(idsStr), "删除的用户数据为空");
        // 逻辑删除
        List<String> ids = Arrays.stream(idsStr.split(","))
                .collect(Collectors.toList());
        return this.removeByIds(ids);

    }

    /**
     * 修改用户密码
     *
     * @param userId   用户ID
     * @param password 用户密码
     * @return true|false
     */
    @Override
    public boolean updatePassword(String userId, String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return this.update(new LambdaUpdateWrapper<SysUser>()
                .eq(SysUser::getId, userId)
                .set(SysUser::getPassword, passwordEncoder.encode(password))
        );
    }



    /**
     * 获取导出用户列表
     *
     * @param queryParams
     * @return
     */
    @Override
    public List<UserExportVO> listExportUsers(UserPageQuery queryParams) {
        List<UserExportVO> list = this.baseMapper.listExportUsers(queryParams);
        return list;
    }


}
