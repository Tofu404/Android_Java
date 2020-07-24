package create.by.showme.fragments;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.MaterialHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import create.by.showme.R;
import create.by.showme.adapters.ContentAdapter;
import create.by.showme.base.BaseFragment;
import create.by.showme.beans.CategoriesContent;
import create.by.showme.network.Api;
import create.by.showme.network.RetrofitBuilder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PagerFragment extends BaseFragment {


    @BindView(R.id.fresh_header)
    MaterialHeader mFreshHeader;
    @BindView(R.id.content_rv)
    RecyclerView mContentRv;
    @BindView(R.id.fresh_footer)
    ClassicsFooter mFreshFooter;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    private ContentAdapter mAdapter;
    private int mMaterialId;
    private String mMaterialTitle;
    private int mCurrentPage = 1;

    private List<CategoriesContent.DataBean> mDataBeanList = new ArrayList<>();

    @Override
    public int setLayoutId() {
        return R.layout.fragment_pager;
    }

    @Override
    public void init() {
        mContentRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new ContentAdapter();
        mContentRv.setAdapter(mAdapter);

        initEvent();
        getFragmentArguments();
    }

    private void getFragmentArguments() {
        Bundle arguments = getArguments();
        mMaterialId = arguments.getInt("materialId");
        mMaterialTitle = arguments.getString("materialTitle");
    }

    private void initEvent() {
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                Log.e("tianze", "onLoadMore: 触发刷新");
                mCurrentPage = 1;
                loadCategoriesContent(mMaterialId, mCurrentPage);
            }
        });
        mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                Log.e("tianze", "onLoadMore: 触发加载更多");
                mCurrentPage++;
                Log.e("tianze", "onLoadMore: 当前页 == > "+mCurrentPage );
                loadCategoriesContent(mMaterialId, mCurrentPage);
            }
        });
    }

    private void loadCategoriesContent(int materialId, int page) {
        Api api = RetrofitBuilder.create();
        api.getCategoriesContent(materialId, page).enqueue(new Callback<CategoriesContent>() {
            @Override
            public void onResponse(Call<CategoriesContent> call, Response<CategoriesContent> response) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    if (response.body() != null) {
                        //页面加载会来的页面
                        List<CategoriesContent.DataBean> data = response.body().getData();
                        if (mCurrentPage == 1) {
                            mDataBeanList.clear();
                            mDataBeanList.addAll(data);
                            mSmartRefreshLayout.finishRefresh();
                        } else {
                            mDataBeanList.addAll(mDataBeanList.size(), data);
                            mSmartRefreshLayout.finishLoadMore();
                        }
                        mAdapter.setData(mDataBeanList);
                    }
                }
            }

            @Override
            public void onFailure(Call<CategoriesContent> call, Throwable t) {
                if (mCurrentPage > 0) {
                    mCurrentPage--;
                }
                mSmartRefreshLayout.finishLoadMore();
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        loadCategoriesContent(mMaterialId, 0);
    }
}
