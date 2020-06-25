package com.tianze.xiangting.data.interfaces;

import com.ximalaya.ting.android.opensdk.model.track.Track;

public interface IHistoryDao {

    /**
     * 添加历史
     * @param track
     */
    void addHistory(Track track);

    /**
     * 根据trackId删除历史记录
     * @param trechId
     */
    void delHistory(long trechId);

    /**
     * 查询全部数据
     */
    void queryHistory();

    /**
     * 删除所有历史记录
     */
    void clearHistory();

}
