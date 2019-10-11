package cn.landi.playandroid.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.landi.playandroid.R;
import cn.landi.playandroid.constant.RouteUrl;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2018/11/28
 * @edit TODO
 */
@Route(path = RouteUrl.ORDERLIST_FRAGMENT)
public class OrderListFragment extends Fragment {

    @BindView(R.id.btn_scale)
    Button btnScale;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        btnScale.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                Log.d("chenhui", "view.getLeft:" + view.getLeft());
                Log.d("chenhui", "view.getTop:" + view.getTop());
                Log.d("chenhui", "view.getTranslationX:" + view.getTranslationX());
                Log.d("chenhui", "view.getTranslationY:" + view.getTranslationY());
                Log.d("chenhui", "view.getX:" + view.getX());
                Log.d("chenhui", "view.getY:" + view.getY());

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        view.setX(motionEvent.getRawX());
                        view.setY(motionEvent.getRawY()-160);
                        break;
                }

                return false;
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_scale)
    public void onViewClicked() {
//        ViewCompat.animate(btnScale).alpha(0).scaleX(0).scaleY(0).setDuration(400).setListener(new ViewPropertyAnimatorListener() {
//            @Override
//            public void onAnimationStart(View view) {
//                Log.d("chenhui", "onAnimationStart");
//            }
//
//            @Override
//            public void onAnimationEnd(View view) {
//                Log.d("chenhui", "onAnimationEnd");
//            }
//
//            @Override
//            public void onAnimationCancel(View view) {
//                Log.d("chenhui", "onAnimationCancel");
//            }
//        }).start();


    }
}
