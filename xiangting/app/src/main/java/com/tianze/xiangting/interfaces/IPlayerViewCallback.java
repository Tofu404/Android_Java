package com.tianze.xiangting.interfaces;

import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.opensdk.player.service.XmPlayListControl;

public interface IPlayerViewCallback {
    /**
     * 将track的信息显示在播放界面上
     */
    void setDataAndStatus(Track track);

    /**
     * 将track的时长信息显示出来
     */
    void updateTrackDuration(int currPos, int duration);

    /**
     * 将进度条重置
     */
    void resetSeekBarDuration();

    /**
     * 设置播放模式
     */
    void setPlayModelImage(XmPlayListControl.PlayMode playMode,boolean clicked);

    /**
     * 播放器切哥时回调
     */
    void switchSound();

    /**
     * 切换track封面
     */
    void switchTrackCover(int position);
}
