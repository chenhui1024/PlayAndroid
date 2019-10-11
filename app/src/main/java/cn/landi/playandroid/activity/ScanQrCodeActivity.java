package cn.landi.playandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.landi.playandroid.R;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2019/8/16
 * @edit TODO
 */
public class ScanQrCodeActivity extends AppCompatActivity {

    @BindView(R.id.fl_my_container)
    FrameLayout flMyContainer;

    private int REQUEST_CODE = 0x01;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qrcode);
        ButterKnife.bind(this);

        /**
         * 执行扫面Fragment的初始化操作
         */
//        MyScanFragment captureFragment = new MyScanFragment();
//        // 为二维码扫描界面设置定制化界面
//
//        CodeUtils.setFragmentArgs(captureFragment, R.layout.scan_view);
//
//        captureFragment.setAnalyzeCallback(new CodeUtils.AnalyzeCallback() {
//            @Override
//            public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
//                Log.d("chenhui", "onAnalyzeSuccess:" + result);
//                Intent resultIntent = new Intent();
//                Bundle bundle = new Bundle();
//                bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_SUCCESS);
//                bundle.putString(CodeUtils.RESULT_STRING, result);
//                resultIntent.putExtras(bundle);
//            }
//
//            @Override
//            public void onAnalyzeFailed() {
//                Log.d("chenhui", "onAnalyzeFailed");
//                Intent resultIntent = new Intent();
//                Bundle bundle = new Bundle();
//                bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_FAILED);
//                bundle.putString(CodeUtils.RESULT_STRING, "");
//                resultIntent.putExtras(bundle);
//            }
//        });
//
//        getSupportFragmentManager().beginTransaction().replace(R.id.fl_my_container, captureFragment).commit();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        if (requestCode == REQUEST_CODE) {
//            //处理扫描结果（在界面上显示）
//            if (null != data) {
//                Bundle bundle = data.getExtras();
//                if (bundle == null) {
//                    return;
//                }
//                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
//                    String result = bundle.getString(CodeUtils.RESULT_STRING);
//                    Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
//                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
//                    Toast.makeText(ScanQrCodeActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
//                }
//            }
//        }

    }

//    public static class MyScanFragment extends CaptureFragment {
//
//        @Nullable
//        @Override
//        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//            View view = super.onCreateView(inflater, container, savedInstanceState);
//
//            view.findViewById(R.id.turn_on).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    CodeUtils.isLightEnable(true);
//
//                }
//            });
//
//            view.findViewById(R.id.turn_off).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    CodeUtils.isLightEnable(false);
//                }
//            });
//
//
//            return view;
//        }
//    }

}
