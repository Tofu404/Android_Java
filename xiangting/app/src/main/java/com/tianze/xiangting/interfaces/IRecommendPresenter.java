package com.tianze.xiangting.interfaces;

public interface IRecommendPresenter {

    //获取猜你喜欢的数据
    void getGuessLikeAlbumData();

    //下拉刷新
    void pullDown2Fresh();

    //上拉加载更多
    void pullUp2LoadMore();
}
