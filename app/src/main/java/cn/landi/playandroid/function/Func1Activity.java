package cn.landi.playandroid.function;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.landi.playandroid.R;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2019/8/5
 * @edit TODO
 */
public class Func1Activity extends AppCompatActivity {

    @BindView(R.id.btn_start)
    Button btnStart;
    @BindView(R.id.toobar)
    Toolbar toobar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_func1);
        ButterKnife.bind(this);

        setSupportActionBar(toobar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toobar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        register();
    }

    private void register() {
        FunctionManager.getInstance().addFunction(new FunctionNoParamNoResult("noparamnoresult") {
            @Override
            public void function() {
                Log.d("chenhui", "没有参数也没有返回的方法被调用");
            }
        });
        FunctionManager.getInstance().addFunction(new FunctionHasParamNoResult<FuncUser>("hasparamnoresult") {
            @Override
            public void function(FuncUser user) {
                Log.d("chenhui", "有参数但是没有返回的方法被调用");
                Log.d("chenhui", "user:" + user.toString());
            }
        });
        FunctionManager.getInstance().addFunction(new FunctionNoParamHasResult<FuncUser>("noparamhasresult") {
            @Override
            public FuncUser function() {
                Log.d("chenhui", "没有参数有返回值的方法被调用");
                return new FuncUser("chenhui", 27);
            }
        });
        FunctionManager.getInstance().addFunction(new FunctionHasParamHasResult<FuncUser, FuncUser>("hasparamhasresult") {
            @Override
            public FuncUser function(FuncUser user) {
                Log.d("chenhui", "有参数也有返回的方法被调用");
                Log.d("chenhui", "user:" + user.toString());
                return new FuncUser("fanxian", 26);
            }
        });
    }

    @OnClick(R.id.btn_start)
    public void onViewClicked() {

        startActivity(new Intent(this, Func2Activity.class));

    }
}
