package com.tianze.xiangting.presenters;

import com.tianze.xiangting.base.BaseApplication;
import com.tianze.xiangting.data.Daos.HistoryDao;
import com.tianze.xiangting.data.interfaces.IHistoryDaoCallback;
import com.tianze.xiangting.interfaces.IHistoryPresenter;
import com.tianze.xiangting.interfaces.IHistoryViewCallback;
import com.ximalaya.ting.android.opensdk.model.track.Track;

import java.util.ArrayList;
import java.util.List;

public class HistoryPresenter implements IHistoryDaoCallback, IHistoryPresenter {

    private static HistoryPresenter sHistoryPresenter = null;
    private final HistoryDao mHistoryDao;
    private List<IHistoryViewCallback> mViewCallbacks = new ArrayList<>();
    private List<Track> historyTracks = new ArrayList<>();

    private HistoryPresenter() {
        mHistoryDao = new HistoryDao(BaseApplication.context);
        mHistoryDao.setViewCallback(this);
    }

    public static HistoryPresenter getInstance() {
        if (sHistoryPresenter == null) {
            synchronized (HistoryPresenter.class) {
                if (sHistoryPresenter == null) {
                    sHistoryPresenter = new HistoryPresenter();
                }
            }
        }
        return sHistoryPresenter;
    }

    //数据库操作返回结果
    @Override
    public void addHistoryCallback(boolean addResult) {
        //Dao层数据添加成功之后的回调
    }

    @Override
    public void delHistoryCallback(boolean delResult) {
        if (delResult) {
            for (IHistoryViewCallback viewCallback : mViewCallbacks) {
                viewCallback.delResultViewCallback();
            }
        }
    }

    @Override
    public void clearHistoryCallback(boolean clearResult) {
        if (clearResult) {
            for (IHistoryViewCallback viewCallback : mViewCallbacks) {
                viewCallback.clearResultViewCallback();
            }
        }
    }

    @Override
    public void queryCallback(List<Track> historyTracks) {
        if (historyTracks.size() > 0) {
            this.historyTracks = historyTracks;
        }
        for (IHistoryViewCallback viewCallback : mViewCallbacks) {
            viewCallback.queryResultViewCallback(historyTracks);
        }
    }


    //注册接口回调
    @Override
    public void registerViewCallback(IHistoryViewCallback iHistoryViewCallback) {
        if (iHistoryViewCallback != null && !mViewCallbacks.contains(iHistoryViewCallback)) {
            mViewCallbacks.add(iHistoryViewCallback);
        }
    }

    //注销接口回调
    @Override
    public void unregisteredViewCallback(IHistoryViewCallback iHistoryViewCallback) {
        mViewCallbacks.remove(iHistoryViewCallback);
    }


    //historyPresenter方法调用
    @Override
    public void queryHistory() {
        mHistoryDao.queryHistory();
    }

    @Override
    public void addHistory(Track curModel) {
        //判断是否超过100条记录，判断是否在集合中
        mHistoryDao.queryHistory();
        if (this.historyTracks.size() >= 100) {
            Track track = this.historyTracks.get(this.historyTracks.size() - 1);
            mHistoryDao.delHistory(track.getDataId());
            if (!this.historyTracks.contains(curModel)) {
                mHistoryDao.addHistory(curModel);
            }
        }else {
            if (!this.historyTracks.contains(curModel)) {
                mHistoryDao.addHistory(curModel);
            }
        }
    }

    @Override
    public void delHistory(int currentItem) {
        queryHistory();
        Track track = this.historyTracks.get(currentItem);
        if (track != null) {
            mHistoryDao.delHistory(track.getDataId());
        }
    }

    @Override
    public void clearHistory() {
        mHistoryDao.clearHistory();
    }
}
