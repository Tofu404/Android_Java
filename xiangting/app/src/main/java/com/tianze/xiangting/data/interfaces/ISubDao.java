package com.tianze.xiangting.data.interfaces;

import com.tianze.xiangting.base.IBaseDao;
import com.ximalaya.ting.android.opensdk.model.album.Album;

public interface ISubDao extends IBaseDao<ISubViewCallback> {

    /**
     * 增
     * @param album
     */
    void addAlbum(Album album);

    /**
     * 删除
     * @param albumId
     */
    void deleteAlbum(long albumId);

    /**
     * 查询
     */
    void queryAlbum();
}
