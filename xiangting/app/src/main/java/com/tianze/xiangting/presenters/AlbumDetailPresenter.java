package com.tianze.xiangting.presenters;

import com.tianze.xiangting.base.BaseApplication;
import com.tianze.xiangting.interfaces.IAlbumDetailPresenter;
import com.tianze.xiangting.interfaces.IAlbumDetailViewCallback;
import com.tianze.xiangting.utils.Constants;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.model.album.Album;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.opensdk.model.track.TrackList;
import com.ximalaya.ting.android.opensdk.player.XmPlayerManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlbumDetailPresenter implements IAlbumDetailPresenter {

    private static final String TAG = "AlbumDetailPresenter";

    public static AlbumDetailPresenter albumDetailPresenter = null;

    List<IAlbumDetailViewCallback> viewCallbacks = new ArrayList<>();

    private Album targetAlbum = null;

    private List<Track> currentTrackList = new ArrayList<>();

    private int currentPage = Integer.parseInt(Constants.TRACK_PAGE);
    private boolean isLoadMore = false;
    private final XmPlayerManager mXmPlayerManager;

    //私有化构造方法
    private AlbumDetailPresenter() {
        mXmPlayerManager = XmPlayerManager.getInstance(BaseApplication.context);
    }

    //懒汉式单例
    public static AlbumDetailPresenter getInstance() {
        if (albumDetailPresenter == null) {
            synchronized (AlbumDetailPresenter.class) {
                if (albumDetailPresenter == null) {
                    albumDetailPresenter = new AlbumDetailPresenter();
                }
            }
        }
        return albumDetailPresenter;
    }

    @Override
    public void getAlbumTrackList() {
        currentTrackList.clear();
        currentPage = 1;
        isLoadMore = false;
        //显示正在加载视图
        for (IAlbumDetailViewCallback viewCallback : viewCallbacks) {
            viewCallback.onLoading();
        }
        doLoad();
    }

    private void doLoad() {
        Map<String, String> map = new HashMap<>();
        map.put(DTransferConstants.ALBUM_ID, targetAlbum.getId() + "");
        map.put(DTransferConstants.SORT, "asc");
        map.put(DTransferConstants.PAGE_SIZE,"50");
        map.put(DTransferConstants.PAGE, currentPage + "");

        CommonRequest.getTracks(map, new IDataCallBack<TrackList>() {
            @Override
            public void onSuccess(TrackList trackList) {
                if (trackList != null) {
                    if (trackList.getTracks().size() > 0) {
                        currentTrackList.addAll(trackList.getTracks());
                    }
                    if (isLoadMore) {
                        for (IAlbumDetailViewCallback viewCallback : viewCallbacks) {
                            viewCallback.pullUpLoadMore(currentTrackList);
                        }
                    } else {
                        for (IAlbumDetailViewCallback viewCallback : viewCallbacks) {
                            viewCallback.onAlbumTrackLoaded(currentTrackList);
                        }
                    }
                }else {
                    for (IAlbumDetailViewCallback viewCallback : viewCallbacks) {
                        viewCallback.noReturnEmptyData();
                    }
                }
            }

            @Override
            public void onError(int i, String s) {
                currentPage--;
                for (IAlbumDetailViewCallback viewCallback : viewCallbacks) {
                    viewCallback.onNetworkError();
                }
            }
        });
    }

    @Override
    public void onAlbumTrackLoaded() {

    }

    @Override
    public void pullUpLoadMore() {
        this.isLoadMore = true;
        this.currentPage++;
        doLoad();
    }

    @Override
    public void registerViewCallback(IAlbumDetailViewCallback viewCallback) {
        if (viewCallback != null && !viewCallbacks.contains(viewCallback)) {
            viewCallbacks.add(viewCallback);
        }
    }

    @Override
    public void unregisteredViewCallback(IAlbumDetailViewCallback viewCallback) {
        if (viewCallback != null && viewCallbacks.contains(viewCallback)) {
            viewCallbacks.remove(viewCallback);
        }
    }

    @Override
    public Album getTargetAlbum() {
        return this.targetAlbum;
    }

    //获取到目标专辑
    @Override
    public void setTargetAlbum(Album albumId) {
        if (albumId != null) {
            this.targetAlbum = albumId;
        }
    }
}
