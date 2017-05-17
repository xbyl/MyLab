package com.zhidun.mylab.ui;

import android.os.Bundle;
import android.util.Log;

import com.zhidun.mylab.R;
import com.zhidun.mylab.entity.HttpResult;
import com.zhidun.mylab.entity.RandomResponse;
import com.zhidun.mylab.http.HttpClient;
import com.zhidun.mylab.http.HttpUtil;
import com.zhidun.mylab.http.ProgressObserver;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HttpUtil.getInstance().toSubscribe(HttpClient.getClient().random(20)
                .doOnNext(new Consumer<HttpResult<List<RandomResponse>>>() {
                    @Override
                    public void accept(@NonNull HttpResult<List<RandomResponse>> listHttpResult) throws Exception {
                        Log.d("TAG",Thread.currentThread().getName());
                        Thread.sleep(1000*20);
                    }
                })
                , new ProgressObserver<List<RandomResponse>>(this) {
                    @Override
                    protected void _onNext(List<RandomResponse> list) {
                        Log.d("TAG",Thread.currentThread().getName());
                    }

                    @Override
                    protected void _onError(String message) {
                        Log.d("TAG",message);
                    }
                }, ActivityLifeCycleEvent.PAUSE, lifecycleSubject);
    }
}
