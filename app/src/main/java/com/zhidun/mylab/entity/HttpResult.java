package com.zhidun.mylab.entity;

/**
 * Created by Administrator on 2017/5/15.
 */

public class HttpResult<T> {

    private boolean error;
    private int code;
    private String message;
    private T results;

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isError() {
        return error;
    }

    public HttpResult setError(boolean error) {
        this.error = error;
        return this;
    }
}
