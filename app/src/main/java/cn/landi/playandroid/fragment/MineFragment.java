package cn.landi.playandroid.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;

import cn.landi.playandroid.R;
import cn.landi.playandroid.constant.RouteUrl;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2018/11/28
 * @edit TODO
 */
@Route(path = RouteUrl.MINE_FRAGMENT)
public class MineFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        return view;
    }
}
