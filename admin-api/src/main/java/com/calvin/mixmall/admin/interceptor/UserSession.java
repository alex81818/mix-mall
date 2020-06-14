package com.calvin.mixmall.admin.interceptor;

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