package cn.landi.playandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.landi.playandroid.R;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2019/8/17
 * @edit TODO
 */
public class ZXingQrCodeActivity extends AppCompatActivity {

    @BindView(R.id.et_qrcode)
    EditText etQrcode;
    @BindView(R.id.btn_scan)
    Button btnScan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxing_qrcode);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_scan)
    public void onViewClicked() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanResult != null) {
            // handle scan result
            Log.d("chenhui", "result:" + scanResult.toString());
            Toast.makeText(this,scanResult.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
