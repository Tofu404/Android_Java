package com.tianze.xiangting.interfaces;

import com.ximalaya.ting.android.opensdk.model.track.Track;

import java.util.List;

public interface IHistoryViewCallback {

    /**
     * 查询结果view回调
     * @param tracks
     */
    void queryResultViewCallback(List<Track> tracks);

    /**
     * 删除item项结果view回调
     */
    void delResultViewCallback();

    /**
     * 删除所有结果view回调
     */
    void clearResultViewCallback();


}
