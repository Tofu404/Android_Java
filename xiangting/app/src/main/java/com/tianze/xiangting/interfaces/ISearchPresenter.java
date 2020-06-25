package com.tianze.xiangting.interfaces;

import com.tianze.xiangting.base.IBasePresenter;

public interface ISearchPresenter extends IBasePresenter<ISearchViewCallback> {

    /**
     * 加载热词
     */
    void loadHotWorld();

    /**
     * 执行搜索操作
     * @param keyWorld
     */
    void doSearch(String keyWorld);

    /**
     * 获取推荐关键词
     */
    void getSuggestWords(String keyWord);

    /**
     * 加载更多
     * @param keyWord
     */
    void loadMore(String keyWord);
}
