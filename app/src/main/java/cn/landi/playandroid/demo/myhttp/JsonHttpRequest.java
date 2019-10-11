package cn.landi.playandroid.demo.myhttp;

import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2019/9/22
 * @edit TODO
 */
public class JsonHttpRequest implements IHttpRequest {

    private String url;
    private byte[] data;
    private ICallBackListener listener;

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void setRequestData(byte[] data) {
        this.data = data;
    }

    @Override
    public void setCallBackListener(ICallBackListener listener) {
        this.listener = listener;
    }

    @Override
    public void excute() {
        HttpURLConnection connection = null;
        try {
            URL remoteUrl = new URL(url);
            connection = (HttpURLConnection) remoteUrl.openConnection();
            connection.setConnectTimeout(6 * 1000);
            connection.setReadTimeout(3 * 1000);
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.connect();

            OutputStream os = connection.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(os);
            bos.write(data);
            bos.flush();
            bos.close();
            os.close();

            int statusCode = connection.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_OK) {
                InputStream is = connection.getInputStream();
                listener.onSuccess(is);
            } else {
                Log.e("chenhui", "statusCode:" + statusCode);
                throw new RuntimeException("返回状态码不为200");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

    }
}
