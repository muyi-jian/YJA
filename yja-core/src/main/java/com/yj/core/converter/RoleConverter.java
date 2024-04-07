package com.yj.core.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yj.core.common.model.Option;
import com.yj.core.model.entity.SysRole;
import com.yj.core.model.form.RoleForm;
import com.yj.core.model.vo.RolePageVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * 角色对象转换器
 *
 * @author Yang Jian
 */
@Mapper(componentModel = "spring")
public interface RoleConverter {

    Page<RolePageVO> entity2Page(Page<SysRole> page);

    @Mappings({
            @Mapping(target = "value", source = "id"),
            @Mapping(target = "label", source = "rolename")
    })
    Option entity2Option(SysRole role);


    List<Option> entities2Options(List<SysRole> roles);

    SysRole form2Entity(RoleForm roleForm);

    RoleForm entity2Form(SysRole entity);
}