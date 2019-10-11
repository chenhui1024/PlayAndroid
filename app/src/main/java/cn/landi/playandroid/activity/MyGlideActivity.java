package cn.landi.playandroid.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.landi.playandroid.R;
import cn.landi.playandroid.myglide.MyGlide;
import cn.landi.playandroid.myglide.RequestManager;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2019/9/11
 * @edit TODO
 */
public class MyGlideActivity extends AppCompatActivity {

    @BindView(R.id.btn_load_one)
    Button btnLoadOne;
    @BindView(R.id.btn_load_more)
    Button btnLoadMore;
    @BindView(R.id.ll_images)
    LinearLayout llImages;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_glide);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_load_one, R.id.btn_load_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_load_one:
                makeOneImage();
                break;
            case R.id.btn_load_more:
                makeMoreImage();
                break;
        }
    }

    private void makeOneImage() {
        ImageView imageView = new ImageView(this);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(layoutParams);
        llImages.addView(imageView);

        MyGlide.with(this).loading(R.mipmap.ic_launcher).load("http://img1.fr-trading.com/1/5_1014_1567050_650_744.jpg")
                .into(imageView);
    }

    private void makeMoreImage() {
        for (int i=0; i<10; i++) {

            ImageView imageView = new ImageView(this);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(layoutParams);
            llImages.addView(imageView);

            String url = "";
            switch (i) {
                case 0:
                    url = "http://cos.solepic.com/20190408/s_1144051_201904080823061196.jpg";
                    break;
                case 1:
                    url = "http://p.ivideo.sina.com.cn/video/303/813/487/303813487_160_90.jpg";
                    break;
                case 2:
                    url = "http://cos.solepic.com/20190408/s_1144051_201904080823061196.jpg";
                    break;
                case 3:
                    url = "http://cos.solepic.com/20190408/s_1144051_201904080823061196.jpg";
                    break;
                case 4:
                    url = "http://cos.solepic.com/20190408/s_1144051_201904080823061196.jpg";
                    break;
                case 5:
                    url = "http://inews.gtimg.com/newsapp_ls/0/10246416914_196130/0.jpg";
                    break;
                case 6:
                    url = "http://img1.fr-trading.com/1/5_12_1433652_750_750.jpg";
                    break;
                case 7:
                    url = "http://inews.gtimg.com/newsapp_ls/0/10246415865_196130/0.jpg";
                    break;
                case 8:
                    url = "http://img1.fr-trading.com/1/5_225_1338847_800_800.jpg";
                    break;
                case 9:
                    url = "http://imgcn3.guidechem.com/simg/product/2019/8/29/295287155501301.jpg";
                    break;
            }

            MyGlide.with(this).loading(R.mipmap.ic_launcher).load(url).into(imageView);
        }
    }


}
