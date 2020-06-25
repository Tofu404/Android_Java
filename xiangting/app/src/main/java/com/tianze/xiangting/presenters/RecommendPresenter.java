package com.tianze.xiangting.presenters;

import com.tianze.xiangting.interfaces.IRecommendPresenter;
import com.tianze.xiangting.interfaces.IRecommendViewCallback;
import com.tianze.xiangting.utils.Constants;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.model.album.Album;
import com.ximalaya.ting.android.opensdk.model.album.GussLikeAlbumList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecommendPresenter implements IRecommendPresenter {

    public static RecommendPresenter recommendPresenter = null;
    List<IRecommendViewCallback> viewCallbacks = new ArrayList<>();

    //私有化构造方法
    private RecommendPresenter() {
    }

    ;

    /**
     * 懒汉式单例
     *
     * @return
     */
    public static RecommendPresenter getInstance() {
        if (recommendPresenter == null) {
            synchronized (RecommendPresenter.class) {
                if (recommendPresenter == null) {
                    recommendPresenter = new RecommendPresenter();
                }
            }
        }
        return recommendPresenter;
    }

    @Override
    public void getGuessLikeAlbumData() {
        for (IRecommendViewCallback viewCallback : viewCallbacks) {
            viewCallback.loading();
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put(DTransferConstants.LIKE_COUNT, Constants.LIKE_COUNT);
        CommonRequest.getGuessLikeAlbum(map, new IDataCallBack<GussLikeAlbumList>() {
            @Override
            public void onSuccess(GussLikeAlbumList gussLikeAlbumList) {
                if (gussLikeAlbumList != null) {
                    List<Album> albumList = gussLikeAlbumList.getAlbumList();
                    if (viewCallbacks != null) {
                        for (IRecommendViewCallback viewCallback : viewCallbacks) {
                            viewCallback.getGuessLikeAlbumData(albumList);
                        }
                    }
                }
            }

            @Override
            public void onError(int i, String s) {
                for (IRecommendViewCallback viewCallback : viewCallbacks) {
                    viewCallback.networkError();
                }
            }
        });
    }

    @Override
    public void pullDown2Fresh() {

    }

    @Override
    public void pullUp2LoadMore() {

    }

    public void registerViewCallback(IRecommendViewCallback callback) {
        if (callback != null && !viewCallbacks.contains(callback)) {
            viewCallbacks.add(callback);
        }
    }

    public void unRegisterViewCallback(IRecommendViewCallback callback) {
        if (callback != null) {
            viewCallbacks.remove(callback);
        }
    }
}
