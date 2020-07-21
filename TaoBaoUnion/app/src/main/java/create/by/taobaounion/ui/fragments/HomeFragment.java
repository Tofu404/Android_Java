package create.by.taobaounion.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import create.by.taobaounion.R;
import create.by.taobaounion.adapters.MyViewPagerAdapter;
import create.by.taobaounion.modle.Categories;
import create.by.taobaounion.presenter.Impl.HomePresenter;
import create.by.taobaounion.ui.BaseFragment;
import create.by.taobaounion.utils.CacheUtil;
import create.by.taobaounion.utils.SpUtil;
import create.by.taobaounion.view_callback.IHomeViewCallback;

public class HomeFragment extends BaseFragment implements IHomeViewCallback {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.categories_page_item)
    ViewPager mCategoriesPageItem;
    private HomePresenter mHomePresenter;
    private MyViewPagerAdapter mAdapter;

    @Override
    protected int setResourceId() {
        return R.layout.fragment_home_1;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        mAdapter = new MyViewPagerAdapter(getChildFragmentManager());
        mCategoriesPageItem.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mCategoriesPageItem);
        initPresenter();
        return view;
    }

    @Override
    protected void initPresenter() {
        mHomePresenter = new HomePresenter();
        mHomePresenter.registerViewCallBack(this);
        mHomePresenter.loadCategories();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mHomePresenter.nuRegisterViewCallBack();
    }

    @Override
    public void onLoadFinish(Categories categories) {
        if (categories != null) {
            mAdapter.setData(categories.getData());
        }
    }

    @Override
    public void loadError() {

    }
}
