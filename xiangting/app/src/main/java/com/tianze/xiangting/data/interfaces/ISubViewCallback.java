package com.tianze.xiangting.data.interfaces;

import com.ximalaya.ting.android.opensdk.model.album.Album;

import java.util.List;

public interface ISubViewCallback {

    /**
     * 添加返回的结果
     * @param addResult
     */
    void addResult(boolean addResult);

    /**
     * 删除返回的结果
     * @param deleteSuccess
     */
    void deleteResult(boolean deleteSuccess);

    /**
     * 查询返回的结果
     * @param subAlbum
     */
    void queryResult(List<Album> subAlbum);
}
