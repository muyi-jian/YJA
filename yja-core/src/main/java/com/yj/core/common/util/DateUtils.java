
package com.yj.core.common.util;


import java.lang.reflect.Field;

/**
 * 日期工具类
 *
 * @author Ynag Jian
 */
public class DateUtils {

    /**
     * 区间日期格式化为数据库日期格式
     * <p>
     * eg：2021-01-01 → 2021-01-01 00:00:00
     *
     * @param obj                要处理的对象
     * @param startTimeFieldName 起始时间字段名
     * @param endTimeFieldName   结束时间字段名
     */
    public static void toDatabaseFormat(Object obj, String startTimeFieldName, String endTimeFieldName) {}

    /**
     * 处理日期字段
     *
     * @param obj           要处理的对象
     * @param field         字段
     * @param fieldName     字段名
     * @param targetPattern 目标数据库日期格式
     */
    private static void processDateTimeField(Object obj, Field field, String fieldName, String targetPattern) {}
}
