package cn.landi.playandroid.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.landi.playandroid.R;
import cn.landi.playandroid.constant.RouteUrl;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2018/12/24
 * @edit TODO
 */
@Route(path = RouteUrl.MYDIALOG_ACTIVITY)
public class MyDialogActivity extends AppCompatActivity {

    @BindView(R.id.toobar)
    Toolbar toobar;
    @BindView(R.id.btn_show)
    Button btnShow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mydialog);
        ButterKnife.bind(this);

        new Handler().removeCallbacks(null);
        new Handler().removeCallbacksAndMessages(null);
        toobar.setTitle("自定义对话框");
        setSupportActionBar(toobar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toobar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @OnClick({R.id.btn_show, R.id.btn_alert_show})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_show:
                MyDialog myDialog = new MyDialog(this, R.style.MyDialogTheme);
                myDialog.show();
                break;
            case R.id.btn_alert_show:
                showAlertDialog();
                break;
        }
    }

    private void showAlertDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_mine, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyDialogTheme);
        final Dialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setContentView(view);


        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = display.getWidth()*9/10;// 设置dialog宽度为屏幕的4/5
        dialog.getWindow().setAttributes(lp);
    }

    class MyDialog extends Dialog {

        private Context context;

        public MyDialog(@NonNull Context context) {
            super(context);
            this.context = context;
        }

        public MyDialog(@NonNull Context context, int themeResId) {
            super(context, themeResId);
            this.context = context;
        }

        protected MyDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
            super(context, cancelable, cancelListener);
            this.context = context;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            View view = LayoutInflater.from(context).inflate(R.layout.dialog_mine, null);
            setContentView(view);

            WindowManager windowManager = ((Activity)context).getWindowManager();
            Display display = windowManager.getDefaultDisplay();
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.width = display.getWidth()*4/5;// 设置dialog宽度为屏幕的4/5
            getWindow().setAttributes(lp);
        }
    }

}
