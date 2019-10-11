package cn.landi.playandroid.demo.myhttp;

import android.util.Log;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2019/9/22
 * @edit TODO
 */
public class ThreadPoolManager {

    private static ThreadPoolManager mInstance = new ThreadPoolManager();
    public static ThreadPoolManager getInstance() {
        return mInstance;
    }

    private LinkedBlockingQueue<Runnable> mRequestQueue = new LinkedBlockingQueue<>();
    private DelayQueue<HttpTask> mDelayQueue = new DelayQueue<>();
    private ThreadPoolExecutor mThreadPoolExecutor;

    private ThreadPoolManager() {
        mThreadPoolExecutor = new ThreadPoolExecutor(3, 10,
                15, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(4), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                //被拒绝时候，重新添加
                addTask(r);
            }
        });
        mThreadPoolExecutor.execute(coreRunnable);
        mThreadPoolExecutor.execute(delayRunnable);
    }

    public void addTask(Runnable runnable) {
        if (runnable != null) {
            mRequestQueue.add(runnable);
        }
    }

    public void addDelayTask(HttpTask httpTask) {
        if (httpTask != null) {
            httpTask.setDelayTime(3000);
            mDelayQueue.offer(httpTask);
        }
    }

    //核心现场
    private final Runnable coreRunnable = new Runnable() {
        @Override
        public void run() {
            while (true) {
                try {
                    Runnable request = mRequestQueue.take();
                    mThreadPoolExecutor.execute(request);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    };

    private final Runnable delayRunnable = new Runnable() {
        @Override
        public void run() {
            while (true) {
                try {
                    HttpTask httpTask = mDelayQueue.take();
                    if (httpTask.getRetryCount() < 3) {
                        mThreadPoolExecutor.execute(httpTask);
                        httpTask.setRetryCount(httpTask.getRetryCount() + 1);
                        Log.e("chenhui", "开始重试..." + httpTask.getRetryCount());
                    } else {
                        Log.e("chenhui", "重试超限...");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

}
