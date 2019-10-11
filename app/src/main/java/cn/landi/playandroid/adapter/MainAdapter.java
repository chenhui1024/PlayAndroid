package cn.landi.playandroid.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.landi.playandroid.R;
import cn.landi.playandroid.bean.MainModel;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2018/11/29
 * @edit TODO
 */
public class MainAdapter extends BaseQuickAdapter<MainModel, BaseViewHolder> {

    public MainAdapter(@Nullable List<MainModel> data) {
        super(R.layout.item_main, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MainModel item) {
        helper.setText(R.id.tv_address, item.getAddress())
                .setText(R.id.tv_name, item.getName())
                .setText(R.id.tv_age, "" + item.getAge())
                .setText(R.id.tv_company, item.getCompany());
    }

}
