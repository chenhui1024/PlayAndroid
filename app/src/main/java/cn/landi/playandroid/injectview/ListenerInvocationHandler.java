package cn.landi.playandroid.injectview;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2019/9/17
 * @edit TODO
 */
public class ListenerInvocationHandler implements InvocationHandler {

    private Object target;
    private HashMap<String, Method> methodHashMap = new HashMap<>();

    public ListenerInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (target != null) {
            String methodName = method.getName();
            method = methodHashMap.get(methodName);
            if (method != null) {
                if (method.getParameterTypes().length == 0)
                    return method.invoke(target);
                return method.invoke(target, args);
            }
        }
        return null;
    }

    public void addMethod(String key, Method method) {
        methodHashMap.put(key, method);
    }
}
