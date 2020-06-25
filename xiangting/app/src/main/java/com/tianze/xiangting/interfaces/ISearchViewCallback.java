package com.tianze.xiangting.interfaces;

import com.ximalaya.ting.android.opensdk.model.album.Album;
import com.ximalaya.ting.android.opensdk.model.word.QueryResult;

import java.util.List;

public interface ISearchViewCallback {

    /**
     * 返回热词
     * @param hotWords
     */
    void loadHotWorldCallback(List<String> hotWords);

    /**
     * 返回搜索到专辑信息
     * @param albums
     */
    void searchResultCallback(List<Album> albums);

    /**
     * 返回关键字的结果
     * @param suggestWords
     */
    void suggestResultCallback(List<QueryResult> keyWordList);

    /**
     * 网络错误
     */
    void onNetWorkError();
}
