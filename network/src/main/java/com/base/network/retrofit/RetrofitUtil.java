package com.base.network.retrofit;

import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.base.base.utilcode.util.Utils;
import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyinlei on 2018/6/27
 */
public class RetrofitUtil {

    /**
     * 上传单张图片
     *
     * @param key
     * @param imagePath
     * @return
     */
    public static MultipartBody.Part getUploadSingleImage(String key, String imagePath) {
        File file = new File(imagePath);
        //图片压缩
        try {
            file = new Compressor(Utils.getApp())
                    .compressToFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 创建 RequestBody，用于封装构建RequestBody
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        // MultipartBody.Part 和后端约定好Key，这里的partName是用image
        MultipartBody.Part body = MultipartBody.Part.createFormData(key, file.getName(), requestFile);
        return body;
    }

    /**
     * 上传多张图片
     *
     * @param key
     * @param imagePathList
     * @return
     */
    public static List<MultipartBody.Part> getUploadManyImage(String key, List<String> imagePathList) {
        List<MultipartBody.Part> partList = new ArrayList<>();
        for (int i = 0; i < imagePathList.size(); i++) {
            File file = new File(imagePathList.get(i));
            //图片压缩
            try {
                file = new Compressor(Utils.getApp())
                        .compressToFile(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 创建 RequestBody，用于封装构建RequestBody
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            // MultipartBody.Part 和后端约定好Key，这里的partName是用image
            MultipartBody.Part body = MultipartBody.Part.createFormData(key, file.getName(), requestFile);
            partList.add(body);
        }
        return partList;
    }

    /**
     * 同步一下cookie
     */
    public static void synCookies(Context context, String url) {
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.removeSessionCookie();//移除
        String cookies = cookieManager.getCookie(url);
        cookieManager.setCookie(url, cookies);//cookies是在HttpClient中获得的cookie
        CookieSyncManager.getInstance().sync();
    }

    /**
     * 传json
     *
     * @param json
     * @return
     */
    public static RequestBody getJsonBody(String json) {
        return RequestBody.create(okhttp3.MediaType.parse("application/json;charset=utf-8"), json);
    }

}
