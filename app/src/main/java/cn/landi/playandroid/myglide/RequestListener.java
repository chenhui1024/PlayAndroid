package cn.landi.playandroid.myglide;

import android.graphics.Bitmap;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2019/9/11
 * @edit TODO
 */
public interface RequestListener {

    void onSuccess(Bitmap bitmap);

    void onFailure(String error);

}
