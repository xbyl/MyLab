package com.zhidun.mylab.http;

import android.content.Context;

import com.zhidun.mylab.NetWorkUtils;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by Administrator on 2017/5/15.
 */

public  abstract class ProgressObserver<T> extends DisposableObserver<T> implements SimpleLoadDialog.ProgressCancelListener {


    private SimpleLoadDialog dialogHandler;
    private Context context;

    public ProgressObserver(Context context) {
        this.context = context;
        dialogHandler = new SimpleLoadDialog(context,this,true);
    }

    @Override
    public void onComplete() {
        dismissProgressDialog();
    }


    /**
     * 显示Dialog
     */
    public void showProgressDialog(){
        if (dialogHandler != null) {
            dialogHandler.obtainMessage(SimpleLoadDialog.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    /**
     * 隐藏Dialog
     */
    private void dismissProgressDialog(){
        if (dialogHandler != null) {
            dialogHandler.obtainMessage(SimpleLoadDialog.DISMISS_PROGRESS_DIALOG).sendToTarget();
            dialogHandler=null;
        }
    }
    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (!NetWorkUtils.isNetworkConnected(context)) { //这里自行替换判断网络的代码
            _onError("网络不可用");
        } else if (e instanceof ApiException) {
            _onError(e.getMessage());
        } else {
            _onError("请求失败，请稍后再试...");
        }
        dismissProgressDialog();
    }


    @Override
    public void onCancelProgress() {
        if (this.isDisposed()) {
            this.dispose();
        }
    }

    protected abstract void _onNext(T t);
    protected abstract void _onError(String message);

}
