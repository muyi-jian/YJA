package com.yj.common.util;

/**
 * @author Yang Jian
 * @date 2024/4/4 17:22
 * @description
 */
public class StrUtil {
    /**
     * 将下划线风格的字符串转换为驼峰风格
     *
     * @param str 输入的字符串，如下划线风格：hello_world
     * @return 转换后的驼峰风格字符串：helloWorld
     */
    public static String toCamelCase(String str, char symbol) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (c == symbol) {
                upperCase = true;
            } else {
                if (upperCase) {
                    sb.append(Character.toUpperCase(c));
                    upperCase = false;
                } else {
                    sb.append(Character.toLowerCase(c));
                }
            }
        }

        return sb.toString();
    }
}
