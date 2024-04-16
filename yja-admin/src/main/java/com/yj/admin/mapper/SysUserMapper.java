package com.yj.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yj.core.model.bo.UserBO;
import com.yj.core.model.bo.UserFormBO;
import com.yj.core.model.dto.UserAuthInfo;
import com.yj.core.model.entity.SysUser;
import com.yj.core.model.query.UserPageQuery;
import com.yj.core.model.vo.UserExportVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
    UserFormBO getUserDetail(String userId);


    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    Page<UserBO> getUserPage(@Param("page") Page<UserBO> page,@Param("queryParams") UserPageQuery queryParams);

    /**
     * 获取导出用户列表
     *
     * @param queryParams
     * @return
     */
    List<UserExportVO> listExportUsers(@Param("queryParams") UserPageQuery queryParams);
}
