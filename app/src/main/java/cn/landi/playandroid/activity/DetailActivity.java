package cn.landi.playandroid.activity;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;

import org.reactivestreams.Subscriber;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.landi.playandroid.R;
import cn.landi.playandroid.constant.RouteUrl;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2018/12/12
 * @edit TODO
 */
@Route(path = RouteUrl.DETAIL_ACTIVITY)
public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.toobar)
    Toolbar toobar;
//    @BindView(R.id.appbar)
//    AppBarLayout appbar;
//    @BindView(R.id.fab)
//    FloatingActionButton fab;

    Observable observable;
    ObservableEmitter emitter;
    Observer<String> observer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        toobar.setTitle("测试");
        setSupportActionBar(toobar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                Log.d("chenhui", "=== subscribe ===,currentThreadName:" + Thread.currentThread().getName());
                emitter = e;
                emitter.onNext("111");
//                emitter.onError(new Exception());
//                SystemClock.sleep(3000);
//                emitter.onNext("222");
                emitter.onComplete();
            }
        });

//        Completable.complete()
//                .doOnSubscribe(new Consumer<Disposable>() {
//                    @Override
//                    public void accept(Disposable disposable) throws Exception {
//                        Log.d("chenhui", "doOnSubscribe thread name:" + Thread.currentThread().getName());
//                    }
//                })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnComplete(new Action() {
//                    @Override
//                    public void run() throws Exception {
//                        Log.d("chenhui", "doOnComplete thread name:" + Thread.currentThread().getName());
//                    }
//                })
//                .subscribe();

        observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                //都是在主线程
//                Log.d("chenhui", "onSubscribe,currentThreadName:" + Thread.currentThread().getName());
            }

            @Override
            public void onNext(String value) {
                Log.d("chenhui", "onNext:" + value + ",currentThreadName:" + Thread.currentThread().getName());
            }

            @Override
            public void onError(Throwable e) {
                Log.d("chenhui", "onError:" + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d("chenhui", "onComplete");
            }
        };

//        observable.observeOn().doOnNext(new Consumer() {
//            @Override
//            public void accept(Object object) throws Exception {
//                Log.d("chenhui", "doOnNext thread name:" + Thread.currentThread().getName());
//            }
//        }).subscribeOn(Schedulers.io()).subscribe();



        Completable.complete()
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        Log.d("chenhui", "doOnSubscribe === >:" + Thread.currentThread().getName());
                        SystemClock.sleep(5000);
                        Log.d("chenhui", "doOnSubscribe === > finished");
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.d("chenhui", "doOnComplete === >:" + Thread.currentThread().getName());
                    }
                }).subscribe();


//
//        final int countTime = 60;
//        Observable.interval(0, 1, TimeUnit.SECONDS)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                //设置循环次数
//                .take(5)
//                //从60-1
//                .map(new Function<Long, Long>() {
//
//                    @Override
//                    public Long apply(Long aLong) throws Exception {
//                        Log.d("test", "aLong:" + aLong + ",apply : " + Thread.currentThread().getName());
//                        return countTime - aLong;
//                    }
//                })
//                //执行过程中按键为不可点击状态
//                .doOnSubscribe(new Consumer<Disposable>() {
//                    @Override
//                    public void accept(Disposable disposable) {
//                        Log.d("test", "doOnSubscribe : " + Thread.currentThread().getName());
//                    }
//                })
//                .doOnNext(new Consumer<Long>() {
//                    @Override
//                    public void accept(Long aLong) throws Exception {
//                        Log.d("test", "doOnNext:" + aLong + ",currentThreadName:" + Thread.currentThread().getName());
//                    }
//                })
//                .subscribe(observer1);



//        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doAfterNext(new Consumer() {
//            @Override
//            public void accept(Object o) throws Exception {
//                Log.d("chenhui", "doAfterNext accept:" + o + ",currentThreadName:" + Thread.currentThread().getName());
//            }
//        }).doOnNext(new Consumer<String>() {
//            @Override
//            public void accept(String o) throws Exception {
//                Log.d("chenhui", "doOnNext1 accept:" + o + ",currentThreadName:" + Thread.currentThread().getName());
//            }
//        }).doOnNext(new Consumer<String>() {
//            @Override
//            public void accept(String o) throws Exception {
//                Log.d("chenhui", "doOnNext2 accept:" + o + ",currentThreadName:" + Thread.currentThread().getName());
//            }
//        }).observeOn(Schedulers.io()).subscribe(observer);


//        observable.subscribe(observer);
//        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String o) throws Exception {
//                Log.d("chenhui", "accept:" + o + ",currentThreadName:" + Thread.currentThread().getName());
//            }
//        });


    }

//    @OnClick(R.id.fab)
//    public void onViewClicked() {
//
//
//    }
}
