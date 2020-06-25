package com.tianze.xiangting.interfaces;

import com.ximalaya.ting.android.opensdk.model.album.Album;

public interface IAlbumDetailPresenter {

    //获取目标专辑信息
    Album getTargetAlbum();

    //设置专辑信息
    void setTargetAlbum(Album albumId);

    //加载数据
    void getAlbumTrackList();

    //加载成功
    void onAlbumTrackLoaded();

    //上拉加载更多
    void pullUpLoadMore();

    //注册回调接口
    void registerViewCallback(IAlbumDetailViewCallback viewCallback);

    //注销回调接口
    void unregisteredViewCallback(IAlbumDetailViewCallback viewCallback);

}
