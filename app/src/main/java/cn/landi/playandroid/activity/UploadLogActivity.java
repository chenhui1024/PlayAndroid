package cn.landi.playandroid.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.landicorp.android.eptapi.utils.BytesUtil;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.landi.playandroid.R;
import cn.landi.playandroid.constant.RouteUrl;
import cn.landi.playandroid.util.TecentTransferHelper;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2019/2/14
 * @edit TODO
 */
@Route(path = RouteUrl.UPLOAD_LOG_ACTIVITY)
public class UploadLogActivity extends AppCompatActivity {

    @BindView(R.id.toobar)
    Toolbar toolbar;
    @BindView(R.id.btn_upload)
    Button btnUpload;

    CityPickerView mPicker=new CityPickerView();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_log);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mPicker.init(this);


//        decode();
    }


    private void encrypt() {
        try {
            String data = "52289C3F94F68CA6E43BF6D27D1BAC4A+C1001+1001+1562950301+{\"indexPage\":\"1\"}";
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
//            byte[] k = new byte[] {(byte) 0xB8, 0x15, (byte) 0xFC, 0x56, (byte) 0xD6, (byte) 0xF2, 0x47,(byte) 0xdf,(byte) 0x8D,(byte) 0xB7,(byte) 0xa6,0x77,0x39,0x5d,(byte) 0xf3,0x73};
//            byte[] k = "B815FC56D6F247df8DB7a677395df373".getBytes("UTF-8");
            byte[] k = "B815FC56".getBytes("UTF-8");
            SecretKey secretKey = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(k));
//            cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(new byte[] {22, 44, 66, 88, 11, 33, 55, 77}));
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(new byte[] {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00}));
            byte[] result = cipher.doFinal(data.getBytes());

            Log.d("chenhui", "des:" + BytesUtil.bytes2HexString(result));

//            String base64 = Base64.encodeToString(result, Base64.DEFAULT);
            byte[] base64 = Base64.encode(result, Base64.DEFAULT);
            Log.d("chenhui", "结果: " + new String(base64));
            //C777DD2C6B6EBE33606812A7BA9F5371D993101BD82468564F14AE35E7D3C4086AF8E5FE1F778A61
            //c777dd2c6b6ebe33606812a7ba9f5371d993101bd82468564f14ae35e7d3c408

            //x3fdLGtuvjNgaBKnup9TcdmTEBvYJGhWTxSuNefTxAhq+OX+H3eKYQ==
            //x3fdLGtuvjNgaBKnup9TcdmTEBvYJGhWTxSuNefTxAhq+OX+H3eKYQ==
            //x3fdLGtuvjNgaBKnup9TcdmTEBvYJGhWTxSuNefTxAg=
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("chenhui", "exception:" + e.getMessage());
        }
    }

    private void decode() {
        try {
            String data = "tjOewDceCNTlJ1df28NaVA==";
            byte[] result = Base64.decode(data.getBytes(), Base64.DEFAULT);

            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            byte[] k = "B815FC56".getBytes("UTF-8");
            SecretKey secretKey = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(k));
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(new byte[] {22, 44, 66, 88, 11, 33, 55, 77}));
//            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(new byte[] {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00}));
            byte[] res = cipher.doFinal(result);
            Log.d("chenhui", "des:" + new String(res));

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("chenhui", "exception:" + e.getMessage());
        }
    }

    @OnClick(R.id.btn_upload)
    public void onViewClicked() {

//        TecentTransferHelper helper = new TecentTransferHelper();
//        helper.uploadFile2Tecent(this, "PoliceFined.txt", "/sdcard/PoliceFined/PoliceFined.txt");


        //添加默认的配置，不需要自己定义
        CityConfig cityConfig = new CityConfig.Builder().build();
        mPicker.setConfig(cityConfig);

//监听选择点击事件及返回结果
        mPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {

                //省份
                if (province != null) {
                    Log.d("chenhui", "province:" + province.getName());
                }

                //城市
                if (city != null) {
                    Log.d("chenhui", "city:" + city.getName());
                }

                //地区
                if (district != null) {
                    Log.d("chenhui", "district:" + district.getName());
                }

            }

            @Override
            public void onCancel() {
                Log.d("chenhui", "cancel");
            }
        });

        //显示
        mPicker.showCityPicker( );

    }
}
