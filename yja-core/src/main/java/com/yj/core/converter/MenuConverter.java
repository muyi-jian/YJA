package com.yj.core.converter;


import com.yj.core.model.entity.SysMenu;
import com.yj.core.model.form.MenuForm;
import com.yj.core.model.vo.MenuVO;
import org.mapstruct.Mapper;

/**
 * 菜单对象转换器
 *
 * @author Yang Jian
 */
@Mapper(componentModel = "spring")
public interface MenuConverter {

    MenuVO entity2Vo(SysMenu entity);

    MenuForm entity2Form(SysMenu entity);

    SysMenu form2Entity(MenuForm menuForm);

}