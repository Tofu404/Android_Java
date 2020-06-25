package com.tianze.xiangting.interfaces;

import com.tianze.xiangting.base.IBasePresenter;
import com.ximalaya.ting.android.opensdk.model.track.Track;

import java.util.List;

public interface IPlayerPresenter extends IBasePresenter<IPlayerViewCallback> {

    /**
     * 暂停播放
     */
    void myPlayPause();

    /**
     * 开始播放
     */
    void myPlayStart();

    /**
     * 上一首
     */
    void myPlayPre();

    /**
     * 下一首
     */
    void myPlayNext();

    /**
     * 切换播放模式
     */
    void mySwitchPlayModel();

    /**
     * 点击播放列表
     */
    void myClickPlayList();

    /**
     * 获取到播放列表
     */
    void mySetTrackList(List<Track> trackList, int position);

    /**
     * 快进
     */
    void mySetCurrentDuration(int currentDuration);

    /**
     * 播放指定的曲目
     */
    void myPlayByIndex(int index);



}
