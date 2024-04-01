package com.yj.common.enums;

/**
 * 统一返回状态枚举
 * @author Yang Jian
 * @date 2024/3/26 20:25
 * @description
 */
public enum ResponseEnum {
    SUCCESS(0, "成功"),
    FAILURE(-1, "服务器内部错误"),

    //-1xx 服务器错误
    BAD_SQL_GRAMMAR_ERROR(101, "sql语法错误"),
    SERVLET_ERROR(102, "servlet请求异常"), //-2xx 参数校验
    UPLOAD_ERROR(103, "文件上传错误"),
    EXPORT_DATA_ERROR(104, "数据导出失败"),
    DATABASE_OPERATION_FAILED(105, "数据库操作失败"),
    BAD_REQUEST(106, "错误的请求"),


    //-2xx 参数校验
    BORROW_AMOUNT_NULL_ERROR(201, "借款额度不能为空"),
    MOBILE_NULL_ERROR(202, "手机号码不能为空"),
    MOBILE_ERROR(203, "手机号码不正确"),
    PASSWORD_NULL_ERROR(204, "密码不能为空"),
    CODE_NULL_ERROR(205, "验证码不能为空"),
    MOBILE_EXIST_ERROR(207, "手机号已被注册"),
    LOGIN_MOBILE_ERROR(208, "用户不存在"),
    LOGIN_PASSWORD_ERROR(209, "密码错误"),
    LOGIN_LOKED_ERROR(210, "用户被锁定"),
    LOGIN_AUTH_ERROR(211, "未登录"),


    USER_BIND_IDCARD_EXIST_ERROR(301, "身份证号码已绑定"),
    USER_NO_BIND_ERROR(302, "用户未绑定"),
    USER_NO_AMOUNT_ERROR(303, "用户信息未审核"),
    USER_AMOUNT_LESS_ERROR(304, "您的借款额度不足"),
    LEND_INVEST_ERROR(305, "当前状态无法投标"),
    LEND_FULL_SCALE_ERROR(306, "已满标，无法投标"),
    NOT_SUFFICIENT_FUNDS_ERROR(307, "余额不足，请充值"),

    PAY_UNIFIEDORDER_ERROR(401, "统一下单错误"),

    ALIYUN_SMS_LIMIT_CONTROL_ERROR(502, "短信发送过于频繁"),//业务限流
    ALIYUN_SMS_ERROR(503, "短信发送失败"),//其他失败
    AUTH_ERROR(504, "授权失败!"),
    SERVER_BUSY(505, "服务器正忙，请稍后再试!"),

    WEIXIN_CALLBACK_PARAM_ERROR(601, "回调参数不正确"),
    WEIXIN_FETCH_ACCESSTOKEN_ERROR(602, "获取access_token失败"),
    WEIXIN_FETCH_USERINFO_ERROR(603, "获取用户信息失败"),

    NOT_EXIST(701, "请求路径不存在"),


    /**
     * 通用操作码
     */
    OPS_REPEAT(110001,"重复操作"),

    /**
     *验证码
     */
    CODE_TO_ERROR(240001,"接收号码不合规"),
    CODE_LIMITED(240002,"验证码发送过快"),
    CODE_ERROR(240003,"验证码错误"),
    CODE_CAPTCHA(240101,"图形验证码错误"),

    /**
     * 账号
     */
    ACCOUNT_REPEAT(250001,"账号已经存在"),
    ACCOUNT_UNREGISTER(250002,"账号不存在"),
    ACCOUNT_PWD_ERROR(250003,"账号或者密码错误");

    private int code;
    private String message;

    ResponseEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
