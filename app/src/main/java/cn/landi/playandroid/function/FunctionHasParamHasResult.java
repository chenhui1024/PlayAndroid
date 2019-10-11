package cn.landi.playandroid.function;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2019/8/5
 * @edit TODO
 */
public abstract class FunctionHasParamHasResult<P, T> extends Function {

    public FunctionHasParamHasResult(String functionName) {
        super(functionName);
    }

    public abstract P function(T t);

}
