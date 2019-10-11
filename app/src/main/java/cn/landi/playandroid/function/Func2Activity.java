package cn.landi.playandroid.function;

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
public class Func2Activity extends AppCompatActivity {

    @BindView(R.id.toobar)
    Toolbar toobar;
    @BindView(R.id.btn_invoke)
    Button btnInvoke;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_func2);
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
    }

    @OnClick(R.id.btn_invoke)
    public void onViewClicked() {
        FunctionManager.getInstance().invoke("noparamnoresult");
        FuncUser funcUser = FunctionManager.getInstance().invokeNoParamHasResult("noparamhasresult", FuncUser.class);
        Log.d("chenhui", "返回的：" + funcUser.toString());
        FunctionManager.getInstance().invokeHasParamNoResult("hasparamnoresult", new FuncUser("chenhui1", 27));
        funcUser = FunctionManager.getInstance().invokeHasParamHasResult("hasparamhasresult", new FuncUser("fanxian1", 26), FuncUser.class);
        Log.d("chenhui", "返回的：" + funcUser.toString());
    }
}
