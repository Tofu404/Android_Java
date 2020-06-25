package com.tianze.xiangting.interfaces;

import com.ximalaya.ting.android.opensdk.model.track.Track;

import java.util.List;

public interface IAlbumDetailViewCallback {

    //加载数据
    void onAlbumTrackLoaded(List<Track> trackList);

    //上拉加载更多
    void pullUpLoadMore(List<Track> currentTrackList);

    //正在加载
    void onLoading();

    //网络错误
    void onNetworkError();

    //数据为空
    void noReturnEmptyData();

}
