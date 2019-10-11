package cn.landi.playandroid.myglide;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.LinkedBlockingQueue;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2019/9/11
 * @edit TODO
 */
public class BitmapDispatcher extends Thread {

    private LinkedBlockingQueue<BitmapRequest> mBitmapQueue;

    public BitmapDispatcher(LinkedBlockingQueue<BitmapRequest> mBitmapQueue) {
        this.mBitmapQueue = mBitmapQueue;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {

            try {
                BitmapRequest bitmapRequest = mBitmapQueue.take();

                //设置占位符
                showLoadingImag(bitmapRequest);

                //请求网络下载
                Bitmap bitmap = requestBitmap(bitmapRequest);

                //显示
                showBitmapToImageview(bitmap, bitmapRequest);

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }

    private void showBitmapToImageview(final Bitmap bitmap, BitmapRequest bitmapRequest) {
        if (bitmap != null && bitmapRequest.getImageView() != null
                && bitmapRequest.getUrlMD5().equals(bitmapRequest.getImageView().getTag())) {
            final ImageView imageView = bitmapRequest.getImageView();
            Completable.complete().observeOn(AndroidSchedulers.mainThread()).doOnComplete(new Action() {
                @Override
                public void run() throws Exception {
                    imageView.setImageBitmap(bitmap);
                }
            }).subscribe();
        }
    }

    private Bitmap requestBitmap(BitmapRequest bitmapRequest) {
        Bitmap bitmap = null;
        if (bitmapRequest != null && !TextUtils.isEmpty(bitmapRequest.getUrl())) {
            bitmap = downloadBitmap(bitmapRequest.getUrl());
        }
        return bitmap;
    }

    private Bitmap downloadBitmap(String uri) {
        InputStream is = null;
        Bitmap bitmap = null;
        try {
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            is = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    private void showLoadingImag(final BitmapRequest bitmapRequest) {
        Log.d("chenhui", "设置占位符");
        if (bitmapRequest != null && bitmapRequest.getImageView() != null
                && bitmapRequest.getResId() > 0) {
            Log.d("chenhui", "设置...");
            final ImageView imageView = bitmapRequest.getImageView();
            Completable.complete().observeOn(AndroidSchedulers.mainThread()).doOnComplete(new Action() {
                @Override
                public void run() throws Exception {
                    Log.d("chenhui", "resId:" + bitmapRequest.getResId());
                    imageView.setImageResource(bitmapRequest.getResId());
                }
            }).subscribe();
        }
    }

}
