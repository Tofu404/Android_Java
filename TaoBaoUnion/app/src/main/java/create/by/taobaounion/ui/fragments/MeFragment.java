package create.by.taobaounion.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import create.by.taobaounion.R;
import create.by.taobaounion.modle.MeItem;
import create.by.taobaounion.ui.BaseFragment;
import create.by.taobaounion.adapters.MeRvAdapter;
import create.by.taobaounion.ui.activities.MainActivity;

public class MeFragment extends BaseFragment {

    @BindView(R.id.me_rv)
    RecyclerView meRv;

    @Override
    protected int setResourceId() {
        return R.layout.fragment_me;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        List<MeItem> meItems = buildMeItem();
        MeRvAdapter adapter = new MeRvAdapter(meItems);
        meRv.setAdapter(adapter);
        return view;
    }

    private List<MeItem> buildMeItem() {
        List<MeItem> meItemList = new ArrayList<>();
        meItemList.add(new MeItem(MainActivity.class,R.drawable.ic_home,MeItem.HEADER,"头部项"));
        meItemList.add(new MeItem());
        meItemList.add(new MeItem(MainActivity.class,R.drawable.ic_home,MeItem.NORMAL,"第一组"));
        meItemList.add(new MeItem(MainActivity.class,R.drawable.ic_home,MeItem.NORMAL,"第一组"));
        meItemList.add(new MeItem(MainActivity.class,R.drawable.ic_home,MeItem.NORMAL,"第一组"));
        meItemList.add(new MeItem(MainActivity.class,R.drawable.ic_home,MeItem.NORMAL,"第一组"));
        meItemList.add(new MeItem());
        meItemList.add(new MeItem(MainActivity.class,R.drawable.ic_search,MeItem.NORMAL,"第二组"));
        meItemList.add(new MeItem(MainActivity.class,R.drawable.ic_search,MeItem.NORMAL,"第二组"));
        meItemList.add(new MeItem(MainActivity.class,R.drawable.ic_search,MeItem.NORMAL,"第二组"));
        meItemList.add(new MeItem(MainActivity.class,R.drawable.ic_search,MeItem.NORMAL,"第二组"));
        meItemList.add(new MeItem(MainActivity.class,R.drawable.ic_search,MeItem.NORMAL,"第二组"));
        meItemList.add(new MeItem(MainActivity.class,R.drawable.ic_search,MeItem.NORMAL,"第二组"));
        meItemList.add(new MeItem(MainActivity.class,R.drawable.ic_search,MeItem.NORMAL,"第二组"));
        meItemList.add(new MeItem());
        meItemList.add(new MeItem(MainActivity.class,R.drawable.ic_discount,MeItem.NORMAL,"第三组"));
        meItemList.add(new MeItem(MainActivity.class,R.drawable.ic_discount,MeItem.NORMAL,"第三组"));
        meItemList.add(new MeItem(MainActivity.class,R.drawable.ic_discount,MeItem.NORMAL,"第三组"));
        meItemList.add(new MeItem(MainActivity.class,R.drawable.ic_discount,MeItem.NORMAL,"第三组"));
        meItemList.add(new MeItem(MainActivity.class,R.drawable.ic_discount,MeItem.NORMAL,"第三组"));
        meItemList.add(new MeItem(MainActivity.class,R.drawable.ic_discount,MeItem.NORMAL,"第三组"));
        meItemList.add(new MeItem(MainActivity.class,R.drawable.ic_discount,MeItem.NORMAL,"第三组"));
        meItemList.add(new MeItem(MainActivity.class,R.drawable.ic_discount,MeItem.NORMAL,"第三组"));
        return meItemList;
    }
}
