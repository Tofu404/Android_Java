package create.by.gank.fragments;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import create.by.gank.R;
import create.by.gank.adapters.CategoryDataAdapter;
import create.by.gank.base.BaseFragment;
import create.by.gank.base.BasePresenter;
import create.by.gank.bean.GanHuoBean;
import create.by.gank.bean.GanHuoCategory;
import create.by.gank.presenters.GanHuoDataPresenter;
import create.by.gank.presenters.impl.GanHuoDataPresenterImpl;
import create.by.gank.view_callback.GanHuoDataCallback;

public class GanHuoCategoryFragment extends BaseFragment implements GanHuoDataCallback {

    private static final int PAGE_SIZE = 10;

    @BindView(R.id.ganhuo_data_rv)
    RecyclerView ganhuoDataRv;

    private int mCurrentPage = 1;
    private String mType;
    private List<GanHuoBean.DataBean> mDataBeans = new ArrayList<>();
    private CategoryDataAdapter mAdapter;

    @Override
    protected void init() {
        Bundle arguments = getArguments();
        assert arguments != null;
        mType = arguments.getString("type");

        ganhuoDataRv.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.left = 20;
                outRect.top = 20;
                outRect.right = 20;
                outRect.bottom = 20;
            }
        });
        ganhuoDataRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new CategoryDataAdapter();
        ganhuoDataRv.setAdapter(mAdapter);
    }

    @Override
    protected BasePresenter initPresenter() {
        GanHuoDataPresenter ganHuoDataPresenter = new GanHuoDataPresenterImpl();
        ganHuoDataPresenter.load(mType, mCurrentPage, PAGE_SIZE);
        ganHuoDataPresenter.registerCallback(this);
        return ganHuoDataPresenter;
    }

    @Override
    public int setLayoutResource() {
        return R.layout.fragment_category;
    }

    @Override
    public void loaded(List<GanHuoBean.DataBean> data) {
        if (data != null) {
            mDataBeans = data;
            mAdapter.setData(data);
        }
    }

    @Override
    public void loadMore() {

    }

    @Override
    public void loadError() {
        Toast toast = Toast.makeText(getContext(), "", Toast.LENGTH_SHORT);
        toast.setText("加载数据出错");
        toast.show();
    }
}
