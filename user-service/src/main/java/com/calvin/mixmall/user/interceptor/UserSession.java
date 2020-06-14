package com.calvin.mixmall.user.interceptor;

/**
 * 线程共享userId
 */
public class UserSession {

    private static ThreadLocal<Long> local = new ThreadLocal<>();

    public static void setUserId(Long userId) {
        local.set(userId);
    }

    public static Long getUserId() {
        return local.get();
    }

    public static void remove() {
        local.remove();
    }

}