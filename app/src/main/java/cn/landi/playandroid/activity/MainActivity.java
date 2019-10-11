package cn.landi.playandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.FitWindowsLinearLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.landi.componentlib.LibActivity;
import cn.landi.playandroid.R;
import cn.landi.playandroid.constant.RouteUrl;
import cn.landi.playandroid.demo.myhttp.MyHttpActivity;
import cn.landi.playandroid.eventbus.FirstActivity;
import cn.landi.playandroid.function.Func1Activity;
import cn.landi.playandroid.injectview.InjectViewActivity;
import cn.landi.playandroid.util.RouteUtil;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.viewpage)
    ViewPager viewpage;
    @BindView(R.id.bottom_navigate)
    BottomNavigationView bottomNavigate;
    @BindView(R.id.toobar)
    Toolbar toobar;
    @BindView(R.id.navigationview)
    NavigationView navigationview;
    @BindView(R.id.drawerlayout)
    DrawerLayout drawerlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toobar.setTitle("首页");
        setSupportActionBar(toobar);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this, drawerlayout, toobar, 0, 0);

        actionBarDrawerToggle.syncState();
        drawerlayout.addDrawerListener(actionBarDrawerToggle);
        navigationview.setItemIconTintList(null);
        navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerlayout.closeDrawers();
                switch (item.getItemId()) {
                    case R.id.action_fold_toorbar:
                        RouteUtil.toActivity(RouteUrl.DETAIL_ACTIVITY);
                        break;
                    case R.id.action_toolbar:
                        RouteUtil.toActivity(RouteUrl.TOOLBAR_ACTIVITY);
                        break;
                    case R.id.action_sliding_pane_layout:
                        break;
                    case R.id.action_swipe_back1:
                        RouteUtil.toActivity(RouteUrl.SLIDEBACK_ACTIVITY);
                        break;
                    case R.id.action_swipe_back2:
                        RouteUtil.toActivity(RouteUrl.SLIDEBACK2_ACTIVITY);
                        break;
                    case R.id.action_unread:
                        RouteUtil.toActivity(RouteUrl.UNREAD_ACTIVITY);
                        break;
                    case R.id.action_dynamic:
                        RouteUtil.toActivity(RouteUrl.DYNAMIC_ACTIVITY);
                        break;
                    case R.id.action_mydialog:
                        RouteUtil.toActivity(RouteUrl.MYDIALOG_ACTIVITY);
                        break;
                    case R.id.action_upload_log:
                        RouteUtil.toActivity(RouteUrl.UPLOAD_LOG_ACTIVITY);
                        break;
                    case R.id.action_retrofit:
                        RouteUtil.toActivity(RouteUrl.RETROFIT_ACTIVITY);
                        break;
                    case R.id.action_stepview:
                        RouteUtil.toActivity(RouteUrl.STEPVIEW_ACTIVITY);
                        break;
                    case R.id.action_gridview:
                        RouteUtil.toActivity(RouteUrl.GRIDVIEW_ACTIVITY);
                        break;
                    case R.id.action_jsview:
                        RouteUtil.toActivity(RouteUrl.JS_ACTIVITY);
                        break;
                    case R.id.action_myeventbus:
//                        startActivity(new Intent(MainActivity.this, FirstActivity.class));
//                        startActivity(new Intent(MainActivity.this, LibActivity.class));
                        startActivity(new Intent(MainActivity.this, Func1Activity.class));
                        break;
                    case R.id.action_scan_qrcode:
                        startActivity(new Intent(MainActivity.this, ScanQrCodeActivity.class));
                        break;
                    case R.id.action_zxing:
                        startActivity(new Intent(MainActivity.this, ZXingQrCodeActivity.class));
                        break;
                    case R.id.action_bt:
                        startActivity(new Intent(MainActivity.this, BlueToothActivity.class));
                        break;
                    case R.id.action_myglide:
                        startActivity(new Intent(MainActivity.this, MyGlideActivity.class));
                        break;
                    case R.id.action_inject:
                        startActivity(new Intent(MainActivity.this, InjectViewActivity.class));
                        break;
                    case R.id.action_myhttp:
                        startActivity(new Intent(MainActivity.this, MyHttpActivity.class));
                        break;
                }

                return false;
            }
        });

        bottomNavigate.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.tb_one:
                        clickTo(0);
                        return true;
                    case R.id.tb_two:
                        clickTo(1);
                        return true;
                    case R.id.tb_three:
                        clickTo(2);
                        return true;
                }
                return false;
            }
        });

        MainPageAdapter adapter = new MainPageAdapter(getSupportFragmentManager(), initFragments());
        viewpage.setAdapter(adapter);
        viewpage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changeTo(position);
                supportInvalidateOptionsMenu();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


//        View view = getWindow().getDecorView();
//        if (view instanceof ViewGroup) {
//            ViewGroup viewGroup = (ViewGroup) view;
//            LinearLayout linearLayout = (LinearLayout) viewGroup.getChildAt(0);
//            Log.d("chenhui", "view type:" + linearLayout.getChildAt(0).getClass());
//            Log.d("chenhui", "view type:" + linearLayout.getChildAt(1).getClass());
//            Log.d("chenhui", "child counts:" + linearLayout.getChildCount());
//
//            FrameLayout frameLayout = (FrameLayout) linearLayout.getChildAt(1);
//            Log.d("chenhui", "FrameLayout child view:" + frameLayout.getChildAt(0).getClass());
//            Log.d("chenhui", "FrameLayout child counts:" + frameLayout.getChildCount());
//
//            FitWindowsLinearLayout fitWindowsLinearLayout = (FitWindowsLinearLayout) frameLayout.getChildAt(0);
//            Log.d("chenhui", "FitWindowsLinearLayout child view:" + fitWindowsLinearLayout.getChildAt(0).getClass());
//            Log.d("chenhui", "FitWindowsLinearLayout child counts:" + fitWindowsLinearLayout.getChildCount());
//
//        } else {
//            Log.d("chenhui", "view is not viewgourp !!!");
//        }


//        Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> e) throws Exception {
//                e.onNext("");
//                Log.d("chenhui", "subscribe ThreadName:" + Thread.currentThread().getName());
//            }
//        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Exception {
//                Log.d("chenhui", "accept ThreadName:" + Thread.currentThread().getName());
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_navagation, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        int curentItem = viewpage.getCurrentItem();
        menu.findItem(R.id.action_query).setVisible(curentItem != 1);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_query:
                RouteUtil.toActivity(RouteUrl.QUERY_ACTIVITY);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private List<Fragment> initFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(RouteUtil.getFragment(RouteUrl.MAIN_FRAGMENT));
        fragments.add(RouteUtil.getFragment(RouteUrl.ORDERLIST_FRAGMENT));
        fragments.add(RouteUtil.getFragment(RouteUrl.MINE_FRAGMENT));
        return fragments;
    }

    private void clickTo(int pos) {
        viewpage.setCurrentItem(pos);
        supportInvalidateOptionsMenu();
    }

    private void changeTo(int pos) {
        switch (pos) {
            case 0:
                bottomNavigate.setSelectedItemId(R.id.tb_one);
                getSupportActionBar().setTitle("首页");
                break;
            case 1:
                bottomNavigate.setSelectedItemId(R.id.tb_two);
                getSupportActionBar().setTitle("订单记录");
                break;
            case 2:
                bottomNavigate.setSelectedItemId(R.id.tb_three);
                getSupportActionBar().setTitle("我的");
                break;
        }
    }

    class MainPageAdapter extends FragmentStatePagerAdapter {

        private List<Fragment> fragments;

        public MainPageAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
        }
    }

}
