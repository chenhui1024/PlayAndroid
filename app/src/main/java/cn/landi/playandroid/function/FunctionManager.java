package cn.landi.playandroid.function;

import android.text.TextUtils;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2019/8/5
 * @edit TODO
 */
public class FunctionManager {

    private final String TAG = FunctionManager.class.getSimpleName();

    private static FunctionManager mInstance = null;
    private Map<String, FunctionNoParamNoResult> mNoParamNoResult;
    private Map<String, FunctionNoParamHasResult> mNoParamHasResult;
    private Map<String, FunctionHasParamNoResult> mHasParamNoResult;
    private Map<String, FunctionHasParamHasResult> mHasParamHasResult;

    private FunctionManager() {
        mNoParamNoResult = new HashMap<>();
        mNoParamHasResult = new HashMap<>();
        mHasParamNoResult = new HashMap<>();
        mHasParamHasResult = new HashMap<>();
    }

    public static FunctionManager getInstance() {
        if (mInstance == null) {
            synchronized (FunctionManager.class) {
                if (mInstance == null) {
                    mInstance = new FunctionManager();
                }
            }
        }
        return mInstance;
    }

    public void addFunction(FunctionNoParamNoResult function) {
        if (function == null) {
            return ;
        }

        if (mNoParamNoResult != null) {
            mNoParamNoResult.put(function.functionName, function);
        }
    }

    public void addFunction(FunctionNoParamHasResult function) {
        if (function == null) {
            return ;
        }

        if (mNoParamNoResult != null) {
            mNoParamHasResult.put(function.functionName, function);
        }
    }

    public void addFunction(FunctionHasParamNoResult function) {
        if (function == null) {
            return ;
        }

        if (mNoParamNoResult != null) {
            mHasParamNoResult.put(function.functionName, function);
        }
    }

    public void addFunction(FunctionHasParamHasResult function) {
        if (function == null) {
            return ;
        }

        if (mNoParamNoResult != null) {
            mHasParamHasResult.put(function.functionName, function);
        }
    }


    public void invoke(String functionName) {
        if (TextUtils.isEmpty(functionName)) {
            Log.e(TAG, "functionName is null !!!");
            return ;
        }

        if (mNoParamNoResult != null) {
            FunctionNoParamNoResult function = mNoParamNoResult.get(functionName);
            if (function != null) {
                function.function();
            }
        }
    }

    public <T> T invokeNoParamHasResult(String functionName, Class<T> cls) {
        if (TextUtils.isEmpty(functionName)) {
            Log.e(TAG, "functionName is null !!!");
            return null;
        }

        if (mNoParamHasResult != null) {
            FunctionNoParamHasResult function = mNoParamHasResult.get(functionName);
            if (function != null) {
                return cls.cast(function.function());
            }
            Log.e(TAG, "function is not found !!!");
        }

        return null;
    }

    public <P> void invokeHasParamNoResult(String functionName, P p) {
        if (TextUtils.isEmpty(functionName)) {
            Log.e(TAG, "functionName is null !!!");
            return ;
        }

        if (mHasParamNoResult != null) {
            FunctionHasParamNoResult function = mHasParamNoResult.get(functionName);
            if (function != null) {
                function.function(p);
                return ;
            }
            Log.e(TAG, "function is not found !!!");
        }
    }

    public <P, T> T invokeHasParamHasResult(String functionName, P p, Class<T> cls) {
        if (TextUtils.isEmpty(functionName)) {
            Log.e(TAG, "functionName is null !!!");
            return null;
        }

        if (mHasParamHasResult != null) {
            FunctionHasParamHasResult function = mHasParamHasResult.get(functionName);
            if (function != null) {
                return cls.cast(function.function(p));
            }
            Log.e(TAG, "function is not found !!!");
        }

        return null;
    }

}
