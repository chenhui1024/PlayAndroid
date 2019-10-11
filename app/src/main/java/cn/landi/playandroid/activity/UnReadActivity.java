package cn.landi.playandroid.activity;

import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.PasswordTransformationMethod;
import android.text.method.ReplacementTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.landi.playandroid.R;
import cn.landi.playandroid.constant.RouteUrl;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2018/12/19
 * @edit TODO
 */
@Route(path = RouteUrl.UNREAD_ACTIVITY)
public class UnReadActivity extends AppCompatActivity {

    @BindView(R.id.toobar)
    Toolbar toobar;
    @BindView(R.id.imv_wechat)
    ImageView imvWechat;
    @BindView(R.id.imv_alipay)
    ImageView imvAlipay;
    @BindView(R.id.tv_alipay_message)
    TextView tvAlipayMessage;
    @BindView(R.id.tv_alipay_message_alert)
    TextView tvAlipayMessageAlert;
    @BindView(R.id.et_input)
    EditText tvInput;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unread);
        ButterKnife.bind(this);

        toobar.setTitle("未读消息");
        setSupportActionBar(toobar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toobar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        new QBadgeView(this).bindTarget(imvWechat).setBadgeNumber(11).setBadgeBackgroundColor(0xffff0000)
                .setBadgeGravity(Gravity.END | Gravity.TOP).setGravityOffset(0, 0, false)
                .setBadgeTextSize(17, false).setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
            @Override
            public void onDragStateChanged(int dragState, Badge badge, View targetView) {

            }
        });

//        new QBadgeView(this).bindTarget(imvAlipay).setBadgeNumber(100).setBadgeBackgroundColor(0xffff0000)
//                .setBadgeGravity(Gravity.END | Gravity.TOP).setGravityOffset(0, 0, false)
//                .setBadgeTextSize(17, false).setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
//            @Override
//            public void onDragStateChanged(int dragState, Badge badge, View targetView) {
//
//            }
//        });


        new QBadgeView(this).bindTarget(tvAlipayMessageAlert).setBadgeNumber(100).setBadgeBackgroundColor(0xffff0000)
                .setBadgeGravity(Gravity.CENTER | Gravity.END).setGravityOffset(0, 0, false)
                .setBadgeTextSize(22, false).setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
            @Override
            public void onDragStateChanged(int dragState, Badge badge, View targetView) {

            }
        });

//        tvInput.setTransformationMethod(PasswordTransformationMethod.getInstance());
        tvInput.setTransformationMethod(new InputLowerToUpper());
        tvInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                Toast.makeText(UnReadActivity.this, "id=" + i, Toast.LENGTH_SHORT).show();

                return false;
            }
        });
    }

    public class InputLowerToUpper extends ReplacementTransformationMethod {
        @Override
        protected char[] getOriginal() {
            char[] lower = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
            return lower;
        }

        @Override
        protected char[] getReplacement() {
            char[] upper = {'1', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
            return upper;
        }

    }

}
