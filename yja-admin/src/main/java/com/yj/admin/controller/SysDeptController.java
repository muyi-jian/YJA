package com.yj.admin.controller;

import com.yj.admin.service.SysDeptService;
import com.yj.common.annotation.ResponseResultBody;
import com.yj.core.common.model.Option;
import com.yj.core.model.form.DeptForm;
import com.yj.core.model.query.DeptQuery;
import com.yj.core.model.vo.DeptVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门控制器
 * @author Yang Jian
 * @date 2024/4/4 16:17
 * @description
 */
@Tag(name = "部门接口")
@RestController
@RequestMapping("/api/dept")
@ResponseResultBody
public class SysDeptController {
    @Autowired
    private SysDeptService deptService;

    @Operation(summary = "获取部门下拉选项")
    @GetMapping("/options")
    public List<Option> listDeptOptions() {
        return deptService.listDeptOptions();
    }
    @Operation(summary = "获取部门列表")
    @GetMapping
    public List<DeptVO> listDepartments(
            @ParameterObject DeptQuery queryParams
    ) {
        return deptService.listDepartments(queryParams);
    }

    @Operation(summary = "获取部门表单数据")
    @GetMapping("/{deptId}/form")
    public DeptForm getDeptForm(
            @Parameter(description ="部门ID") @PathVariable String deptId
    ) {
        return deptService.getDeptForm(deptId);
    }

    @Operation(summary = "新增部门")
    @PostMapping
    @PreAuthorize("@ss.hasPerm('sys:dept:add')")
    public String saveDept(
            @Valid @RequestBody DeptForm formData
    ) {
        return deptService.saveDept(formData);
    }

    @Operation(summary = "修改部门")
    @PutMapping(value = "/{deptId}")
    public String updateDept(
            @PathVariable String deptId,
            @Valid @RequestBody DeptForm formData
    ) {
        return deptService.updateDept(deptId, formData);
    }

    @Operation(summary = "删除部门")
    @DeleteMapping("/{ids}")
    public boolean deleteDepartments(
            @Parameter(description ="部门ID，多个以英文逗号(,)分割") @PathVariable("ids") String ids
    ) {
        return  deptService.deleteByIds(ids);
    }

}
