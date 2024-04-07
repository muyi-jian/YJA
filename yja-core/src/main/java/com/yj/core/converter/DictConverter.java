package com.yj.core.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yj.core.model.entity.SysDict;
import com.yj.core.model.form.DictForm;
import com.yj.core.model.vo.DictPageVO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

/**
 * 字典数据项对象转换器
 *
 * @author Yang Jian
 */
@Mapper(componentModel = "spring")
public interface DictConverter {

    Page<DictPageVO> entity2Page(Page<SysDict> page);

    DictForm entity2Form(SysDict entity);

    @InheritInverseConfiguration(name="entity2Form")
    SysDict form2Entity(DictForm entity);
}
