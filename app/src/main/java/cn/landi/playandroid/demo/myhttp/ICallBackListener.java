package cn.landi.playandroid.demo.myhttp;

import java.io.InputStream;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2019/9/22
 * @edit TODO
 */
public interface ICallBackListener {

    void onSuccess(InputStream is);

    void onFailure();

}
