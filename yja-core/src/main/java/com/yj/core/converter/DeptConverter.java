package com.yj.core.converter;


import com.yj.core.model.entity.SysDept;
import com.yj.core.model.form.DeptForm;
import com.yj.core.model.vo.DeptVO;
import org.mapstruct.Mapper;

/**
 * 部门对象转换器
 *
 * @author Yang Jian
 */
@Mapper(componentModel = "spring")
public interface DeptConverter {

    DeptForm entity2Form(SysDept entity);
    DeptVO entity2Vo(SysDept entity);

    SysDept form2Entity(DeptForm deptForm);

}