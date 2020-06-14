package com.calvin.mixmall.user.controller;

import com.alibaba.fastjson.JSON;
import com.calvin.mixmall.user.interceptor.TokenInfo;
import com.calvin.mixmall.user.entity.User;
import com.calvin.mixmall.support.RedisKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class UserCache {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public void updateLoginTokenInfo(User user, String phone, String token) {
        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setUserId(user.getId());
        tokenInfo.setPhone(phone);
        tokenInfo.setLastLoginTime(new Date());
        tokenInfo.setToken(token);

        stringRedisTemplate.opsForValue().set(RedisKey.USER_LOGIN_TOKEN + token, JSON.toJSON(tokenInfo).toString(), 7, TimeUnit.DAYS);
        stringRedisTemplate.opsForValue().set(RedisKey.USER_LOGIN_PHONE + phone, JSON.toJSON(tokenInfo).toString(), 7, TimeUnit.DAYS);
    }
}
