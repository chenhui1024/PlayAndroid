package cn.landi.playandroid.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.alibaba.android.arouter.facade.annotation.Route;

import cn.landi.playandroid.R;
import cn.landi.playandroid.constant.RouteUrl;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2018/12/20
 * @edit TODO
 */
@Route(path = RouteUrl.DYNAMIC_ACTIVITY)
public class DynamicActivity extends AppCompatActivity {

    private LinearLayout linearLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        linearLayout = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.activity_dynamic, null);
        setContentView(linearLayout);

        //Button1
        Button button = new Button(this);
        button.setText("Button1");
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10, 20, 20, 10);
        button.setLayoutParams(layoutParams);

        //Button2
        Button button2 = new Button(this);
        button2.setText("Button2");
        button2.setLayoutParams(layoutParams);

        //Button3
        Button button3 = new Button(this);
        button3.setText("Button3");
        layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(50, 20, 20, 10);
        button3.setLayoutParams(layoutParams);

        LinearLayout child = new LinearLayout(this);
        child.setGravity(Gravity.CENTER);
        child.setOrientation(LinearLayout.HORIZONTAL);
        child.setPadding(20, 20, 20, 20);
        child.addView(button);
        child.addView(button2);
        child.addView(button3);

        linearLayout.addView(child);

        //Button4
        Button button4 = new Button(this);
        button4.setText("Button4");
        layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10, 50, 10, 0);
        button4.setPadding(10, 0, 20, 10);
        button4.setLayoutParams(layoutParams);

        //Button5
        Button button5 = new Button(this);
        button5.setText("Button5");
        layoutParams = new LinearLayout.LayoutParams(100, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10, 10, 0, 0);
        button5.setLayoutParams(layoutParams);

        LinearLayout child2 = new LinearLayout(this);
        child2.setOrientation(LinearLayout.VERTICAL);
        child2.setGravity(Gravity.LEFT);
        child2.addView(button4);
        child2.addView(button5);

        linearLayout.addView(child2);

        RelativeLayout relativeLayout = new RelativeLayout(this);

        Button button6 = new Button(this);
        button6.setText("Button6");
        RelativeLayout.LayoutParams rlParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        rlParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        button6.setLayoutParams(rlParams);

        Button button7 = new Button(this);
        button7.setText("Button7");
        rlParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        rlParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        button7.setLayoutParams(rlParams);

        Button button8 = new Button(this);
        button8.setText("Button8");
        rlParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        rlParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        button8.setLayoutParams(rlParams);

        relativeLayout.addView(button6);
        relativeLayout.addView(button7);
        relativeLayout.addView(button8);
        LinearLayout.LayoutParams rootLay = new LinearLayout.LayoutParams(400, LinearLayout.LayoutParams.WRAP_CONTENT);
        relativeLayout.setLayoutParams(rootLay);

        linearLayout.addView(relativeLayout);

    }
}
