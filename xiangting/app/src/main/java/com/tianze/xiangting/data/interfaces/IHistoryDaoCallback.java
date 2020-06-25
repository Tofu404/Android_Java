package com.tianze.xiangting.data.interfaces;

import com.ximalaya.ting.android.opensdk.model.track.Track;

import java.util.List;

public interface IHistoryDaoCallback {

    /**
     * 添加历史返回结果
     * @param addResult
     */
    void addHistoryCallback(boolean addResult);

    /**
     * 删除历史返会结果
     * @param delResult
     */
    void delHistoryCallback(boolean delResult);

    /**
     * 清除历史记录返回的结果
     * @param clearResult
     */
    void clearHistoryCallback(boolean clearResult);

    /**
     * 返回的历史记录结果
     * @param historyTracks
     */
    void queryCallback(List<Track> historyTracks);
}
