package cn.landi.playandroid.demo.myhttp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import cn.landi.playandroid.R;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2019/9/22
 * @edit TODO
 */
public class MyHttpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myhttp);
        ImageView imageView = findViewById(R.id.bg);
        imageView.post(new Runnable() {
            @Override
            public void run() {
                draw();
            }
        });
//        String url = "https://api.douban.com/v2/book/1220562";
//        NEHttp.sendJsonRequest(url, "", Response.class, new IJsonDataTransformListener<Response>() {
//            @Override
//            public void onSuccess(Response data) {
//                Log.d("chenhui", "onSuccess:data" + data.toString());
//            }
//
//            @Override
//            public void onFailure() {
//                Log.d("chenhui", "onFailure");
//            }
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void draw() {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(8);

        ImageView imageView = findViewById(R.id.bg);
        Bitmap bitmap = Bitmap.createBitmap(imageView.getWidth(), imageView.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        canvas.drawColor(Color.rgb(0xdc, 0xdc, 0xdc));
        canvas.drawLine(0, 100, 300, 100, paint);

        imageView.setImageBitmap(bitmap);
    }

}
