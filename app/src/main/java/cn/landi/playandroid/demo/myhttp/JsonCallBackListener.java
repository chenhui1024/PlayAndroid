package cn.landi.playandroid.demo.myhttp;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2019/9/22
 * @edit TODO
 */
public class JsonCallBackListener<T> implements ICallBackListener {

    private Class<T> responseCls;
    private IJsonDataTransformListener transformListener;
    private Handler handler = new Handler(Looper.getMainLooper());

    public JsonCallBackListener(Class<T> responseCls, IJsonDataTransformListener transformListener) {
        this.responseCls = responseCls;
        this.transformListener = transformListener;
    }

    @Override
    public void onSuccess(InputStream is) {
        String content = getContent(is);
        final T t = new Gson().fromJson(content, responseCls);
        handler.post(new Runnable() {
            @Override
            public void run() {
                transformListener.onSuccess(t);
            }
        });
    }

    @Override
    public void onFailure() {
        transformListener.onFailure();
    }

    private String getContent(InputStream is) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while (((line = bufferedReader.readLine()) != null)) {
                sb.append(line);
            }
            bufferedReader.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return "";
    }

}
