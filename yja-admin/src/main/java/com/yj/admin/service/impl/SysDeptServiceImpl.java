package com.yj.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yj.admin.mapper.SysDeptMapper;
import com.yj.admin.service.SysDeptService;
import com.yj.core.model.entity.SysDept;
import org.springframework.stereotype.Service;

/**
 * 部门业务实现类
 * @author Yang Jian
 * @date 2024/4/4 17:01
 * @description
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {
}
