package cn.landi.playandroid.injectview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cn.landi.playandroid.R;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2019/9/17
 * @edit TODO
 */
@ContentView(R.layout.activity_inject)
public class InjectViewActivity extends AppCompatActivity {

    @InjectView(R.id.btn_1)
    Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectManager.inject(this);

        btn.setText("测试成功");
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

    @OnClick({R.id.btn_1, R.id.btn_2})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
                Toast.makeText(this, "btn_1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_2:
                Toast.makeText(this, "btn_2", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
