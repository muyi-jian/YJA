package com.yj.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yj.core.model.dto.UserAuthInfo;
import com.yj.core.model.entity.SysUser;
import com.yj.core.model.form.UserForm;
import com.yj.core.model.query.UserPageQuery;
import com.yj.core.model.vo.UserExportVO;
import com.yj.core.model.vo.UserInfoVO;
import com.yj.core.model.vo.UserPageVO;

import java.util.List;

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
    UserForm getUserFormData(String userId);



    /**
     * 用户分页列表
     *
     * @return
     */
    IPage<UserPageVO> getUserPage(UserPageQuery queryParams);



    /**
     * 新增用户
     *
     * @param userForm 用户表单对象
     * @return
     */
    boolean saveUser(UserForm userForm);

    /**
     * 修改用户
     *
     * @param userId   用户ID
     * @param userForm 用户表单对象
     * @return
     */
    boolean updateUser(String userId, UserForm userForm);


    /**
     * 删除用户
     *
     * @param idsStr 用户ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteUsers(String idsStr);


    /**
     * 修改用户密码
     *
     * @param userId   用户ID
     * @param password 用户密码
     * @return
     */
    boolean updatePassword(String userId, String password);




    /**
     * 获取导出用户列表
     *
     * @param queryParams
     * @return
     */
    List<UserExportVO> listExportUsers(UserPageQuery queryParams);





}
