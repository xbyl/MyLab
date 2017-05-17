package com.zhidun.mylab.http;

import com.zhidun.mylab.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/5/16.
 */

public class HttpClient {

    private static Api api;

    public static Api getClient() {
        if (api == null) {
            OkHttpClient.Builder okHttpBuilder = new OkHttpClient().newBuilder();
            okHttpBuilder.readTimeout(10, TimeUnit.SECONDS);
            okHttpBuilder.connectTimeout(9, TimeUnit.SECONDS);

            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                okHttpBuilder.addInterceptor(interceptor);
            }

            api = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                    .client(okHttpBuilder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
                    .create(Api.class);
        }
        return api;
    }

}
