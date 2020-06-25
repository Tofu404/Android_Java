package com.tianze.xiangting;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.tianze.xiangting.adapters.TrackListAdapter;
import com.tianze.xiangting.base.BaseApplication;
import com.tianze.xiangting.data.Daos.SubDao;
import com.tianze.xiangting.data.interfaces.ISubViewCallback;
import com.tianze.xiangting.interfaces.IAlbumDetailViewCallback;
import com.tianze.xiangting.presenters.AlbumDetailPresenter;
import com.tianze.xiangting.presenters.PlayerPresenter;
import com.tianze.xiangting.utils.ImageBlur;
import com.tianze.xiangting.utils.ToastUtil;
import com.tianze.xiangting.views.RoundRectImageView;
import com.tianze.xiangting.views.UILoader;
import com.ximalaya.ting.android.opensdk.model.album.Album;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.opensdk.player.XmPlayerManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlbumDetailActivity extends AppCompatActivity implements
        IAlbumDetailViewCallback,
        UILoader.OnNetworkErrorViewClickListener,
        TrackListAdapter.OnTrackListItemClickListener, ISubViewCallback {


    @BindView(R.id.album_cover_bg)
    ImageView albumCoverBg;
    @BindView(R.id.subscription)
    TextView subscription;
    @BindView(R.id.album_cover)
    RoundRectImageView albumCover;
    @BindView(R.id.album_title)
    TextView albumTitle;
    @BindView(R.id.author_iv)
    ImageView authorIv;
    @BindView(R.id.album_author)
    TextView albumAuthor;
    @BindView(R.id.play)
    ImageView play;
    @BindView(R.id.container)
    FrameLayout container;
    @BindView(R.id.track_title)
    TextView trackTitle;

    private AlbumDetailPresenter albumDetailPresenter;
    private UILoader myUIloader;
    private Album targetAlbum;
    private TrackListAdapter adapter;
    private RecyclerView mTrackListRv;
    private TwinklingRefreshLayout mRefreshLayout;
    private List<Track> trackList = new ArrayList<>();
    private XmPlayerManager mXmPlayerManager;
    private PlayerPresenter mPlayerPresenter;
    private SubDao mSubDao;
    private List<Album> subAlbums;
    private boolean isSub = false;

    public static void start(Context context) {
        Intent starter = new Intent(context, AlbumDetailActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_detail);
        //设置View延申到状态栏
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        ButterKnife.bind(this);
        //获得播放器实例
        mXmPlayerManager = XmPlayerManager.getInstance(this);
        //拿到播放器逻辑层
        mPlayerPresenter = PlayerPresenter.getInstance();
        //初始化UILoader
        initUILoader();
        initPresenter();
        initDao();
        initView();
        initEvent();
    }

    private void initDao() {
        //初始化数据库层
        mSubDao = SubDao.getInstance();

        mSubDao.registerViewCallback(this);

        mSubDao.queryAlbum();
    }

    private void initEvent() {

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击之后进行播放或暂停
                if (mXmPlayerManager.getPlayList() == null || mXmPlayerManager.getPlayList().size() == 0) {
                    handleNoPlayList();
                } else {
                    if (mXmPlayerManager.isPlaying()) {
                        mPlayerPresenter.myPlayPause();
                        play.setImageDrawable(getResources().getDrawable(R.drawable.ic_detail_play, null));
                        trackTitle.setText(getString(R.string.click_to_play));
                        trackTitle.setTextColor(getResources().getColor(R.color.colorGray));
                    } else {
                        mPlayerPresenter.myPlayStart();
                        play.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_pause, null));
                        trackTitle.setSelected(true);
                        trackTitle.setText(((Track) mXmPlayerManager.getCurrSound()).getTrackTitle());
                        trackTitle.setTextColor(getResources().getColor(R.color.colorMain));
                    }
                }
            }
        });

        subscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击的时候订阅或取消订阅
                if (targetAlbum != null) {
                    if (isSub) {
                        mSubDao.deleteAlbum(targetAlbum.getId());
                    } else {
                        mSubDao.addAlbum(targetAlbum);
                    }
                }
            }
        });
    }

    private void handleNoPlayList() {
        mXmPlayerManager.setPlayList(trackList, 0);
        mPlayerPresenter.myPlayStart();
        play.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_pause, null));
        trackTitle.setSelected(true);
        trackTitle.setText(((Track) mXmPlayerManager.getCurrSound()).getTrackTitle());
        trackTitle.setTextColor(getResources().getColor(R.color.colorMain));
    }


    private void initUILoader() {
        myUIloader = new UILoader(AlbumDetailActivity.this) {
            @Override
            public View getSuccessView(ViewGroup container) {
                return createSuccessView(container);
            }
        };

        //为网络错误页面添加监听器
        myUIloader.setOnNetworkViewClickListener(this);
    }

    private void initPresenter() {
        albumDetailPresenter = AlbumDetailPresenter.getInstance();
        albumDetailPresenter.registerViewCallback(this);
        albumDetailPresenter.getAlbumTrackList();
        targetAlbum = albumDetailPresenter.getTargetAlbum();


    }

    @Override
    public void onAlbumTrackLoaded(List<Track> trackList) {
        this.trackList.clear();
        if (trackList != null && trackList.size() > 0) {
            this.trackList.addAll(trackList);
        }
        myUIloader.setCurrentState(UILoader.UIState.SUCCESS);
        initSuccessView();
    }

    private void initSuccessView() {

        if (myUIloader != null) {
            mTrackListRv = myUIloader.findViewById(R.id.track_list_rv);
            mRefreshLayout = myUIloader.findViewById(R.id.refresh_layout);
            mRefreshLayout.setEnableRefresh(false);
            mTrackListRv.setLayoutManager(new LinearLayoutManager(AlbumDetailActivity.this));
            adapter = new TrackListAdapter();
            adapter.setOnTrackListItemClickListener(this);
            adapter.setTracksData(trackList);
            mTrackListRv.setAdapter(adapter);

            //为刷新操作添加监听器
            mRefreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
                @Override
                public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                    assert BaseApplication.getHandle() != null;
                    BaseApplication.getHandle().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.showToast(BaseApplication.context, "刷新成功！");
                            refreshLayout.finishRefreshing();
                        }
                    }, 2000);
                }

                @Override
                public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                    assert BaseApplication.getHandle() != null;
                    BaseApplication.getHandle().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            albumDetailPresenter.pullUpLoadMore();
                            refreshLayout.finishLoadmore();
                        }
                    }, 1000);
                }
            });
        }
    }


    private void initView() {
        //初始化专辑信息
        if (targetAlbum != null) {
            if (!TextUtils.isEmpty(targetAlbum.getCoverUrlLarge())) {
                Picasso.get().load(targetAlbum.getCoverUrlLarge()).into(albumCoverBg, new Callback() {
                    @Override
                    public void onSuccess() {
                        Drawable drawable = albumCoverBg.getDrawable();
                        //返回的图片不为空的时候将图片设置成毛玻璃的状态
                        if (drawable != null) {
                            ImageBlur.makeBlur(albumCoverBg, AlbumDetailActivity.this);
                        }
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
            }
            if (!TextUtils.isEmpty(targetAlbum.getCoverUrlLarge())) {
                Picasso.get().load(targetAlbum.getCoverUrlLarge()).into(albumCover);
            }
            albumTitle.setText(targetAlbum.getAlbumTitle());
            albumAuthor.setText(targetAlbum.getAnnouncer().getNickname());
        }

        //控制播放按钮状态
        initPlayBtnStatus();

        //通过获取到订阅列表判断该专辑是否已经已经订阅


        for (Album subAlbum : subAlbums) {

            if (subAlbum == null) {
                break;
            }

            if (subAlbum.getId() == targetAlbum.getId()) {
                subscription.setText("取消订阅");
                isSub = true;
                break;
            }
        }
    }

    private void initPlayBtnStatus() {
        if (mXmPlayerManager.isPlaying()) {
            play.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_pause, null));
            trackTitle.setText(((Track) mXmPlayerManager.getCurrSound()).getTrackTitle());
            trackTitle.setSelected(true);
            trackTitle.setTextColor(getResources().getColor(R.color.colorMain));
        }
    }

    private View createSuccessView(ViewGroup container) {
        return LayoutInflater.from(AlbumDetailActivity.this).inflate(R.layout.layout_track_list, container, false);

    }

    @Override
    public void pullUpLoadMore(List<Track> trackList) {
        if (trackList != null) {
            int i = trackList.size() - this.trackList.size();
            if (i > 0) {
                ToastUtil.showToast(AlbumDetailActivity.this, "更新了" + i + "条数据！");
                this.trackList.clear();
                this.trackList.addAll(trackList);
            } else {
                ToastUtil.showToast(AlbumDetailActivity.this, "没有数据了！");
            }
        }

        if (adapter != null) {
            adapter.setTracksData(this.trackList);
        }
    }

    @Override
    public void onLoading() {
        if (myUIloader != null) {
            myUIloader.setCurrentState(UILoader.UIState.LOADING);
            container.removeAllViews();
            container.addView(myUIloader);
        }
    }

    @Override
    public void onNetworkError() {
        if (myUIloader != null) {
            myUIloader.setCurrentState(UILoader.UIState.NETWORK_ERRPR);
            container.removeAllViews();
            container.addView(myUIloader);
        }
    }

    @Override
    public void noReturnEmptyData() {
        if (myUIloader != null) {
            myUIloader.setCurrentState(UILoader.UIState.EMPTY);
            container.removeAllViews();
            container.addView(myUIloader);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        albumDetailPresenter.unregisteredViewCallback(this);
        mSubDao.unregisteredViewCallback(this);
    }

    @Override
    public void onNetworkViewClick() {
        //点击网络错误页面重新加载trackList
        albumDetailPresenter.getAlbumTrackList();
    }

    @Override
    public void onTrackListItemClick(int position) {
        mPlayerPresenter.mySetTrackList(this.trackList, position);
        PlayerActivity.start(AlbumDetailActivity.this);
    }

    @Override
    public void onTrackListItemLongClick(int position) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        initPlayBtnStatus();
    }


    //数据库相关回调
    @Override
    public void addResult(boolean addResult) {
        Toast toast = Toast.makeText(AlbumDetailActivity.this, null, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 0);
        if (addResult) {
            subscription.setText("取消订阅");
            toast.setText("订阅成功！");
            isSub = true;
        } else {
            toast.setText("订阅失败！");
        }
        toast.show();
    }

    @Override
    public void deleteResult(boolean deleteSuccess) {
        Toast toast = Toast.makeText(AlbumDetailActivity.this, null, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 0);
        if (deleteSuccess) {
            subscription.setText("+订阅");
            toast.setText("取消订阅成功");
            isSub = false;
        } else {
            toast.setText("取消订阅失败");
        }
        toast.show();
    }

    @Override
    public void queryResult(List<Album> subAlbums) {
        if (subAlbums.size() > 0) {
            this.subAlbums = subAlbums;
        } else {
            this.subAlbums = new ArrayList<>();
        }
    }
}
