package cn.landi.playandroid.eventbus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.landi.playandroid.R;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2019/6/20
 * @edit TODO
 */
public class SecondActivity extends AppCompatActivity {

    @BindView(R.id.btn_send)
    Button btnSend;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_send)
    public void onViewClicked() {
        MyEventBus.getDefault().post(new Person("陈辉", 26));
    }
}
