package com.yj.core.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.yj.core.common.base.BaseEntity;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户表
 */
@Data
@TableName(value ="yja_user")
public class SysUser extends BaseEntity {
    /**
     * 主键
     */
    private String id;
    /**
     * 用户账号
     */
    private String userid;
    /**
     * 用户名
     */
    private String username;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 部门ID
     */
    private String deptId;
    /**
     * 密码
     */
    private String password;
    /**
     * 性别((1:男;2:女))
     */
    private Integer gender;

    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 联系方式
     */
    private String mobile;
    /**
     * 生日
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime birthday;
    /**
     * 邮箱验证码
     */
    private String validCode;
    /**
     * 个人简介
     */
    private String summary;
    /**
     * 登录次数
     */
    private Integer loginCount;
    /**
     * 最后登录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastLoginTime;
    /**
     * 最后登录ip
     */
    private String lastLoginIp;

    /**
     * 用户状态((1:正常;0:禁用))
     */
    private Integer status;

    /**
     * 资料来源
     */
    private String source;
    /**
     * QQ号
     */
    private String qqNumber;
    /**
     * 微信号
     */
    private String weChat;
    /**
     * 职业
     */
    private String occupation;
    /**
     * 评论状态;(1:正常;0:禁言)
     */
    private String commentStatus;
    /**
     * ip来源
     */
    private String ipSource;
    /**
     * 浏览器
     */
    private String browser;
    /**
     * 操作系统
     */
    private String os;
    /**
     * 是否开启邮件通知;(0:关闭;1:开启)
     */
    private String startEmailNotification;
    /**
     * 用户标签;(0:普通用户;1:管理员;2:博主 等)
     */
    private String useTag;
    /**
     * 是否通过加载校验;(0:未通过;1:已通过)
     */
    private String loadingValid;

    /**
     * 逻辑删除标识(0:未删除;1:已删除)
     */
    private Integer deleted;

}