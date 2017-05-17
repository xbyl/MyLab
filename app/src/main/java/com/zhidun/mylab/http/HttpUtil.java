package com.zhidun.mylab.http;


import com.zhidun.mylab.entity.HttpResult;
import com.zhidun.mylab.ui.ActivityLifeCycleEvent;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Administrator on 2017/5/15.
 */

public class HttpUtil {
    /**
     * 构造方法私有
     */
    private HttpUtil() {
    }

    /**
     * 在访问HttpUtil时创建单例
     */
    private static class SingletonHolder {
        private static final HttpUtil INSTANCE = new HttpUtil();
    }

    /**
     * 获取单例
     */
    public static HttpUtil getInstance() {
        return SingletonHolder.INSTANCE;
    }

    //添加线程管理并订阅
    public <T> void toSubscribe(Observable<HttpResult<T>> observable
            , final ProgressObserver<T> observer
            , ActivityLifeCycleEvent event
            , PublishSubject<ActivityLifeCycleEvent> lifecycleSubject) {
        //数据预处理
        ObservableTransformer<HttpResult<T>, T> result = handleResult(event, lifecycleSubject);
        //重用操作符
        observable.compose(result)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        //显示Dialog和一些其他操作
                        observer.showProgressDialog();
                    }
                }).subscribe(observer);
    }


    public <T> ObservableTransformer<HttpResult<T>, T> handleResult(
            final ActivityLifeCycleEvent event
            , final PublishSubject<ActivityLifeCycleEvent> lifecycleSubject) {
        return new ObservableTransformer<HttpResult<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<HttpResult<T>> tObservable) {
                Observable<ActivityLifeCycleEvent> compareLifecycleObservable =
                        lifecycleSubject.take(1);
                return tObservable.flatMap(new Function<HttpResult<T>, Observable<T>>() {
                    @Override
                    public Observable<T> apply(@NonNull HttpResult<T> tHttpResult) throws Exception {
                        if (tHttpResult.getCode() == 0) {
                            return createData(tHttpResult.getResults());
                        } else {
                            return Observable.error(new ApiException(tHttpResult.getCode()));
                        }
                    }
                }).takeUntil(compareLifecycleObservable)
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


    /**
     * 创建成功的数据
     *
     * @param data
     * @param <T>
     * @return
     */
    private <T> Observable<T> createData(final T data) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<T> e) throws Exception {
                try {
                    e.onNext(data);
                    e.onComplete();
                } catch (Exception ex) {
                    e.onError(ex);
                }
            }
        });
    }
}