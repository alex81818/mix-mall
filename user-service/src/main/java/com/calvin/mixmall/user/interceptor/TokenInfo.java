package com.calvin.mixmall.user.interceptor;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TokenInfo implements Serializable {
    private String phone;
    private long userId;
    private Date lastLoginTime;
    private String token;
}
