package com.yj.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yj.core.model.dto.UserAuthInfo;
import com.yj.core.model.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户持久层
 * @author Yang Jian
 * @date 2024/4/4 16:57
 * @description
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * 根据用户名获取认证信息
     *
     * @param username
     * @return
     */
    UserAuthInfo getUserAuthInfo(String username);
    /**
     * 获取用户表单详情
     *
     * @param userId 用户ID
     * @return
     */
    //UserFormBO getUserDetail(Long userId);
}
