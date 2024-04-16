package com.yj.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yj.core.common.model.Option;
import com.yj.core.model.entity.SysDept;
import com.yj.core.model.form.DeptForm;
import com.yj.core.model.query.DeptQuery;
import com.yj.core.model.vo.DeptVO;

import java.util.List;

/**
 * 部门业务接口
 * @author Yang Jian
 * @date 2024/4/4 16:53
 * @description
 */
public interface SysDeptService extends IService<SysDept> {

    /**
     * 部门列表
     *
     * @return
     */
    List<DeptVO> listDepartments(DeptQuery queryParams);

    /**
     * 部门树形下拉选项
     *
     * @return
     */
    List<Option> listDeptOptions();

    /**
     * 新增部门
     *
     * @param formData
     * @return
     */
    String saveDept(DeptForm formData);

    /**
     * 修改部门
     *
     * @param deptId
     * @param formData
     * @return
     */
    String updateDept(String deptId, DeptForm formData);

    /**
     * 删除部门
     *
     * @param ids 部门ID，多个以英文逗号,拼接字符串
     * @return
     */
    boolean deleteByIds(String ids);

    /**
     * 获取部门详情
     *
     * @param deptId
     * @return
     */
    DeptForm getDeptForm(String deptId);

}
