package cn.landi.playandroid.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.alibaba.android.arouter.facade.annotation.Route;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.landi.playandroid.R;
import cn.landi.playandroid.constant.RouteUrl;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2019/1/10
 * @edit TODO
 */
@Route(path = RouteUrl.TOOLBAR_ACTIVITY)
public class ToolbarActivity extends AppCompatActivity {

    @BindView(R.id.toobar)
    Toolbar toobar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
        ButterKnife.bind(this);

        toobar.setTitle("Toolbar");
        setSupportActionBar(toobar);

    }
}
