package com.calvin.mixmall.exception;


import com.calvin.mixmall.support.ResultEnum;

/**
 * 业务异常（运行时异常），用于回滚spring管理事务
 */

public class BusinessException extends RuntimeException {

    /** 错误Code. */
    private String code;
    /** 错误信息. */
    private String message;
    /** 响应码类型(默认系统错误). */
    private ResultEnum resultEnum;

    /**
     * 构造方法(带有响应码类型).<br>
     *
     * @param resultEnum 响应码类型
     */
    public BusinessException(ResultEnum resultEnum) {
        this.resultEnum = resultEnum;
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
    }

    /**
     * 构造方法(接收错误错误信息).<br>
     * 可以定义响应码类型<br>
     *
     * @param resultEnum 响应码类型
     * @param message 错误信息
     */
    public BusinessException(ResultEnum resultEnum, String message) {
        this(resultEnum);
        this.message = message;
    }

    /**
     * 构造方法(带有响应码类型的原生异常).
     *
     * @param resultEnum 响应码类型
     * @param cause 异常cause
     */
    public BusinessException(ResultEnum resultEnum, Throwable cause) {
        // 设置cause
        super(cause);
        this.resultEnum = resultEnum;
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
    }

    /**
     * @param resultEnum 响应码类型
     * @param cause 异常cause
     * @param message 错误信息
     */
    public BusinessException(ResultEnum resultEnum, Throwable cause, String message){
        this(resultEnum, cause);
        this.message = message;
    }

    /**
     * 错误Code 取得处理.
     *
     * @return 错误Code
     */
    public String getCode() {
        return code;
    }

    /**
     * 错误信息 取得处理.
     *
     * @return 错误信息
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * 响应码类型 的取得处理.
     *
     * @return 响应码类型
     */
    public ResultEnum getResultEnum() {
        return resultEnum;
    }

}
