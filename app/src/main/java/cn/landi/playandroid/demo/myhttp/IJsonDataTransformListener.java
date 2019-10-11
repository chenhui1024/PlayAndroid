package cn.landi.playandroid.demo.myhttp;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2019/9/22
 * @edit TODO
 */
public interface IJsonDataTransformListener<T> {

    void onSuccess(T data);

    void onFailure();

}
