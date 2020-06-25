package com.tianze.xiangting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.tianze.xiangting.adapters.AlbumLIstAdapter;
import com.tianze.xiangting.adapters.SuggestWordAdapter;
import com.tianze.xiangting.interfaces.ISearchViewCallback;
import com.tianze.xiangting.presenters.AlbumDetailPresenter;
import com.tianze.xiangting.presenters.SearchPresenter;
import com.tianze.xiangting.utils.ToastUtil;
import com.tianze.xiangting.views.FlowTextLayout;
import com.tianze.xiangting.views.UILoader;
import com.ximalaya.ting.android.opensdk.model.album.Album;
import com.ximalaya.ting.android.opensdk.model.word.QueryResult;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity implements ISearchViewCallback, AlbumLIstAdapter.OnRecommendItemClickListener, SuggestWordAdapter.OnSuggestItemClickListener {

    private final static int SHOW_SUGGEST_WORD = 0;
    private final static int SHOW_ALBUM_LIST = 1;
    private final static int SHOW_HOT_WORD = 2;

    @BindView(R.id.key_word_et)
    EditText keyWordEt;
    @BindView(R.id.search_result_container)
    FrameLayout searchResultContainer;
    @BindView(R.id.search_btn)
    TextView searchBtn;

    private UILoader mLoaderView;
    private RecyclerView mSuggestWordList;
    private TwinklingRefreshLayout mAlbumListRefreshLayout;
    private RecyclerView mSearchAlbumList;
    private FlowTextLayout mHotWordContainer;

    private SearchPresenter mSearchPresenter;
    private InputMethodManager mInputMethodManager;
    private AlbumLIstAdapter mAlbumLIstAdapter;
    private SuggestWordAdapter mSuggestWordAdapter;
    private boolean showSuggestWord = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        init();
        initEvent();
    }

    private void init() {

        //初始化UILoader
        if (mLoaderView == null) {
            mLoaderView = new UILoader(SearchActivity.this) {
                @Override
                public View getSuccessView(ViewGroup container) {
                    return createSuccessView(container);
                }
            };

            //将成功页面添加进去
            addLoaderView(mLoaderView);
        }

        mSearchPresenter = SearchPresenter.getInstance();
        mSearchPresenter.registerViewCallback(this);
        mSearchPresenter.loadHotWorld();

        //进入搜索界面的时候，弹出键盘
        mInputMethodManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        keyWordEt.postDelayed(() -> {
            keyWordEt.requestFocus();
            mInputMethodManager.showSoftInput(keyWordEt, InputMethodManager.SHOW_IMPLICIT);
        }, 400);

    }

    private void addLoaderView(UILoader loaderView) {
        if (loaderView.getParent() instanceof ViewGroup) {
            ((ViewGroup) loaderView.getParent()).removeView(loaderView);
        }

        if (searchResultContainer != null) {
            searchResultContainer.addView(mLoaderView);
        }
    }

    private void showView(int viewNumber) {
        switch (viewNumber) {
            case SHOW_SUGGEST_WORD:
                //显示推荐关键字
                mSuggestWordList.setVisibility(View.VISIBLE);
                mAlbumListRefreshLayout.setVisibility(View.GONE);
                mHotWordContainer.setVisibility(View.GONE);
                break;
            case SHOW_ALBUM_LIST:
                //显示搜索专辑列表
                mSuggestWordList.setVisibility(View.GONE);
                mAlbumListRefreshLayout.setVisibility(View.VISIBLE);
                mHotWordContainer.setVisibility(View.GONE);
                break;
            case SHOW_HOT_WORD:
                //显示热词
                mSuggestWordList.setVisibility(View.GONE);
                mAlbumListRefreshLayout.setVisibility(View.GONE);
                mHotWordContainer.setVisibility(View.VISIBLE);
                break;
        }
    }

    private View createSuccessView(ViewGroup container) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.layout_search_success_view, container, false);
        //初始化推荐词控件
        mSuggestWordList = view.findViewById(R.id.suggest_word_list);
        mSuggestWordList.setLayoutManager(new LinearLayoutManager(this));
        mSuggestWordAdapter = new SuggestWordAdapter();
        mSuggestWordAdapter.setOnSuggestItemClickListener(this);
        mSuggestWordList.setAdapter(mSuggestWordAdapter);

        //获取专辑控件
        mAlbumListRefreshLayout = view.findViewById(R.id.album_list_refresh);
        mAlbumListRefreshLayout.setEnableRefresh(false);

        mSearchAlbumList = view.findViewById(R.id.search_album_list);
        mSearchAlbumList.setLayoutManager(new LinearLayoutManager(this));
        mAlbumLIstAdapter = new AlbumLIstAdapter();
        mAlbumLIstAdapter.setOnRecommendItemClickListener(this);
        mSearchAlbumList.setAdapter(mAlbumLIstAdapter);

        //获取热词控件
        mHotWordContainer = view.findViewById(R.id.hot_word_container);
        return view;
    }

    private void initEvent() {

        //点击网络错误页面的时候
        mLoaderView.setOnNetworkViewClickListener(new UILoader.OnNetworkErrorViewClickListener() {
            @Override
            public void onNetworkViewClick() {
                mLoaderView.setCurrentState(UILoader.UIState.LOADING);
                String keyWork = keyWordEt.getText().toString();
                if (keyWork.length() > 0) {
                    mSearchPresenter.doSearch(keyWork);
                } else {
                    mSearchPresenter.loadHotWorld();
                }
            }
        });

        mAlbumListRefreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                //实现加载更多的内容
                String keyWord = keyWordEt.getText().toString();
                mSearchPresenter.loadMore(keyWord);
                refreshLayout.finishLoadmore();
            }
        });

        keyWordEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //是否有需要显示推荐词
                if (showSuggestWord) {
                    //加载推荐关键词
                    if (s.length() == 0) {
                        showView(SHOW_HOT_WORD);
                    } else {
                        mSearchPresenter.getSuggestWords(s.toString());
                        showView(SHOW_SUGGEST_WORD);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //点击搜索按钮进行搜索
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyWord = keyWordEt.getText().toString();
                if (TextUtils.isEmpty(keyWord)) {
                    ToastUtil.showToast(SearchActivity.this, "请输入关键字！");
                    return;
                }
                //执行搜索操作
                mLoaderView.setCurrentState(UILoader.UIState.LOADING);
                mSearchPresenter.doSearch(keyWord);
            }
        });

        //点击热词
        mHotWordContainer.setClickListener(new FlowTextLayout.ItemClickListener() {
            @Override
            public void onItemClick(String text) {
                showSuggestWord = false;
                keyWordEt.setText(text);
                keyWordEt.setSelection(text.length());
                //执行搜索操作
                mLoaderView.setCurrentState(UILoader.UIState.LOADING);
                mSearchPresenter.doSearch(text);
                showSuggestWord = true;
            }
        });

    }

    public static void start(Context context) {
        Intent starter = new Intent(context, SearchActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSearchPresenter.unregisteredViewCallback(this);
    }

    @Override
    public void loadHotWorldCallback(List<String> hotWords) {
        if (hotWords != null) {
            if (mLoaderView != null) {
                mLoaderView.setCurrentState(UILoader.UIState.SUCCESS);
                showView(SHOW_HOT_WORD);
                mHotWordContainer.setTextContents(hotWords);
            }
        }
    }

    @Override
    public void searchResultCallback(List<Album> albums) {
        if (albums != null && albums.size() > 0) {
            if (mLoaderView != null) {
                mLoaderView.setCurrentState(UILoader.UIState.SUCCESS);
                showView(SHOW_ALBUM_LIST);
                setData2AlbumList(albums);
            }
        } else {
            mLoaderView.setCurrentState(UILoader.UIState.EMPTY);
            showView(SHOW_ALBUM_LIST);
        }
    }

    @Override
    public void suggestResultCallback(List<QueryResult> keyWordList) {
        if (keyWordList != null && keyWordList.size() > 0) {
            mLoaderView.setCurrentState(UILoader.UIState.SUCCESS);
            showView(SHOW_SUGGEST_WORD);
            mSuggestWordAdapter.setSuggestData(keyWordList);
        }else {
            showView(SHOW_HOT_WORD);
        }
    }

    @Override
    public void onNetWorkError() {
        mLoaderView.setCurrentState(UILoader.UIState.NETWORK_ERRPR);
    }

    private void setData2AlbumList(List<Album> albums) {
        mAlbumLIstAdapter.setData(albums);
    }

    @Override
    public void onAlbumItemClick(Album album) {
        //跳转到专辑详情
        AlbumDetailPresenter.getInstance().setTargetAlbum(album);
        AlbumDetailActivity.start(SearchActivity.this);
    }

    @Override
    public void onSuggestItemClick(String text) {
        //点击进行搜索操作
        //当keyWordEt.setText(text)的时候触发change监听，这时候我们不需要显示推荐词
        //所以showSuggestWord 为 false;
        showSuggestWord = false;
        keyWordEt.setText(text);
        keyWordEt.setSelection(text.length());
        //执行搜索操作
        mLoaderView.setCurrentState(UILoader.UIState.LOADING);
        mSearchPresenter.doSearch(text);
        showSuggestWord = true;
    }
}
