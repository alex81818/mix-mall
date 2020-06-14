package com.calvin.mixmall.support;

/**
 * 错误枚举
 * 错误码使用4位数字表示
 * 前2位数字表示大的错误类型
 * 后2位数字表示该类型下的具体分类错误
 *
 * 0000 为操作成功
 *
 * 其他自定义错误码从 0100 开始
 */
public enum ResultEnum {

    SUCCESS("0000", "请求成功"),
    DATABASE_ERROR("0002", "数据库异常"),
    PARAMETER_ERROR("0101", "请求参数有误!"),
    USERNAME_ERROR("0102", "用户名或密码错误"),
    PHONE_NOT_EXIST("0103", "手机号不存在"),
    CHANGE_DEVICE("0104", "用户更换设备"),
    InvalidToken("0105","token不合法"),
    UNKNOWN_ERROR("9999", "未知的错误!");
 
    private String code;
    private String message;
 
    ResultEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
 
    public String getCode() {
        return code;
    }
 
    public String getMessage() {
        return message;
    }

}
