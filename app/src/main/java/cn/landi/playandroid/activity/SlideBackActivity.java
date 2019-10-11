package cn.landi.playandroid.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;

import cn.landi.playandroid.R;
import cn.landi.playandroid.constant.RouteUrl;
import cn.landi.playandroid.view.SlideBackLayout;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2018/12/16
 * @edit TODO
 */
@Route(path = RouteUrl.SLIDEBACK_ACTIVITY)
public class SlideBackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        new SlideBackLayout(this).attach2Activity(this, null);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_back);
    }
}
