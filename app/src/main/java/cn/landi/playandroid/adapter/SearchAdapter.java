package cn.landi.playandroid.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;
import java.util.Random;

import cn.landi.playandroid.R;
import cn.landi.playandroid.bean.SearchItem;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2018/11/29
 * @edit TODO
 */
public class SearchAdapter extends BaseQuickAdapter<SearchItem, BaseViewHolder> {

    /**
     * Tab colors
     */
    private static final int[] TAB_COLORS = new int[]{
            Color.parseColor("#90C5F0"),
            Color.parseColor("#91CED5"),
            Color.parseColor("#F88F55"),
            Color.parseColor("#C0AFD0"),
            Color.parseColor("#E78F8F"),
            Color.parseColor("#67CCB7"),
            Color.parseColor("#F6BC7E")
    };

    public int randomTagColor() {
        int randomNum = new Random().nextInt();
        int position = randomNum % TAB_COLORS.length;
        if (position < 0) {
            position = -position;
        }
        return TAB_COLORS[position];
    }

    public SearchAdapter(@Nullable List<SearchItem> data) {
        super(R.layout.item_search_histroy, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchItem item) {
        helper.setText(R.id.tv_search_histroy, item.getName())
                .setBackgroundColor(R.id.tv_search_histroy, randomTagColor());
    }
}
