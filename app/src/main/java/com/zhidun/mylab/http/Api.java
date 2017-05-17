package com.zhidun.mylab.http;

import com.zhidun.mylab.entity.HttpResult;
import com.zhidun.mylab.entity.RandomResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2017/5/16.
 */

public interface Api {
    String BASE_URL = "http://gank.io/api/";

    @GET("random/data/Android/{num}")
    Observable<HttpResult<List<RandomResponse>>> random(@Path("num") int num);

}
