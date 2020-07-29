package create.by.gank.fragments;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.List;

import butterknife.BindView;
import create.by.gank.R;
import create.by.gank.base.BaseFragment;
import create.by.gank.base.BasePresenter;
import create.by.gank.bean.GirlBean;
import create.by.gank.presenters.GirlPresenter;
import create.by.gank.presenters.impl.GirlPresenterImpl;
import create.by.gank.view_callback.GirlCallback;

public class GirlFragment extends BaseFragment implements GirlCallback {

    @BindView(R.id.girl_rv)
    RecyclerView girlRv;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;

    @Override
    protected void init() {
        girlRv.setLayoutManager(new LinearLayoutManager(getContext()));

        girlRv.setAdapter();
    }

    @Override
    protected BasePresenter initPresenter() {
        GirlPresenter girlPresenter = new GirlPresenterImpl();
        girlPresenter.load();
        girlPresenter.registerCallback(this);
        return girlPresenter;
    }

    @Override
    public int setLayoutResource() {
        return R.layout.fragment_gril;
    }

    @Override
    public void loaded(List<GirlBean.DataBean> data) {

    }

    @Override
    public void loadError() {

    }

    @Override
    public void loadEmpty() {

    }
}
