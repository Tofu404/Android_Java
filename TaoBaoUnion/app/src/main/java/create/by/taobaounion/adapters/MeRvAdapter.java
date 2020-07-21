package create.by.taobaounion.adapters;


import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

import create.by.taobaounion.R;
import create.by.taobaounion.modle.MeItem;

public class MeRvAdapter extends BaseMultiItemQuickAdapter<MeItem, BaseViewHolder> {


    public MeRvAdapter(@Nullable List<MeItem> data) {
        super(data);
        addItemType(MeItem.HEADER, R.layout.layout_header_item);
        addItemType(MeItem.NORMAL, R.layout.layout_normal_item);
        addItemType(MeItem.PARTITION, R.layout.layout_partition);
    }


    @Override
    protected void convert(BaseViewHolder holder, MeItem item) {
        switch (item.getItemType()) {
            case MeItem.HEADER:
                break;
            case MeItem.NORMAL:
                break;
            case MeItem.PARTITION:
                break;
        }
    }
}
