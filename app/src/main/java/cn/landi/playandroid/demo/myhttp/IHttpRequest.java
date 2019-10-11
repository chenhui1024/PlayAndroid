package cn.landi.playandroid.demo.myhttp;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2019/9/22
 * @edit TODO
 */
public interface IHttpRequest<T> {

    void setUrl(String url);

    void setRequestData(byte[] data);

    void setCallBackListener(ICallBackListener listener);

    void excute();

}
