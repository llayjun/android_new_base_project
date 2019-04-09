package com.base.network.interceptor;

import com.base.base.TokenSp;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * Created by zhangyinlei on 2018/8/29
 */
public class TokenInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request authorised = chain.request().newBuilder()
                .header("token", TokenSp.getSpToken())
                .build();
        return chain.proceed(authorised);
    }

}
