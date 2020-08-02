package create.by.gank.fragments;

import android.graphics.Color;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import create.by.gank.activity.GirlDetailActivity;
import create.by.gank.R;
import create.by.gank.adapters.GirlAdapter;
import create.by.gank.base.BaseFragment;
import create.by.gank.base.BasePresenter;
import create.by.gank.bean.GirlBean;
import create.by.gank.presenters.GirlPresenter;
import create.by.gank.presenters.impl.GirlPresenterImpl;
import create.by.gank.view_callback.GirlCallback;

public class GirlFragment extends BaseFragment implements GirlCallback, GirlAdapter.RecyclerViewListener {

    @BindView(R.id.girl_rv)
    RecyclerView girlRv;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    private List<GirlBean.DataBean> girlBeanList = new ArrayList<>();
    private GirlAdapter mGirlAdapter;

    //当前页面
    private int mCurrentPage = 1;
    //加载页面的数据数量
    private static final int DATA_NUM = 10;
    private GirlPresenter mGirlPresenter;

    @Override
    protected void init() {
        girlRv.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.left = 20;
                outRect.top = 20;
                outRect.right = 20;
                outRect.bottom = 20;
            }
        });
        girlRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mGirlAdapter = new GirlAdapter();
        mGirlAdapter.setRecyclerViewListener(this);
        girlRv.setAdapter(mGirlAdapter);

        refresh.setColorSchemeColors(Color.BLUE, Color.GREEN);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mCurrentPage = 1;
                mGirlPresenter.refresh(mCurrentPage, DATA_NUM);
                mGirlAdapter.setLoadMoreStatus(GirlAdapter.LOADING);
            }
        });
    }

    @Override
    protected BasePresenter initPresenter() {
        mGirlPresenter = new GirlPresenterImpl();
        mGirlPresenter.load(mCurrentPage, DATA_NUM);
        mGirlPresenter.registerCallback(this);
        return mGirlPresenter;
    }

    @Override
    public int setLayoutResource() {
        return R.layout.fragment_gril;
    }

    @Override
    public void loaded(List<GirlBean.DataBean> data) {
        if (data != null) {
            girlBeanList = data;
            mGirlAdapter.setData(girlBeanList);
        }
        refresh.setRefreshing(false);
    }

    @Override
    public void loadMore(List<GirlBean.DataBean> data) {
        if (data != null && data.size() > 0) {
            girlBeanList.addAll(girlBeanList.size(), data);
            mGirlAdapter.setData(girlBeanList);
            mGirlAdapter.setLoadMoreStatus(GirlAdapter.SUCCEED);
        }
    }

    @Override
    public void loadError() {
        mCurrentPage--;
        refresh.setRefreshing(false);
        mGirlAdapter.setLoadMoreStatus(GirlAdapter.ERROR);
    }

    @Override
    public void loadEmpty() {
        mGirlAdapter.setLoadMoreStatus(GirlAdapter.EMPTY);
    }

    @Override
    public void recyclerViewLoadMore() {
        if (mGirlPresenter != null) {
            mCurrentPage++;
            mGirlPresenter.loadMore(mCurrentPage, DATA_NUM);
        }
    }

    @Override
    public void onItemClick(GirlBean.DataBean dataBean) {
        GirlDetailActivity.start(getContext(),dataBean);
    }
}
