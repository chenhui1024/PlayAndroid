package cn.landi.playandroid.injectview;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2019/9/17
 * @edit TODO
 */
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventBase {

    String listenerName();

    Class<?> listenerCls();

    String listenerMethodName();

}
