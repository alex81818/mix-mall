package com.calvin.mixmall.support;

import lombok.Data;

@Data
public class ResultDTO<T> {
    private String code;
    private String msg;
    private T data;
 
    public ResultDTO(){
    }
 
    public ResultDTO(ResultEnum resultEnum){
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMessage();
    }
 
    public ResultDTO(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResultDTO success(){
        return new ResultDTO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), null);
    }

    public static <T> ResultDTO success(T data){
        return new ResultDTO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), data);
    }

    public static ResultDTO failure(ResultEnum resultEnum){
        return new ResultDTO(resultEnum.getCode(), resultEnum.getMessage(), null);
    }

    public static ResultDTO failure(String msg){
        return new ResultDTO(ResultEnum.PARAMETER_ERROR.getCode(), msg, null);
    }



}
