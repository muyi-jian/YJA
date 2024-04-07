package com.yj.core.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yj.core.model.bo.UserBO;
import com.yj.core.model.bo.UserFormBO;
import com.yj.core.model.entity.SysUser;
import com.yj.core.model.form.UserForm;
import com.yj.core.model.vo.UserImportVO;
import com.yj.core.model.vo.UserInfoVO;
import com.yj.core.model.vo.UserPageVO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * 用户对象转换器
 *
 * @author Yang Jian
 */
@Mapper(componentModel = "spring")
public interface UserConverter {

    @Mappings({
            @Mapping(target = "genderLabel", expression = "java(com.yj.core.common.base.IBaseEnum.getLabelByValue(bo.getGender(), com.yj.core.common.enums.GenderEnum.class))")
    })
    UserPageVO toPageVo(UserBO bo);

    Page<UserPageVO> toPageVo(Page<UserBO> bo);

    UserForm bo2Form(UserFormBO bo);

    UserForm entity2Form(SysUser entity);

    @InheritInverseConfiguration(name = "entity2Form")
    SysUser form2Entity(UserForm entity);

    @Mappings({
            @Mapping(target = "userId", source = "id")
    })
    UserInfoVO toUserInfoVo(SysUser entity);

    SysUser importVo2Entity(UserImportVO vo);

}
