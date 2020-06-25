package com.tianze.xiangting.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tianze.xiangting.AlbumDetailActivity;
import com.tianze.xiangting.R;
import com.tianze.xiangting.adapters.AlbumLIstAdapter;
import com.tianze.xiangting.data.Daos.SubDao;
import com.tianze.xiangting.data.interfaces.ISubViewCallback;
import com.tianze.xiangting.presenters.AlbumDetailPresenter;
import com.ximalaya.ting.android.opensdk.model.album.Album;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SubscriptionFragment extends Fragment implements ISubViewCallback, AlbumLIstAdapter.OnRecommendItemClickListener {

    @BindView(R.id.sub_album_list)
    RecyclerView subAlbumList;
    private Unbinder mBind;
    private SubDao mSubDao;
    private List<Album> subAlbums;
    private AlbumLIstAdapter mAlbumLIstAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_subscription, container, false);
        mBind = ButterKnife.bind(this, view);
        intDao();
        initView();
        return view;
    }

    private void initView() {
        subAlbumList.setLayoutManager(new LinearLayoutManager(getContext()));
        mAlbumLIstAdapter = new AlbumLIstAdapter();
        mAlbumLIstAdapter.setOnRecommendItemClickListener(this);
        subAlbumList.setAdapter(mAlbumLIstAdapter);
    }

    private void intDao() {
        mSubDao = SubDao.getInstance();
        mSubDao.registerViewCallback(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mSubDao.queryAlbum();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }

    //数据库相关回调
    @Override
    public void addResult(boolean addResult) {
    }

    @Override
    public void deleteResult(boolean deleteSuccess) {
    }


    @Override
    public void queryResult(List<Album> subAlbums) {
        if (subAlbums.size() > 0) {
            this.subAlbums = subAlbums;
            mAlbumLIstAdapter.setData(this.subAlbums);
        } else {
            this.subAlbums = new ArrayList<>();
        }
    }

    @Override
    public void onAlbumItemClick(Album album) {
        AlbumDetailPresenter.getInstance().setTargetAlbum(album);
        //跳转到详情页面
        AlbumDetailActivity.start(getContext());
    }
}
