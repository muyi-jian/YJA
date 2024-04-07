package com.yj.core.model.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 用户导入对象
 *
 * @author Yang Jian
 */
@Data
public class UserImportVO {

    @ExcelProperty(value = "用户名")
    private String username;

    @ExcelProperty(value = "昵称")
    private String nickname;

    @ExcelProperty(value = "性别")
    private String genderLabel;

    @ExcelProperty(value = "手机号码")
    private String mobile;

    @ExcelProperty(value = "邮箱")
    private String email;

    @ExcelProperty("角色")
    private String roleCodes;

}