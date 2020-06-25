package com.tianze.xiangting.presenters;

import com.tianze.xiangting.interfaces.ISearchPresenter;
import com.tianze.xiangting.interfaces.ISearchViewCallback;
import com.tianze.xiangting.utils.Constants;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.model.album.Album;
import com.ximalaya.ting.android.opensdk.model.album.SearchAlbumList;
import com.ximalaya.ting.android.opensdk.model.word.HotWord;
import com.ximalaya.ting.android.opensdk.model.word.HotWordList;
import com.ximalaya.ting.android.opensdk.model.word.QueryResult;
import com.ximalaya.ting.android.opensdk.model.word.SuggestWords;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchPresenter implements ISearchPresenter {

    private List<ISearchViewCallback> mViewCallbacks = new ArrayList<>();
    private List<String> hotWords = new ArrayList<>();
    private int currentPage = 1;
    List<Album> afterAlbum = new ArrayList<>();
    boolean isLoadMore = false;

    private SearchPresenter() {
    }

    private static SearchPresenter mSearchPresenter = null;

    public static SearchPresenter getInstance() {
        if (mSearchPresenter == null) {
            synchronized (SearchPresenter.class) {
                if (mSearchPresenter == null) {
                    mSearchPresenter = new SearchPresenter();
                }
            }
        }
        return mSearchPresenter;
    }

    @Override
    public void registerViewCallback(ISearchViewCallback iSearchViewCallback) {
        if (iSearchViewCallback != null && !mViewCallbacks.contains(iSearchViewCallback)) {
            mViewCallbacks.add(iSearchViewCallback);
        }
    }

    @Override
    public void unregisteredViewCallback(ISearchViewCallback iSearchViewCallback) {
        mViewCallbacks.remove(iSearchViewCallback);
    }

    @Override
    public void loadHotWorld() {
        Map<String, String> map = new HashMap<String, String>();
        map.put(DTransferConstants.TOP, Constants.HOT_WORLD_COUNT);
        CommonRequest.getHotWords(map, new IDataCallBack<HotWordList>() {
            @Override
            public void onSuccess(HotWordList hotWordList) {
                if (hotWordList != null) {
                    hotWords.clear();
                    for (HotWord hotWord : hotWordList.getHotWordList()) {
                        hotWords.add(hotWord.getSearchword());
                    }
                    //将结果返回到View层
                    for (ISearchViewCallback viewCallback : mViewCallbacks) {
                        viewCallback.loadHotWorldCallback(hotWords);
                    }
                }
            }

            @Override
            public void onError(int i, String s) {

                for (ISearchViewCallback viewCallback : mViewCallbacks) {
                    viewCallback.onNetWorkError();
                }

            }
        });
    }

    @Override
    public void doSearch(String keyWorld) {
        Map<String, String> map = new HashMap<String, String>();
        map.put(DTransferConstants.SEARCH_KEY, keyWorld);
        map.put(DTransferConstants.PAGE_SIZE, Constants.PAGE_SIZE);
        map.put(DTransferConstants.CATEGORY_ID, "0");
        if (this.currentPage <= 0){
            this.currentPage = 1;
        }
        map.put(DTransferConstants.PAGE, currentPage + "");
        CommonRequest.getSearchedAlbums(map, new IDataCallBack<SearchAlbumList>() {
            @Override
            public void onSuccess(SearchAlbumList searchAlbumList) {
                if (searchAlbumList != null) {
                    //判断是否为加载更多的模式，不是的话将afterAlbum列表清空
                    if (!isLoadMore) {
                        afterAlbum.clear();
                    }

                    List<Album> backAlbums = searchAlbumList.getAlbums();
                    if (backAlbums.size() > 0) {
                        afterAlbum.addAll(backAlbums);
                        for (ISearchViewCallback viewCallback : mViewCallbacks) {
                            viewCallback.searchResultCallback(afterAlbum);
                        }
                    }
                }

                //加载更多之后，设置搜索为非加载更多模式！
                isLoadMore = false;
            }

            @Override
            public void onError(int i, String s) {
                currentPage--;
                for (ISearchViewCallback viewCallback : mViewCallbacks) {
                    viewCallback.onNetWorkError();
                }
            }
        });

    }

    @Override
    public void getSuggestWords(String keyWord) {
        Map<String, String> map = new HashMap<String, String>();
        map.put(DTransferConstants.SEARCH_KEY, keyWord);
        CommonRequest.getSuggestWord(map, new IDataCallBack<SuggestWords>() {
            @Override
            public void onSuccess(SuggestWords suggestWords) {
                if (suggestWords != null) {
                    List<QueryResult> keyWordList = suggestWords.getKeyWordList();
                    if (keyWordList != null) {
                        for (ISearchViewCallback viewCallback : mViewCallbacks) {
                            viewCallback.suggestResultCallback(keyWordList);
                        }
                    }
                }
            }

            @Override
            public void onError(int i, String s) {
            }
        });
    }


    @Override
    public void loadMore(String keyWord) {
        //设置搜索模式为加载更多！
        this.isLoadMore = true;
        this.currentPage++;
        doSearch(keyWord);

    }
}
