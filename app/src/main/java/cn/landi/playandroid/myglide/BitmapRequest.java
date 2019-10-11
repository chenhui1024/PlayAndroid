package cn.landi.playandroid.myglide;

import android.widget.ImageView;

import java.lang.ref.SoftReference;

import cn.landi.playandroid.util.EncryptUtil;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2019/9/11
 * @edit TODO
 */
public class BitmapRequest {

    /**
     *  请求url
     */
    private String url;

    /**
     *
     */
    private String urlMD5;

    /**
     *
     */
    private int resId;

    /**
     *
     */
    private SoftReference<ImageView> imageView;

    /**
     *
     */
    private RequestListener listener;

    public BitmapRequest load(String url) {
        this.url = url;
        this.urlMD5 = EncryptUtil.Encrypt(url, "MD5");
        return this;
    }

    public BitmapRequest loading(int resId) {
        this.resId = resId;
        return this;
    }

    public void into(ImageView imageView) {
        imageView.setTag(urlMD5);
        this.imageView = new SoftReference<>(imageView);

        RequestManager.getInstance().addBitmapRequest(this);
    }

    public BitmapRequest listener(RequestListener listener) {
        this.listener = listener;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlMD5() {
        return urlMD5;
    }

    public int getResId() {
        return resId;
    }

    public ImageView getImageView() {
        return imageView.get();
    }

    public RequestListener getListener() {
        return listener;
    }
}
