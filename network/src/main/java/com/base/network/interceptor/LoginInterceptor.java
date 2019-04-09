package com.base.network.interceptor;


import android.text.TextUtils;
import okhttp3.*;
import okio.Buffer;
import okio.BufferedSource;

import java.io.IOException;
import java.nio.charset.Charset;

public class LoginInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        /**通过如下的办法曲线取到请求完成的数据
         *
         * 原本想通过  originalResponse.body().string()
         * 去取到请求完成的数据,但是一直报错,不知道是okhttp的bug还是操作不当
         *
         * 然后去看了okhttp的源码,找到了这个曲线方法,取到请求完成的数据后,根据特定的判断条件去判断token过期
         */
        Request request = chain.request();
        Response originalResponse = chain.proceed(request);
        ResponseBody responseBody = originalResponse.body();
        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE); // Buffer the entire body.
        Buffer buffer = source.buffer();
        Charset charset = Charset.defaultCharset();
        MediaType contentType = responseBody.contentType();
        if (contentType != null) {
            charset = contentType.charset(Charset.defaultCharset());
        }
        assert charset != null;
        String bodyString = buffer.clone().readString(charset);
        if (!TextUtils.isEmpty(bodyString)) {
//            BaseBean baseBean = GsonUtils.fromJson(bodyString, BaseBean.class);
//            if (baseBean.getCODE() == 40101) {
//                MainHandlerUtils.getMainHandler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        BaseApplication.application.finishAllActivities();
//                        TokenSp.setSpToken("");
//                        TagAliasOperatorHelper.TagAliasBean tagAliasBean = new TagAliasOperatorHelper.TagAliasBean(TagAliasOperatorHelper.ACTION_DELETE, null, null, true);
//                        TagAliasOperatorHelper.getInstance().handleAction(BaseApplication.application, 2, tagAliasBean);
//                        ARouter.getInstance().build("/app/route/login/cooperation").navigation();
//                    }
//                }, 1000);
//            }
        }
        return originalResponse;
    }

}
