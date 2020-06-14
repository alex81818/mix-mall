package com.calvin.mixmall.util;

import cn.hutool.crypto.digest.DigestUtil;

import java.util.UUID;

/**
 * @Description:
 * @Author: guowei
 * @Date: 2019/4/29 14:44
 */
public class CommonUtil {

    /**
     * 生成token
     *
     * @return
     */
    public static final String getToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args) {
        System.out.println(DigestUtil.md5Hex("123456"));
    }
}
