package com.calvin.mixmall.user.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.calvin.mixmall.support.RedisKey;
import com.calvin.mixmall.support.ResultDTO;
import com.calvin.mixmall.support.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description:
 * @Author: guowei
 * @Date: 2019/4/19 15:30
 */
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //在请求处理之前进行调用 Controller方法调用之前
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String uri = httpServletRequest.getRequestURI();
        if (uri.contains("swagger") || uri.contains("doc.html") ||
                uri.contains("webjars")) {
            return true;
        }

        String token = httpServletRequest.getHeader("X-USER-TOKEN");

        if (StringUtils.isEmpty(token)) {
            log.error("请求没有token");
            returnInvalidToken(httpServletResponse);
            return false;
        }

        String key = RedisKey.USER_LOGIN_TOKEN + token;
        String value = stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.isEmpty(value)) {
            log.error("根据token找不到系统里面的值,token:{}", token);
            returnInvalidToken(httpServletResponse);
            return false;
        }

        TokenInfo tokenInfo = JSON.parseObject(value, TokenInfo.class);
        String phoneKey = RedisKey.USER_LOGIN_PHONE + tokenInfo.getPhone();
        String phoneToken = stringRedisTemplate.opsForValue().get(phoneKey);
        if (StringUtils.isEmpty(phoneToken)) {
            log.error("根据token找不到系统里面phone的key值,token:{}, phoneKey:{}", token, phoneKey);
            returnInvalidToken(httpServletResponse);
            return false;
        }
        TokenInfo phoneTokenInfo = JSON.parseObject(phoneToken, TokenInfo.class);
        if (phoneTokenInfo != null && phoneTokenInfo.getToken().equals(token)) {
            UserSession.setUserId(phoneTokenInfo.getUserId());
            return true;
        }

        log.error("用户在其他设备登录,token:{}, phoneKey:{}", token, phoneKey);
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        ResultDTO dto = ResultDTO.failure(ResultEnum.CHANGE_DEVICE);
        httpServletResponse.getWriter().write(JSONObject.toJSONString(dto));
        return false;
    }

    private void returnInvalidToken(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        ResultDTO dto = ResultDTO.failure(ResultEnum.InvalidToken);
        httpServletResponse.getWriter().write(JSONObject.toJSONString(dto));
    }


    //请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
//        System.out.println("postHandle被调用\n");
    }

    //在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
//        System.out.println("afterCompletion被调用\n");
        UserSession.remove();
    }
}
