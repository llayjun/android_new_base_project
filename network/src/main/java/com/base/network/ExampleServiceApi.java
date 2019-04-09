package com.base.network;

import com.base.base.bean.BaseBean;
import com.base.base.bean.WeatherBean;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.*;

import java.util.List;

public interface ExampleServiceApi {

    // 例子
    @GET("xxx")
    Observable<BaseBean> get(@Query("x") int x);

    @POST("xxx")
    Observable<BaseBean> postJson(@Body BaseBean baseBean);

    @FormUrlEncoded
    @POST("xxx")
    Observable<BaseBean> postForm(
            @Field("x") int x
    );

    // 上传图片一张图片
    @Multipart
    @POST("xxx")
    Observable<BaseBean<String>> uploadOneImage(@Part MultipartBody.Part part);

    // 上传多张图片
    @Multipart
    @POST("xxx")
    Observable<BaseBean<String>> uploadManyImages(@Part List<MultipartBody.Part> partList);

    // 获取天气预报
    @GET("http://wthrcdn.etouch.cn/weather_mini")
    Observable<WeatherBean> getWeatherByCityName(@Query("city") String cityName);
}
