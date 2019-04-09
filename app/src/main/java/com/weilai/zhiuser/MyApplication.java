package com.weilai.zhiuser;

import android.content.Context;
import android.content.res.AssetManager;
import com.base.base.baseui.base.BaseApplication;
import com.base.network.retrofit.HttpUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by zhangyinlei on 2018/8/13
 */
public class MyApplication extends BaseApplication {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // init http
        HttpUtil.getInstance().setBaseUrl(BuildConfig.BASE_URL);
    }

    /**
     * 获取城市名称
     *
     * @return
     */
    private String getCityJson() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = this.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open("city.json")));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

}
