package cn.landi.playandroid.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.landi.playandroid.R;
import cn.landi.playandroid.adapter.SearchAdapter;
import cn.landi.playandroid.bean.SearchItem;
import cn.landi.playandroid.constant.RouteUrl;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2018/11/29
 * @edit TODO
 */
@Route(path = RouteUrl.QUERY_ACTIVITY)
public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.toobar)
    Toolbar toobar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        ButterKnife.bind(this);
        toobar.setTitle("");
        setSupportActionBar(toobar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(this);
        flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
        flexboxLayoutManager.setJustifyContent(JustifyContent.FLEX_START);
        recyclerview.setLayoutManager(flexboxLayoutManager);

        SearchAdapter searchAdapter = new SearchAdapter(initSearchItems());
        recyclerview.setAdapter(searchAdapter);
    }

    private List<SearchItem> initSearchItems() {
        List<SearchItem> items = new ArrayList<>();
        SearchItem searchItem = new SearchItem();
        searchItem.setName("测试1");
        items.add(searchItem);

        searchItem = new SearchItem();
        searchItem.setName("测试1测试1");
        items.add(searchItem);

        searchItem = new SearchItem();
        searchItem.setName("测试1测试1测试1测试1");
        items.add(searchItem);

        searchItem = new SearchItem();
        searchItem.setName("测试1测试1测试1");
        items.add(searchItem);

        searchItem = new SearchItem();
        searchItem.setName("测试1测试1");
        items.add(searchItem);

        searchItem = new SearchItem();
        searchItem.setName("测试1测试1测试1测试1测试1测试1");
        items.add(searchItem);

        return items;
    }
}
