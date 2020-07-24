package create.by.showme.fragments;

import android.widget.FrameLayout;

import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.youth.banner.Banner;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.indicator.RectangleIndicator;
import com.youth.banner.transformer.AlphaPageTransformer;
import com.youth.banner.transformer.ZoomOutPageTransformer;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import create.by.showme.R;
import create.by.showme.adapters.HeaderBannerAdapter;
import create.by.showme.adapters.HomeViewPaperAdapter;
import create.by.showme.base.BaseFragment;
import create.by.showme.beans.BannerDataBean;
import create.by.showme.beans.Categories;
import create.by.showme.network.Api;
import create.by.showme.network.RetrofitBuilder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends BaseFragment {

    @BindView(R.id.header_view_container)
    FrameLayout mHeaderViewContainer;
    @BindView(R.id.item_type)
    TabLayout mItemType;
    @BindView(R.id.home_view_pager)
    ViewPager2 mHomeViewPager;
    @BindView(R.id.header_banner)
    Banner mHeaderBanner;
    private List<Categories.DataBean> mData;
    private HomeViewPaperAdapter mHomeViewPaperAdapter;

    @Override
    public int setLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void init() {
        mHomeViewPaperAdapter = new HomeViewPaperAdapter(getChildFragmentManager());
        mHomeViewPager.setAdapter(mHomeViewPaperAdapter);
        loadCategories();
        initBanner();
    }

    private void initBanner() {

        int [] imageId = new int[]{
                R.drawable.ic_0,
                R.drawable.ic_1,
                R.drawable.ic_2,
                R.drawable.ic_3,
                R.drawable.ic_4,
                R.drawable.ic_5
        };

        List<BannerDataBean> imageResourceList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            BannerDataBean bannerDataBean = new BannerDataBean(imageId[i]);
            imageResourceList.add(bannerDataBean);
        }

        HeaderBannerAdapter adapter = new HeaderBannerAdapter(imageResourceList);
        mHeaderBanner.setAdapter(adapter);
        mHeaderBanner.setIndicator(new RectangleIndicator(getContext()));
        mHeaderBanner.setPageTransformer(new AlphaPageTransformer());
    }

    private void initTitle() {
        new TabLayoutMediator(mItemType, mHomeViewPager, (tab, position) -> tab.setText(mData.get(position).getTitle())).attach();
    }

    private void loadCategories() {
        Api api = RetrofitBuilder.create();
        Call<Categories> categories = api.getCategories();
        categories.enqueue(new Callback<Categories>() {

            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    assert response.body() != null;
                    if (response.body().getData().size() > 0 && response.body() != null) {
                        mData = response.body().getData();
                        mHomeViewPaperAdapter.setData(mData);
                        initTitle();
                    }
                }
            }

            @Override
            public void onFailure(Call<Categories> call, Throwable t) {

            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            mHeaderBanner.stop();
        } else {
            mHeaderBanner.start();
        }
    }
}
