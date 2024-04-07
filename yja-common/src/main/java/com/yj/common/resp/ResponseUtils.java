package com.yj.common.resp;

import com.alibaba.fastjson.JSONObject;
import com.yj.common.enums.ResponseEnum;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

/**
 * 响应工具类
 * @author Yang Jian
 * @date 2024/4/4 21:37
 * @description
 */
public class ResponseUtils {

    /**
     * 异常消息返回(适用过滤器中处理异常响应)
     *
     * @param response
     * @param resultCode
     */
    public static void writeErrMsg(HttpServletResponse response, ResponseEnum responseEnum) throws IOException {
        switch (responseEnum) {
            case ACCESS_UNAUTHORIZED:
            case TOKEN_INVALID:
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                break;
            case TOKEN_ACCESS_FORBIDDEN:
                response.setStatus(HttpStatus.FORBIDDEN.value());
                break;
            default:
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                break;
        }
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(JSONObject.toJSONString(ResponseObject.failure(responseEnum)));
    }



}
