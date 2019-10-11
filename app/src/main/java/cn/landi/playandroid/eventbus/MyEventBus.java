package cn.landi.playandroid.eventbus;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2019/6/20
 * @edit TODO
 */
public class MyEventBus {

    private static volatile MyEventBus mInstance;
    private Handler handler;
    private Map<Object, List<SubscribeMethod>> methods;
    private MyEventBus() {
        handler = new Handler(Looper.getMainLooper());
        methods = new HashMap<>();
    }

    public static MyEventBus getDefault() {
        if (mInstance == null) {
            synchronized (MyEventBus.class) {
                if (mInstance == null) {
                    mInstance = new MyEventBus();
                }
            }
        }
        return mInstance;
    }

    public void register(Object object) {
        List<SubscribeMethod> list = methods.get(object);
        if (list == null) {
            list = findSubscribeMehtod(object);
            methods.put(object, list);
        }
    }

    public void post(final Object object) {
        Iterator<Object> it = methods.keySet().iterator();
        while (it.hasNext()) {
            final Object obj = it.next();
            List<SubscribeMethod> list = methods.get(obj);
            for (final SubscribeMethod subscribeMethod : list) {
                if (subscribeMethod.getType().isAssignableFrom(object.getClass())) {

                    ThreadMode threadMode = subscribeMethod.getThreadMode();
                    switch (threadMode) {
                        case MAIN:
                            if (Looper.myLooper() == Looper.getMainLooper()) {
                                invoke(subscribeMethod, obj, object);
                            } else {
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        invoke(subscribeMethod, obj, object);
                                    }
                                });
                            }
                            break;
                        case BACKGROUND:
                            Executors.newCachedThreadPool().execute(new Runnable() {
                                @Override
                                public void run() {
                                    invoke(subscribeMethod, obj, object);
                                }
                            });
                            break;
                    }
                }
            }
        }

    }

    private void invoke(SubscribeMethod subscribeMethod, Object obj, Object object) {
        try {
            subscribeMethod.getMethod().invoke(obj, object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private List<SubscribeMethod> findSubscribeMehtod(Object object) {
        List<SubscribeMethod> list = new ArrayList<>();
        Class<?> cls = object.getClass();

        while (cls != null) {

            if (cls.getName().startsWith("java.") || cls.getName().startsWith("javax.")
                    || cls.getName().startsWith("android.")) {
                break;
            }

            Method[] methods = cls.getDeclaredMethods();
            for (Method method : methods) {
                Subscribe subscribe = method.getAnnotation(Subscribe.class);
                if (subscribe == null) {
                    continue;
                }

                ThreadMode threadMode = subscribe.threadMode();
                Class<?>[] types = method.getParameterTypes();
                if (types.length != 1) {
                    Log.e("MyEventBus", "MyEventBus Only Accept One Para !!!");
                }

                list.add(new SubscribeMethod(method, threadMode, types[0]));
            }

            cls = cls.getSuperclass();
        }


        return list;
    }

}
