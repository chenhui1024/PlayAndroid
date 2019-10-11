package cn.landi.componentlib;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2019/6/30
 * @edit TODO
 */
public class LibActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lib);

        Toast.makeText(this, "lib", Toast.LENGTH_SHORT).show();
    }
}
