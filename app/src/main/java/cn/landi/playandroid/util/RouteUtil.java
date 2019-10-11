package cn.landi.playandroid.util;

import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2018/11/28
 * @edit TODO
 */
public class RouteUtil {

    public static Fragment getFragment(String url) {
        Fragment fragment = (Fragment) ARouter.getInstance().build(url).navigation();
        return fragment;
    }

    public static void toActivity(String url) {
        ARouter.getInstance().build(url).navigation();
    }

}
