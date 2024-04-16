package com.yj.admin.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yj.admin.plugin.easyexcel.UserImportListener;
import com.yj.admin.service.SysUserService;
import com.yj.common.annotation.ResponseResultBody;
import com.yj.common.resp.PageResponseObject;
import com.yj.common.resp.ResponseObject;
import com.yj.admin.util.ExcelUtils;
import com.yj.core.model.entity.SysUser;
import com.yj.core.model.form.UserForm;
import com.yj.core.model.query.UserPageQuery;
import com.yj.core.model.vo.UserExportVO;
import com.yj.core.model.vo.UserImportVO;
import com.yj.core.model.vo.UserInfoVO;
import com.yj.core.model.vo.UserPageVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * 用户控制器
 * @author Yang Jian
 * @date 2024/4/4 16:17
 * @description
 */
@Tag(name = "用户接口")
@RestController
@RequestMapping("/api/users")
@ResponseResultBody
public class SysUserController {

    @Autowired
    private SysUserService userService;

    @Operation(summary = "获取当前登录用户信息")
    @GetMapping("/me")
    public ResponseObject<UserInfoVO> getCurrentUserInfo() {
        UserInfoVO userInfoVO = userService.getCurrentUserInfo();
        return ResponseObject.success(userInfoVO);
    }

    @Operation(summary = "用户分页列表")
    @GetMapping("/page")
    public PageResponseObject<UserPageVO> getUserPage(
            @ParameterObject UserPageQuery queryParams
    ) {

        IPage<UserPageVO> result = userService.getUserPage(queryParams);
        return PageResponseObject.success(result);
    }

    @Operation(summary = "新增用户")
    @PostMapping
    public boolean saveUser(
            @RequestBody @Valid UserForm userForm
    ) {
        return userService.saveUser(userForm);
    }

    @Operation(summary = "用户表单数据")
    @GetMapping("/{userId}/form")
    public UserForm getUserForm(
            @Parameter(description = "用户ID") @PathVariable String userId
    ) {
        return userService.getUserFormData(userId);
    }

    @Operation(summary = "修改用户")
    @PutMapping(value = "/{userId}")
    public boolean updateUser(
            @Parameter(description = "用户ID") @PathVariable String userId,
            @RequestBody @Validated UserForm userForm) {
        return userService.updateUser(userId, userForm);
    }

    @Operation(summary = "删除用户")
    @DeleteMapping("/{ids}")
    public boolean deleteUsers(
            @Parameter(description = "用户ID，多个以英文逗号(,)分割") @PathVariable String ids
    ) {
        return userService.deleteUsers(ids);
    }

    @Operation(summary = "修改用户密码")
    @PatchMapping(value = "/{userId}/password")
    public boolean updatePassword(
            @Parameter(description = "用户ID") @PathVariable String userId,
            @RequestParam String password
    ) {
        return userService.updatePassword(userId, password);
    }

    @Operation(summary = "修改用户状态")
    @PatchMapping(value = "/{userId}/status")
    public boolean updateUserStatus(
            @Parameter(description = "用户ID") @PathVariable String userId,
            @Parameter(description = "用户状态(1:启用;0:禁用)") @RequestParam Integer status
    ) {
        boolean result = userService.update(new LambdaUpdateWrapper<SysUser>()
                .eq(SysUser::getId, userId)
                .set(SysUser::getStatus, status)
        );
        return result;
    }


    @Operation(summary = "用户导入模板下载")
    @GetMapping("/template")
    public void downloadTemplate(HttpServletResponse response) throws IOException {
        String fileName = "用户导入模板.xlsx";
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));

        String fileClassPath = "excel-templates" + File.separator + fileName;
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileClassPath);

        ServletOutputStream outputStream = response.getOutputStream();
        ExcelWriter excelWriter = EasyExcel.write(outputStream).withTemplate(inputStream).build();

        excelWriter.finish();
    }

    @Operation(summary = "导入用户")
    @PostMapping("/_import")
    public String importUsers(@Parameter(description = "部门ID") String deptId, MultipartFile file) throws IOException {
        UserImportListener listener = new UserImportListener(deptId);
        String msg = ExcelUtils.importExcel(file.getInputStream(), UserImportVO.class, listener);
        return msg;
    }

    @Operation(summary = "导出用户")
    @GetMapping("/_export")
    public void exportUsers(UserPageQuery queryParams, HttpServletResponse response) throws IOException {
        String fileName = "用户列表.xlsx";
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));

        List<UserExportVO> exportUserList = userService.listExportUsers(queryParams);
        EasyExcel.write(response.getOutputStream(), UserExportVO.class).sheet("用户列表")
                .doWrite(exportUserList);
    }

}
