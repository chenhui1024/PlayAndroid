package cn.landi.playandroid.eventbus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

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
public class FirstActivity extends AppCompatActivity {

    @BindView(R.id.tv_info)
    TextView tvInfo;
    @BindView(R.id.btn_next)
    Button btnNext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        ButterKnife.bind(this);

        MyEventBus.getDefault().register(this);
    }

    @OnClick(R.id.btn_next)
    public void onViewClicked() {
        Intent intent = new Intent();
        intent.setClass(this, SecondActivity.class);
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void testMethod(Person person) {
        Log.d("chenhui", "name:" + person.getName() + ",age:" + person.getAge());
        Log.d("chenhui", "当前线程名称：" + Thread.currentThread().getName());
//        tvInfo.setText("收到数据：\n" + "名字：" + person.getName() + ",年龄：" + person.getAge());
    }

}
