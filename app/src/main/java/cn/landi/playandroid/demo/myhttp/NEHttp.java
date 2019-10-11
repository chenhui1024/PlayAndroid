package cn.landi.playandroid.demo.myhttp;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2019/9/22
 * @edit TODO
 */
public class NEHttp {

    public static <T, M> void sendJsonRequest(String url, T requestData, Class<M> responseData,
                                       IJsonDataTransformListener listener) {
        IHttpRequest httpRequest = new JsonHttpRequest();
        ICallBackListener callBackListener = new JsonCallBackListener<>(responseData, listener);
        HttpTask httpTask = new HttpTask(url, requestData, httpRequest, callBackListener);
        ThreadPoolManager.getInstance().addTask(httpTask);
    }

}
