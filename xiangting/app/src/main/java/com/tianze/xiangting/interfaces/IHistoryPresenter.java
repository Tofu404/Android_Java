package com.tianze.xiangting.interfaces;

import com.tianze.xiangting.base.IBasePresenter;
import com.ximalaya.ting.android.opensdk.model.track.Track;

public interface IHistoryPresenter extends IBasePresenter<IHistoryViewCallback> {

    /**
     * 查询历史
     */
    void queryHistory();

    /**
     * 添加历史记录
     * @param curModel
     */
    void addHistory(Track curModel);

    /**
     * 删除相对应item项
     * @param currentItem
     */
    void delHistory(int currentItem);

    /**
     * 清除历史记录
     */
    void clearHistory();
}
