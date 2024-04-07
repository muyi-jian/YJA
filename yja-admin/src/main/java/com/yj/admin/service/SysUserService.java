package com.yj.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yj.core.model.dto.UserAuthInfo;
import com.yj.core.model.entity.SysUser;
import com.yj.core.model.form.UserForm;
import com.yj.core.model.vo.UserInfoVO;

/**
 * 用户业务接口
 * @author Yang Jian
 * @date 2024/4/4 16:52
 * @description
 */
public interface SysUserService extends IService<SysUser> {
    /**
     * 获取登录用户信息
     *
     * @return
     */
    UserInfoVO getCurrentUserInfo();

    /**
     * 根据用户名获取认证信息
     *
     * @param username 用户名
     * @return {@link UserAuthInfo}
     */

    UserAuthInfo getUserAuthInfo(String username);

    /**
     * 获取用户表单数据
     *
     * @param userId
     * @return
     */
    UserForm getUserFormData(Long userId);

}
