package com.yj.admin.util;

import com.alibaba.excel.EasyExcel;
import com.yj.admin.plugin.easyexcel.MyAnalysisEventListener;

import java.io.InputStream;

/**
 * Excel 工具类
 *
 * @author Ynag Jian
 */
public class ExcelUtils {
    public static <T> String importExcel(InputStream is, Class clazz, MyAnalysisEventListener<T> listener) {
        EasyExcel.read(is, clazz, listener).sheet().doRead();
        return listener.getMsg();
    }
}
