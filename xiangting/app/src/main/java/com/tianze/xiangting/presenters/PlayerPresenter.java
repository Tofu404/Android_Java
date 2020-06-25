package com.tianze.xiangting.presenters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.tianze.xiangting.base.BaseApplication;
import com.tianze.xiangting.interfaces.IPlayerPresenter;
import com.tianze.xiangting.interfaces.IPlayerViewCallback;
import com.ximalaya.ting.android.opensdk.model.PlayableModel;
import com.ximalaya.ting.android.opensdk.model.advertis.Advertis;
import com.ximalaya.ting.android.opensdk.model.advertis.AdvertisList;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.opensdk.player.XmPlayerManager;
import com.ximalaya.ting.android.opensdk.player.advertis.IXmAdsStatusListener;
import com.ximalaya.ting.android.opensdk.player.service.IXmPlayerStatusListener;
import com.ximalaya.ting.android.opensdk.player.service.XmPlayListControl;
import com.ximalaya.ting.android.opensdk.player.service.XmPlayerException;

import java.util.ArrayList;
import java.util.List;

public class PlayerPresenter implements IPlayerPresenter, IXmPlayerStatusListener, IXmAdsStatusListener {

    private static PlayerPresenter sPlayerPresenter = null;

    private XmPlayerManager mXmPlayerManager;
    private Track currentTrack = null;
    public List<Track> trackList = new ArrayList<>();

    private XmPlayListControl.PlayMode[] mPlayModes = new XmPlayListControl.PlayMode[]{
            XmPlayListControl.PlayMode.PLAY_MODEL_LIST_LOOP,
            XmPlayListControl.PlayMode.PLAY_MODEL_SINGLE_LOOP,
            XmPlayListControl.PlayMode.PLAY_MODEL_RANDOM
    };

    private final SharedPreferences.Editor mEditPlayModleSP;

    @SuppressLint("CommitPrefEdits")
    private PlayerPresenter() {
        //初始化SP
        mEditPlayModleSP = BaseApplication.context.getSharedPreferences("currentPlayModel", Context.MODE_PRIVATE).edit();
        mXmPlayerManager = XmPlayerManager.getInstance(BaseApplication.context);
        mXmPlayerManager.addPlayerStatusListener(this);
        mXmPlayerManager.addAdsStatusListener(this);
    }

    public static PlayerPresenter getInstance() {
        if (sPlayerPresenter == null) {
            synchronized (PlayerPresenter.class) {
                if (sPlayerPresenter == null) {
                    sPlayerPresenter = new PlayerPresenter();
                }
            }
        }
        return sPlayerPresenter;
    }

    private List<IPlayerViewCallback> mViewCallbacks = new ArrayList<>();


    @Override
    public void registerViewCallback(IPlayerViewCallback viewCallback) {
        if (viewCallback != null && !mViewCallbacks.contains(viewCallback)) {
            mViewCallbacks.add(viewCallback);
        }
    }

    @Override
    public void unregisteredViewCallback(IPlayerViewCallback viewCallback) {
        if (viewCallback != null) {
            mViewCallbacks.remove(viewCallback);
        }
    }


    //我写的接口！
    @Override
    public void myPlayPause() {
        if (mXmPlayerManager != null) {
            mXmPlayerManager.pause();
        }
    }

    @Override
    public void myPlayStart() {
        if (mXmPlayerManager != null) {
            onSoundPrepared();
        }
    }

    @Override
    public void myPlayPre() {
        if (mXmPlayerManager.hasPreSound()) {
            mXmPlayerManager.playPre();
            for (IPlayerViewCallback viewCallback : mViewCallbacks) {
                viewCallback.switchTrackCover(mXmPlayerManager.getCurrentIndex());
            }
        }
    }

    @Override
    public void myPlayNext() {
        if (mXmPlayerManager != null) {
            mXmPlayerManager.playNext();
            for (IPlayerViewCallback viewCallback : mViewCallbacks) {
                viewCallback.switchTrackCover(mXmPlayerManager.getCurrentIndex());
            }
        }
    }

    @Override
    public void mySwitchPlayModel() {
        XmPlayListControl.PlayMode currentPlayMode = mXmPlayerManager.getPlayMode();
        for (int i = 0; i < mPlayModes.length; i++) {
            if (currentPlayMode == mPlayModes[i]) {
                if (i == mPlayModes.length - 1) {
                    currentPlayMode = mPlayModes[0];
                    mEditPlayModleSP.putInt("currentModel",0);
                } else {
                    currentPlayMode = mPlayModes[i + 1];
                    mEditPlayModleSP.putInt("currentModel",i+1);
                }
                mEditPlayModleSP.commit();
                break;
            }
        }
        mXmPlayerManager.setPlayMode(currentPlayMode);
        for (IPlayerViewCallback viewCallback : mViewCallbacks) {
            viewCallback.setPlayModelImage(currentPlayMode,true);
        }

    }

    @Override
    public void myClickPlayList() {

    }

    @Override
    public void mySetTrackList(List<Track> trackList, int position) {
        this.trackList = trackList;
        this.currentTrack = trackList.get(position);
        if (mXmPlayerManager!=null){
            mXmPlayerManager.setPlayList(trackList, position);
        }
    }

    @Override
    public void mySetCurrentDuration(int duration) {
        mXmPlayerManager.seekTo(duration);
    }

    @Override
    public void myPlayByIndex(int index) {
        mXmPlayerManager.play(index);
    }

    //播放器状态相关状态回调
    @Override
    public void onPlayStart() {

    }

    @Override
    public void onPlayPause() {

    }

    @Override
    public void onPlayStop() {

    }

    @Override
    public void onSoundPlayComplete() {
        //播放将进度条设置为零
        for (IPlayerViewCallback viewCallback : mViewCallbacks) {
            viewCallback.resetSeekBarDuration();
        }
    }

    @Override
    public void onSoundPrepared() {
        //这个时候才能进行播放
        mXmPlayerManager.play();
        //将track信息设置到播放列表上
        for (IPlayerViewCallback viewCallback : mViewCallbacks) {
            viewCallback.setDataAndStatus(currentTrack);
        }
    }

    @Override
    public void onSoundSwitch(PlayableModel lastModel, PlayableModel curModel) {
        if (curModel instanceof Track) {
            Track track = (Track) curModel;
            this.currentTrack = track;
            for (IPlayerViewCallback viewCallback : mViewCallbacks) {
                viewCallback.setDataAndStatus(track);
            }

            //将声音添加到历史记录中去
            HistoryPresenter.getInstance().addHistory(track);
        }

        for (IPlayerViewCallback viewCallback : mViewCallbacks) {
            viewCallback.switchSound();
        }
    }

    @Override
    public void onBufferingStart() {

    }

    @Override
    public void onBufferingStop() {

    }

    @Override
    public void onBufferProgress(int i) {

    }

    @Override
    public void onPlayProgress(int currPos, int duration) {
        for (IPlayerViewCallback viewCallback : mViewCallbacks) {
            viewCallback.updateTrackDuration(currPos, duration);
        }
    }

    @Override
    public boolean onError(XmPlayerException e) {
        return false;
    }


    //广告相关状态回调
    @Override
    public void onStartGetAdsInfo() {

    }

    @Override
    public void onGetAdsInfo(AdvertisList advertisList) {

    }

    @Override
    public void onAdsStartBuffering() {

    }

    @Override
    public void onAdsStopBuffering() {

    }

    @Override
    public void onStartPlayAds(Advertis advertis, int i) {

    }

    @Override
    public void onCompletePlayAds() {

    }

    @Override
    public void onError(int i, int i1) {

    }

}
