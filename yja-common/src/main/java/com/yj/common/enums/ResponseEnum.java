package com.yj.common.enums;

import com.yj.common.resp.IResultCode;

import java.io.Serializable;

/**
 * 统一返回状态枚举
 *
 * @author Yang Jian
 * @date 2024/3/26 20:25
 * @description
 */
public enum ResponseEnum implements IResultCode, Serializable {

    SUCCESS("000000", "成功"),
    FAILURE("999999", "失败"),

    /* 服务器 */
    SYSTEM_BAD_REQUEST("A00001", "错误的请求"),
    SYSTEM_EXECUTION_ERROR("A00002", "系统执行出错"),
    SYSTEM_RESOURCE_ERROR("A00003", "系统资源异常"),
    SYSTEM_RESOURCE_EXHAUSTION("A00004", "系统资源耗尽"),
    SYSTEM_RESOURCE_ACCESS_ERROR("A00005", "系统资源访问异常"),
    SYSTEM_READ_DISK_FILE_ERROR("A00006", "系统读取磁盘文件失败"),
    SYSTEM_EXECUTION_TIMEOUT("A00007", "系统执行超时"),
    SYSTEM_ORDER_PROCESSING_TIMEOUT("A00008", "系统订单处理超时"),
    SYSTEM_DISASTER_RECOVERY_TRIGGER("A00009", "系统容灾功能被出发"),
    SYSTEM_FLOW_LIMITING("A000010", "系统限流"),
    SYSTEM_DEGRADATION("A000011", "系统功能降级"),
    SYSTEM_SERVER_BUSY("A000012", "服务器正忙，请稍后再试!"),


    /* 客户端 */
    CLIENT_USER_ERROR("A100002", "用户端错误"),
    CLIENT_AUTHENTICATION_FAILED("A100003", "客户端认证失败"),


    /* 用户 */
    USER_ACCOUNT_NOT_EXIST("B000001", "用户不存在"),
    USER_ACCOUNT_EXIST("B000002", "账号已经存在"),
    USER_ACCOUNT_LOCKED("B000003", "用户账户被冻结"),
    USER_ACCOUNT_INVALID("B000004", "用户账户已作废"),
    USER_AUTH_ERROR("B000005", "未登录"),
    USER_LOGIN_ERROR("B000006", "用户登录异常"),
    USER_BIND_IDCARD_EXIST_ERROR("B000007", "身份证号码已绑定"),
    USER_NO_BIND_ERROR("B000008", "用户未绑定"),
    USER_NO_AMOUNT_ERROR("B000009", "用户信息未审核"),
    USERNAME_OR_PASSWORD_ERROR("B000010", "用户名或密码错误"),
    USER_PASSWORD_ENTER_EXCEED_LIMIT("B000012", "用户输入密码次数超限"),
    USER_PASSWORD_NULL_ERROR("B100010", "密码不能为空"),
    USER_PASSWORD_ERROR("B100011", "密码错误"),
    /* 验证码 */
    VERIFY_CODE_MOBILE_NULL_ERROR("B200001", "验证码不能为空"),
    VERIFY_CODE_MOBILE_TIMEOUT("B200002", "验证码已过期"),
    VERIFY_CODE_MOBILE_ERROR("B200003", "验证码错误"),
    VERIFY_CODE_MOBILE_TO_ERROR("B200004", "接收号码不合规"),
    VERIFY_CODE_MOBILE_LIMITED("B200005", "验证码发送过快"),
    VERIFY_CODE_CAPTCHA_ERROR("B30000", "图形验证码错误"),
    VERIFY_CODE_CAPTCHA_TIMEOUT("B30001", "验证码已过期"),

    /* 手机 */
    MOBILE_NULL_ERROR("B400001", "手机号码不能为空"),
    MOBILE_ERROR("B400002", "手机号码不正确"),
    MOBILE_EXIST_ERROR("B400003", "手机号已被注册"),
    MOBILE_SMS_LIMIT_CONTROL_ERROR("B50001", "短信发送过于频繁"),
    MOBILE_SMS_ERROR("B500002", "短信发送失败"),


    /* 请求 */
    REQUEST_PARAM_ERROR("C000001", "用户请求参数错误"),
    REQUEST_RESOURCE_NOT_FOUND("C000002", "请求资源不存在"),
    REQUEST_PARAM_IS_NULL("C000003", "请求必填参数为空"),
    REQUEST_NOT_EXIST("C000004", "请求路径不存在"),
    REQUEST_SUBMIT_REPEAT("C000005", "您的请求已提交，请不要重复提交或等待片刻再尝试。"),


    CALL_THIRD_PARTY_SERVICE_ERROR("C100001", "调用第三方服务出错"),
    MIDDLEWARE_SERVICE_ERROR("C000002", "中间件服务出错"),
    INTERFACE_NOT_EXIST("C000003", "接口不存在"),

    /* 微信 */
    WEIXIN_CALLBACK_PARAM_ERROR("C100001", "回调参数不正确"),
    WEIXIN_FETCH_ACCESSTOKEN_ERROR("C100002", "获取access_token失败"),
    WEIXIN_FETCH_USERINFO_ERROR("C100003", "获取用户信息失败"),


    /* 消息 */
    MESSAGE_SERVICE_ERROR("C200001", "消息服务出错"),
    MESSAGE_DELIVERY_ERROR("C200002", "消息投递出错"),
    MESSAGE_CONSUMPTION_ERROR("C200003", "消息消费出错"),
    MESSAGE_SUBSCRIPTION_ERROR("C200004", "消息订阅出错"),
    MESSAGE_GROUP_NOT_FOUND("C200005", "消息分组未查到"),

    /* 数据库 */
    DATABASE_ERROR("C300001", "数据库服务出错"),
    DATABASE_TABLE_NOT_EXIST("C300002", "表不存在"),
    DATABASE_COLUMN_NOT_EXIST("C300003", "列不存在"),
    DATABASE_DUPLICATE_COLUMN_NAME("C300004", "多表关联中存在多个相同名称的列"),
    DATABASE_DEADLOCK("C300005", "数据库死锁"),
    DATABASE_PRIMARY_KEY_CONFLICT("C300006", "主键冲突"),

    /* TOKEN */
    TOKEN_INVALID("D000001", "token无效或已过期"),
    TOKEN_ACCESS_FORBIDDEN("D000002", "token已被禁止访问"),

    /* 权限 */
    AUTHORIZED_ERROR("D100001", "访问权限异常"),
    ACCESS_UNAUTHORIZED("D100002", "访问未授权"),


    /* 文件上传下载 */
    FILE_UPLOAD_ERROR("E000001", "用户上传文件异常"),
    FILE_UPLOAD_TYPE_NOT_MATCH("E000002", "用户上传文件类型不匹配"),
    FILE_UPLOAD_SIZE_EXCEEDS("E000003", "用户上传文件太大"),
    FILE_UPLOAD_IMAGE_SIZE_EXCEEDS("E000004", "用户上传图片太大"),
    FILE_EXPORT_ERROR("E000005", "文件导出失败"),

    /* 环境 */
    TEST_FORBIDDEN_OPERATION("F03002", "演示环境禁止新增、修改和删除数据，请本地部署后测试");

    private String code;
    private String message;

    ResponseEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
    @Override
    public String getCode() {
        return this.code;
    }
    @Override
    public String getMessage() {
        return this.message;
    }

    public static ResponseEnum getValue(String code){
        for (ResponseEnum value : values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return SYSTEM_EXECUTION_ERROR; // 默认系统执行错误
    }
}
