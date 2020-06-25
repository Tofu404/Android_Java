package com.tianze.xiangting.interfaces;

import com.ximalaya.ting.android.opensdk.model.album.Album;

import java.util.List;

public interface IRecommendViewCallback {

    //获取猜你喜欢的数据
    void getGuessLikeAlbumData(List<Album> albumList);

    //下拉刷新
    void pullDown2FreshMore();

    //上拉加载更多
    void pullUp2LoadMore();

    /*加载成功*/
    void loading();

    /*网络错误*/
    void networkError();

    /*没有内容*/
    void contentEmpty();

}
