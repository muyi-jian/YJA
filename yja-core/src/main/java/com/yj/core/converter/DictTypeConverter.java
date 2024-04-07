package com.yj.core.converter;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yj.core.model.entity.SysDictType;
import com.yj.core.model.form.DictTypeForm;
import com.yj.core.model.vo.DictTypePageVO;
import org.mapstruct.Mapper;

/**
 * 字典类型对象转换器
 *
 * @author Yang Jian
 */
@Mapper(componentModel = "spring")
public interface DictTypeConverter {

    Page<DictTypePageVO> entity2Page(Page<SysDictType> page);

    DictTypeForm entity2Form(SysDictType entity);

    SysDictType form2Entity(DictTypeForm entity);
}
