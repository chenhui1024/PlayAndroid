package cn.landi.playandroid.eventbus;

import java.lang.reflect.Method;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2019/6/20
 * @edit TODO
 */
public class SubscribeMethod {

    private Method method;
    private ThreadMode threadMode;
    private Class<?> type;

    public SubscribeMethod(Method method, ThreadMode threadMode, Class<?> type) {
        this.method = method;
        this.threadMode = threadMode;
        this.type = type;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public ThreadMode getThreadMode() {
        return threadMode;
    }

    public void setThreadMode(ThreadMode threadMode) {
        this.threadMode = threadMode;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }
}
