package cn.landi.playandroid.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lljjcoder.style.citylist.Toast.ToastUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.landi.playandroid.R;
import cn.landi.playandroid.constant.RouteUrl;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2019/5/22
 * @edit TODO
 */
@Route(path = RouteUrl.GRIDVIEW_ACTIVITY)
public class GridviewActivity extends AppCompatActivity {

    @BindView(R.id.toobar)
    Toolbar toobar;
    @BindView(R.id.viewpage)
    ViewPager viewpage;
    @BindView(R.id.footer)
    LinearLayout footer;

    private ArrayList<View> contentViews = new ArrayList<>();
    private ArrayList<ImageView> dots = new ArrayList<>();
    private ArrayList<MenuModel> menuModels = new ArrayList<>();
    private int currentPos = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview);
        ButterKnife.bind(this);

        toobar.setTitle("gridview");
        setSupportActionBar(toobar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        initMenuModels();
        initContentView();
        initDots();

        viewpage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.d("chenhui", "onPageSelected position ..." + position);
                refreshDots(position);
                currentPos = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewpage.setAdapter(new PagerAdapter() {

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//                super.destroyItem(container, position, object);
                container.removeView(contentViews.get(position));
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                container.addView(contentViews.get(position));
                return contentViews.get(position);
            }

            @Override
            public int getCount() {
                return contentViews.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }
        });
    }

    private void initMenuModels() {
        menuModels.clear();

        menuModels.add(new MenuModel(R.mipmap.icon_alipay, "测试1"));
        menuModels.add(new MenuModel(R.mipmap.icon_alipay, "测试2"));
        menuModels.add(new MenuModel(R.mipmap.icon_alipay, "测试3"));
        menuModels.add(new MenuModel(R.mipmap.icon_alipay, "测试4"));
        menuModels.add(new MenuModel(R.mipmap.icon_alipay, "测试5"));

        menuModels.add(new MenuModel(R.mipmap.icon_alipay, "测试6"));
        menuModels.add(new MenuModel(R.mipmap.icon_alipay, "测试7"));
        menuModels.add(new MenuModel(R.mipmap.icon_alipay, "测试8"));
        menuModels.add(new MenuModel(R.mipmap.icon_alipay, "测试9"));
        menuModels.add(new MenuModel(R.mipmap.icon_alipay, "测试10"));

        menuModels.add(new MenuModel(R.mipmap.icon_alipay, "测试11"));
        menuModels.add(new MenuModel(R.mipmap.icon_alipay, "测试12"));
        menuModels.add(new MenuModel(R.mipmap.icon_alipay, "测试13"));
        menuModels.add(new MenuModel(R.mipmap.icon_alipay, "测试14"));
        menuModels.add(new MenuModel(R.mipmap.icon_alipay, "测试15"));
    }

    private void refreshDots(int position) {

        for (int i=0; i<3; i++) {
            dots.get(i).setBackgroundResource(R.drawable.circle_normal);
            if (i == position) {
                Log.d("chenhui", "设置为选中..." + position);
                dots.get(position).setBackgroundResource(R.drawable.circle_selected);
            }
        }

    }

    private void initDots() {
        dots.clear();
        footer.removeAllViews();
        for (int i=0; i<3; i++) {

            ImageView imageView = new ImageView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(5, 3, 5, 3);
            imageView.setLayoutParams(params);

            if (i == 0) {
                imageView.setBackgroundResource(R.drawable.circle_selected);
            } else {
                imageView.setBackgroundResource(R.drawable.circle_normal);
            }

            footer.addView(imageView);
            dots.add(imageView);
        }
    }

    private void initContentView() {
        contentViews.clear();

        final int itemCountEachPage = 5;
        for (int i = 0; i < 3; i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.gridview, null);
            GridView gridView = view.findViewById(R.id.gridview);
            ArrayList<MenuModel> tmpMenus = new ArrayList<>();
            for (int j = i * itemCountEachPage; j < i * itemCountEachPage + itemCountEachPage; j++) {
                tmpMenus.add(menuModels.get(j));
            }
            gridView.setAdapter(new GridviewAdapter(tmpMenus));

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    int pos = currentPos * itemCountEachPage + position;
                    MenuModel menuModel = menuModels.get(pos);
                    ToastUtils.showShortToast(GridviewActivity.this, menuModel.getIconName());
                }
            });


            contentViews.add(view);
        }
    }

    class GridviewAdapter extends BaseAdapter {

        private ArrayList<MenuModel> mMenus = new ArrayList<>();

        public GridviewAdapter(ArrayList<MenuModel> mMenus) {
            this.mMenus = mMenus;
        }

        @Override
        public int getCount() {
            return mMenus.size();
        }

        @Override
        public Object getItem(int i) {
            return mMenus.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {

            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(GridviewActivity.this).inflate(R.layout.imageview_textview, null);

                viewHolder = new ViewHolder();
                viewHolder.imvIcon = convertView.findViewById(R.id.imv_icon);
                viewHolder.tvName = convertView.findViewById(R.id.tv_name);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            MenuModel menuModel = mMenus.get(i);
            viewHolder.tvName.setText(menuModel.getIconName());
            viewHolder.imvIcon.setImageResource(menuModel.getIcon());

            return convertView;
        }
    }

    class ViewHolder {

        TextView tvName;
        ImageView imvIcon;
    }

    class MenuModel {

        private int icon;
        private String iconName;

        public MenuModel(int icon, String iconName) {
            this.icon = icon;
            this.iconName = iconName;
        }

        public int getIcon() {
            return icon;
        }

        public void setIcon(int icon) {
            this.icon = icon;
        }

        public String getIconName() {
            return iconName;
        }

        public void setIconName(String iconName) {
            this.iconName = iconName;
        }
    }

}
