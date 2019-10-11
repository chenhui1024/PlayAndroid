package cn.landi.playandroid.injectview;

import android.app.Activity;
import android.view.View;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2019/9/17
 * @edit TODO
 */
public class InjectManager {

    public static void inject(Activity activity) {
        injectContentView(activity);
        injectView(activity);
        injectListeners(activity);
    }

    private static void injectContentView(Activity activity) {
        Class<?> cls = activity.getClass();
        ContentView contentView = cls.getAnnotation(ContentView.class);
        if (contentView != null) {
            int layoutId = contentView.value();
            if (layoutId > 0) {
                //第一种方法
//                activity.setContentView(layoutId);

                //第二种方法：
                try {
                    Method method = cls.getMethod("setContentView", int.class);
                    method.invoke(activity, layoutId);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void injectView(Activity activity) {
        Class<? extends Activity> cls = activity.getClass();
        Field[] declaredFields = cls.getDeclaredFields();

        for (Field field : declaredFields) {
            InjectView injectView = field.getAnnotation(InjectView.class);
            if (injectView != null) {
                int viewId = injectView.value();
                View view = activity.findViewById(viewId);
                try {
                    field.setAccessible(true);
                    field.set(activity, view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        
    }
    
    private static void injectListeners(Activity activity) {
        Class<? extends Activity> cls = activity.getClass();
        Method[] methods = cls.getDeclaredMethods();
        for (Method method : methods) {
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                Class<? extends Annotation> annotationType = annotation.annotationType();
                if (annotationType != null) {
                    EventBase eventBase = annotationType.getAnnotation(EventBase.class);
                    if (eventBase != null) {
                        String listenerName = eventBase.listenerName();
                        Class<?> listenerCls = eventBase.listenerCls();
                        String listenerMethodName = eventBase.listenerMethodName();

                        ListenerInvocationHandler handler = new ListenerInvocationHandler(activity);
                        handler.addMethod(listenerMethodName, method);
                        Object listener = Proxy.newProxyInstance(listenerCls.getClassLoader(),
                                new Class[]{listenerCls}, handler);

                        try {
                            Method valueMethod = annotationType.getDeclaredMethod("value");
                            int[] viewIds = (int[]) valueMethod.invoke(annotation);
                            for (int viewId : viewIds) {
                                View view = activity.findViewById(viewId);
                                Method setter = view.getClass().getMethod(listenerName, listenerCls);
                                setter.invoke(view, listener);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }


}
