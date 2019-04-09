package com.base.base;

import com.base.base.utilcode.util.SPUtils;

/**
 * Created by zhangyinlei on 2018/8/29
 */
public class TokenSp {
    // token
    public static String getSpToken() {
        return SPUtils.getInstance("Token").getString("Token");
    }

    public static void setSpToken(String token) {
        SPUtils.getInstance("Token").put("Token", token);
    }
}
