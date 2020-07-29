package create.by.gank.fragments;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

import butterknife.BindView;
import create.by.gank.R;
import create.by.gank.adapters.CategoryPagerAdapter;
import create.by.gank.base.BaseFragment;
import create.by.gank.base.BasePresenter;
import create.by.gank.bean.GanHuoCategory;
import create.by.gank.presenters.impl.GanHuoPresenterImpl;
import create.by.gank.view_callback.GanHuoCallback;

public class GanHuoFragment extends BaseFragment implements GanHuoCallback {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.category_container)
    ViewPager categoryContainer;
    private CategoryPagerAdapter mAdapter;

    @Override
    protected void init() {
        mAdapter = new CategoryPagerAdapter(getChildFragmentManager());
        categoryContainer.setAdapter(mAdapter);
        tabLayout.setupWithViewPager(categoryContainer);
    }

    @Override
    protected BasePresenter initPresenter() {
        GanHuoPresenterImpl ganHuoPresenter = new GanHuoPresenterImpl();
        ganHuoPresenter.registerCallback(this);
        ganHuoPresenter.load();
        return ganHuoPresenter;
    }

    @Override
    public int setLayoutResource() {
        return R.layout.fragment_ganhuo;
    }

    @Override
    public void loaded(List<GanHuoCategory.DataBean> data) {
        if (data != null) {
            mAdapter.setData(data);
        }
    }
}
