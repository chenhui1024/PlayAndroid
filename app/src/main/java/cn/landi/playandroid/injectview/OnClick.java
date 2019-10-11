package cn.landi.playandroid.injectview;

import android.view.View;

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
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@EventBase(listenerName = "setOnClickListener", listenerCls = View.OnClickListener.class, listenerMethodName = "onClick")
public @interface OnClick {

    int[] value();

}
