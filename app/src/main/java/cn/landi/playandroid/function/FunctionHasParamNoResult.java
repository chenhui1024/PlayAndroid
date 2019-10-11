package cn.landi.playandroid.function;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2019/8/5
 * @edit TODO
 */
public abstract class FunctionHasParamNoResult<T> extends Function {

    public FunctionHasParamNoResult(String functionName) {
        super(functionName);
    }

    public abstract void function(T t);

}
