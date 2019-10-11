package cn.landi.playandroid.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.landi.playandroid.R;
import cn.landi.playandroid.constant.RouteUrl;
import cn.landi.playandroid.http.HttpService;
import cn.landi.playandroid.http.RxHttpService;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.BufferedSource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2019/3/12
 * @edit TODO
 */
@Route(path = RouteUrl.RETROFIT_ACTIVITY)
public class RetrofitActivity extends AppCompatActivity {

    @BindView(R.id.toobar)
    Toolbar toobar;
    @BindView(R.id.btn_request)
    Button btnRequest;
    @BindView(R.id.btn_request_rxjava)
    Button btnRequestRxjava;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        ButterKnife.bind(this);

        setSupportActionBar(toobar);
        setTitle("Retrofit");

        float xdpi = getResources().getDisplayMetrics().xdpi;
        float ydpi = getResources().getDisplayMetrics().ydpi;
        float desity = getResources().getDisplayMetrics().density;
        Log.d("chenhui", "xdpi:" + xdpi + ",ydpi:" + ydpi);
        Log.d("chenhui", "desity:" + desity);
    }

    private void retrofitRequest() {
        new Thread() {

            @Override
            public void run() {
                super.run();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.douban.com/v2/")
                        .build();

                HttpService httpService = retrofit.create(HttpService.class);
                Call<ResponseBody> call = httpService.getBook(1220562);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            if (response != null && response.isSuccessful()) {
                                Log.d("Retrofit", "response:" + response.body().string());
                            } else {
                                Log.d("Retrofit", "response is not successful");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.e("Retrofit", "onResponse IOException");
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("Retrofit", "onFailure:" + t.getMessage());
                    }
                });
            }

        }.start();
    }

    private void rxjavaRetrofitHttpRequest() {
        new Thread() {

            @Override
            public void run() {
                super.run();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.douban.com/v2/")
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build();

                RxHttpService rxHttpService = retrofit.create(RxHttpService.class);
                rxHttpService.getBook(1220562)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends ResponseBody>>() {
                            @Override
                            public ObservableSource<? extends ResponseBody> apply(Throwable throwable) throws Exception {
                                Log.d("Retrofit", "apply !!!");
                                return Observable.error(new Throwable("it is just a test error ~"));
                            }
                        })
                        .subscribe(new Consumer<ResponseBody>() {
                            @Override
                            public void accept(ResponseBody s) throws Exception {
                                Log.d("Retrofit", "subscribe result:" + s.string());
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.d("Retrofit", "subscribe throwable name:" + throwable.toString() + ",message:" + throwable.getMessage());
                            }
                        });
            }

        }.start();
    }

    @OnClick({R.id.btn_request, R.id.btn_request_rxjava})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_request:
                retrofitRequest();
                break;
            case R.id.btn_request_rxjava:
                rxjavaRetrofitHttpRequest();
                break;
        }
    }
}
