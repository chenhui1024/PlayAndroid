package cn.landi.playandroid.myglide;

import android.util.Log;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2019/9/11
 * @edit TODO
 */
public class RequestManager {

    private static RequestManager mInstance = new RequestManager();
    private LinkedBlockingQueue<BitmapRequest> mBitmapQueue;
    private BitmapDispatcher[] bitmapDispatchers;


    private RequestManager() {
        mBitmapQueue = new LinkedBlockingQueue<>();
        startWork();
    }

    public static RequestManager getInstance() {
        return mInstance;
    }

    private void startWork() {
        stop();
        start();
    }

    private void stop() {
        if (bitmapDispatchers != null && bitmapDispatchers.length > 0) {
            for (int i=0; i<bitmapDispatchers.length; i++) {
                if (!bitmapDispatchers[i].isInterrupted()) {
                    bitmapDispatchers[i].interrupt();
                }
            }
        }

    }

    private void start() {
        int threadCount = Runtime.getRuntime().availableProcessors();
        Log.d("chenhui", "threadCount:" + threadCount);
        bitmapDispatchers = new BitmapDispatcher[threadCount];
        for (int i=0; i<threadCount; i++) {
            bitmapDispatchers[i] = new BitmapDispatcher(mBitmapQueue);
            bitmapDispatchers[i].start();
        }
    }

    public void addBitmapRequest(BitmapRequest request) {
        if (!mBitmapQueue.contains(request)) {
            mBitmapQueue.add(request);
        }
    }

}
