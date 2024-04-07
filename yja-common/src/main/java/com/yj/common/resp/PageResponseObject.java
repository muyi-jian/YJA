package com.yj.common.resp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yj.common.enums.ResponseEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页响应结构体
 *
 * @author Ynag Jian
 */
@Data
public class PageResponseObject<T> implements Serializable {

    private String code;

    private Data<T> data;

    private String msg;

    public static <T> PageResponseObject<T> success(IPage<T> page) {
        PageResponseObject<T> result = new PageResponseObject<>();
        result.setCode(ResponseEnum.SUCCESS.getCode());

        Data data = new Data<T>();
        data.setList(page.getRecords());
        data.setTotal(page.getTotal());

        result.setData(data);
        result.setMsg(ResponseEnum.SUCCESS.getMessage());
        return result;
    }

    @lombok.Data
    public static class Data<T> {

        private List<T> list;

        private long total;

    }

}
