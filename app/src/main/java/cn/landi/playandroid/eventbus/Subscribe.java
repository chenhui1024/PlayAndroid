package cn.landi.playandroid.eventbus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2019/6/20
 * @edit TODO
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Subscribe {

    ThreadMode threadMode() default ThreadMode.MAIN;

}
