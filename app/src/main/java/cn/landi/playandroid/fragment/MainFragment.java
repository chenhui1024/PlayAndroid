package cn.landi.playandroid.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.landi.playandroid.adapter.MainAdapter;
import cn.landi.playandroid.bean.MainModel;
import cn.landi.playandroid.R;
import cn.landi.playandroid.activity.SlideBack2Activity;
import cn.landi.playandroid.constant.RouteUrl;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2018/11/28
 * @edit TODO
 */
@Route(path = RouteUrl.MAIN_FRAGMENT)
public class MainFragment extends Fragment {

    @BindView(R.id.rv_main)
    RecyclerView rvMain;
    @BindView(R.id.smart_refresh)
    SmartRefreshLayout smartRefresh;
    Unbinder unbinder;

    private Banner banner;
    private MainAdapter mainAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, view);

        rvMain.setLayoutManager(new LinearLayoutManager(getActivity()));
        mainAdapter = new MainAdapter(initModels());
        rvMain.setAdapter(mainAdapter);
        View headView = inflater.inflate(R.layout.layout_banner, null);
        banner = headView.findViewById(R.id.banner);
        mainAdapter.addHeaderView(headView);
        initBanner();

        smartRefresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        MainFragment.this.onLoadMore();
                    }
                }, 5000);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        smartRefresh.finishRefresh();
                    }
                }, 3000);
            }
        });

        mainAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                MainModel mainModel = (MainModel) adapter.getData().get(position);
//                Toast.makeText(getActivity(), mainModel.getAddress(), Toast.LENGTH_SHORT).show();

//                getActivity().startActivity(new Intent(getActivity(), DetailActivity.class));

//                getActivity().startActivity(new Intent(getActivity(), SlideBackActivity.class));
                getActivity().startActivity(new Intent(getActivity(), SlideBack2Activity.class));

            }
        });

        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (banner != null) {
            if (isVisibleToUser) {
                Log.d("chenhui", "setUserVisibleHint isVisibleToUser");
                banner.startAutoPlay();
            } else {
                Log.d("chenhui", "setUserVisibleHint not VisibleToUser");
                banner.stopAutoPlay();
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (banner != null) {
            Log.d("chenhui", "onPause hidden");
            banner.stopAutoPlay();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (banner != null) {
            Log.d("chenhui", "onResume display");
            banner.startAutoPlay();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (isHidden()) {
            if (banner != null) {
                Log.d("chenhui", "onHiddenChanged hidden");
                banner.stopAutoPlay();
            }
        } else {
            if (banner != null) {
                Log.d("chenhui", "onHiddenChanged not hidden");
                banner.startAutoPlay();
            }
        }
    }

    private void onLoadMore() {
        MainModel mainModel = new MainModel();
        mainModel.setName("郑建安");
        mainModel.setCompany("福建联迪");
        mainModel.setAge(26);
        mainModel.setAddress("软件园");
        mainAdapter.addData(mainModel);

        smartRefresh.finishLoadMore();
    }


    private void initBanner() {
        Integer[] bannerImages = new Integer[]{R.mipmap.ic_banner_logo1, R.mipmap.ic_banner_logo2, R.mipmap.ic_banner_logo3};
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new MyImageLoader());
        //设置图片集合
        banner.setImages(Arrays.asList(bannerImages));
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    private List<MainModel> initModels() {
        List<MainModel> modelList = new ArrayList<>();
        MainModel mainModel = new MainModel();
        mainModel.setAddress("福州市鼓楼区");
        mainModel.setAge(24);
        mainModel.setCompany("福建联迪商用设备有限公司");
        mainModel.setName("张三");
        modelList.add(mainModel);

        mainModel = new MainModel();
        mainModel.setAddress("福州市鼓楼区");
        mainModel.setAge(24);
        mainModel.setCompany("福建联迪商用设备有限公司");
        mainModel.setName("张三");
        modelList.add(mainModel);

        mainModel = new MainModel();
        mainModel.setAddress("福州市鼓楼区");
        mainModel.setAge(24);
        mainModel.setCompany("福建联迪商用设备有限公司");
        mainModel.setName("张三");
        modelList.add(mainModel);

        mainModel = new MainModel();
        mainModel.setAddress("福州市鼓楼区");
        mainModel.setAge(24);
        mainModel.setCompany("福建联迪商用设备有限公司");
        mainModel.setName("张三");
        modelList.add(mainModel);

        return modelList;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    class MyImageLoader  extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            imageView.setImageResource((Integer) path);
        }
    }

}
