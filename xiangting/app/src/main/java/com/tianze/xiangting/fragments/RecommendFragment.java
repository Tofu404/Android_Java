package com.tianze.xiangting.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tianze.xiangting.AlbumDetailActivity;
import com.tianze.xiangting.R;
import com.tianze.xiangting.adapters.AlbumLIstAdapter;
import com.tianze.xiangting.interfaces.IRecommendViewCallback;
import com.tianze.xiangting.presenters.AlbumDetailPresenter;
import com.tianze.xiangting.presenters.RecommendPresenter;
import com.tianze.xiangting.views.UILoader;
import com.ximalaya.ting.android.opensdk.model.album.Album;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RecommendFragment extends Fragment
        implements IRecommendViewCallback, UILoader.OnNetworkErrorViewClickListener,
        AlbumLIstAdapter.OnRecommendItemClickListener {

    private static final String TAG = "RecommendFragment";

    @BindView(R.id.recommend_rv)
    RecyclerView recommendRv;
    @BindView(R.id.click_reload)
    ConstraintLayout clickReload;

    private AlbumLIstAdapter adapter;
    private Unbinder bind;
    private RecommendPresenter recommendPresenter;
    private UILoader myViewLoader;
    private static List<Album> loadedAlbumList = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        myViewLoader = new UILoader(Objects.requireNonNull(getContext())) {
            @Override
            public View getSuccessView(ViewGroup container) {
                return createSuccessView(container);
            }
        };

        myViewLoader.setOnNetworkViewClickListener(this);

        bind = ButterKnife.bind(this, myViewLoader);
        initView();
        initPresenter();

        //返回viwe的时候要和父容器解绑
        if (myViewLoader.getParent() instanceof ViewGroup) {
            ((ViewGroup) myViewLoader.getParent()).removeView(myViewLoader);
        }
        return myViewLoader;
    }

    private void initPresenter() {
        recommendPresenter = RecommendPresenter.getInstance();
        recommendPresenter.registerViewCallback(this);
        if (loadedAlbumList == null) {
        recommendPresenter.getGuessLikeAlbumData();
        }
        refreshRecommendUI(loadedAlbumList);
    }

    private void initView() {
        recommendRv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AlbumLIstAdapter();
        adapter.setOnRecommendItemClickListener(this);
        recommendRv.setAdapter(adapter);
    }

    private View createSuccessView(ViewGroup container) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_recommend, container, false);
    }

    private void refreshRecommendUI(List<Album> albumList) {
        if (albumList != null) {
            myViewLoader.setCurrentState(UILoader.UIState.SUCCESS);
            adapter.setData(albumList);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
        recommendPresenter.unRegisterViewCallback(this);
    }

    @Override
    public void getGuessLikeAlbumData(List<Album> albumList) {
        if (albumList != null) {
            if (albumList.size() > 0) {
                myViewLoader.setCurrentState(UILoader.UIState.SUCCESS);
                loadedAlbumList = albumList;
                refreshRecommendUI(albumList);
            } else {
                myViewLoader.setCurrentState(UILoader.UIState.EMPTY);
            }
        }
    }

    @Override
    public void pullDown2FreshMore() {

    }

    @Override
    public void pullUp2LoadMore() {

    }

    @Override
    public void loading() {
        myViewLoader.setCurrentState(UILoader.UIState.LOADING);
    }

    @Override
    public void networkError() {
        myViewLoader.setCurrentState(UILoader.UIState.NETWORK_ERRPR);
    }

    @Override
    public void contentEmpty() {
        myViewLoader.setCurrentState(UILoader.UIState.EMPTY);
    }

    @Override
    public void onNetworkViewClick() {
        //点击网络错误页面重新加载
        if (recommendPresenter != null) {
            recommendPresenter.getGuessLikeAlbumData();
        }
    }

    @Override
    public void onAlbumItemClick(Album album) {
        AlbumDetailPresenter.getInstance().setTargetAlbum(album);
        //跳转到详情页面
        AlbumDetailActivity.start(getContext());
    }
}
