package cn.landi.playandroid.demo.myhttp;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2019/9/22
 * @edit TODO
 */
public class HttpTask<T> implements Runnable, Delayed {

    private IHttpRequest httpRequest;
    private long delayTime;
    private int retryCount;

    public HttpTask(String url, T requestData, IHttpRequest httpRequest, ICallBackListener listener) {
        try {
            httpRequest.setUrl(url);
            httpRequest.setCallBackListener(listener);
            String request = new Gson().toJson(requestData);
            httpRequest.setRequestData(request.getBytes("utf-8"));
            this.httpRequest = httpRequest;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        try {
            httpRequest.excute();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("chenhui", "异常出现：" + e.getMessage());
            ThreadPoolManager.getInstance().addDelayTask(this);
        }
    }

    public long getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(long delayTime) {
        this.delayTime = System.currentTimeMillis() + delayTime;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    @Override
    public long getDelay(@NonNull TimeUnit unit) {
        return unit.convert(delayTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(@NonNull Delayed o) {
        return 0;
    }
}
