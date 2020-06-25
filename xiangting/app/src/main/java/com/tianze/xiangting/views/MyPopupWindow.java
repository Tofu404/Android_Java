package com.tianze.xiangting.views;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tianze.xiangting.R;
import com.tianze.xiangting.adapters.PlayListAdapter;
import com.tianze.xiangting.base.BaseApplication;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.opensdk.player.XmPlayerManager;
import com.ximalaya.ting.android.opensdk.player.service.XmPlayListControl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyPopupWindow extends PopupWindow implements PlayListAdapter.OnItemClickListener {

    private final View mMPlayerTrackList;
    @BindView(R.id.play_model)
    ImageView playModelIv;
    @BindView(R.id.play_model_tips)
    TextView playModelTips;
    @BindView(R.id.order_or_reverse)
    TextView orderOrReverse;
    @BindView(R.id.player_track_list)
    RecyclerView playerTrackList;
    @BindView(R.id.close_popup_window)
    TextView closePopupWindow;
    private final XmPlayerManager mXmPlayerManager;
    private PlayListAdapter mAdapter;
    private List<Track> mPlayList;
    private UpdateCurrentPagerLisener updateCurrentPagerLisener = null;

    XmPlayListControl.PlayMode[] mPlayModes = new XmPlayListControl.PlayMode[]{
            XmPlayListControl.PlayMode.PLAY_MODEL_LIST_LOOP,
            XmPlayListControl.PlayMode.PLAY_MODEL_SINGLE_LOOP,
            XmPlayListControl.PlayMode.PLAY_MODEL_RANDOM
    };

    public MyPopupWindow() {
        //设置窗口的宽高
        super(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //这里要注意：设置setOutsideTouchable之前，先要设置：setBackgroundDrawable,
        //否则点击外部无法关闭pop.
        setBackgroundDrawable(new ColorDrawable(BaseApplication.context.getResources().getColor(R.color.colorBG)));
        setOutsideTouchable(true);
        setTouchable(true);
        setFocusable(true);
        //将要显示的视图加载进来！
        mMPlayerTrackList = LayoutInflater.from(BaseApplication.context).inflate(R.layout.layout_player_track_list, null);
        ButterKnife.bind(this,mMPlayerTrackList);
        setContentView(mMPlayerTrackList);
        setAnimationStyle(R.style.pop_animation);

        //拿到播放器实例
        mXmPlayerManager = XmPlayerManager.getInstance(BaseApplication.context);
        initView();
        initEvent();
    }

    public void refreshWindow() {
        refreshPlayModel();
    }

    private void initView() {
        playerTrackList.setLayoutManager(new LinearLayoutManager(BaseApplication.context));
        mAdapter = new PlayListAdapter();
        mPlayList = mXmPlayerManager.getPlayList();
        mAdapter.setTrackData(mPlayList);
        mAdapter.setOnItemClickListener(this);
        playerTrackList.setAdapter(mAdapter);
        refreshPlayModel();
    }

    private void refreshPlayModel() {
        SharedPreferences sharedPreferences = BaseApplication.context.getSharedPreferences("currentPlayModel", Context.MODE_PRIVATE);
        int currentModle = sharedPreferences.getInt("currentModel", 0);
        switch (mPlayModes[currentModle]) {
            case PLAY_MODEL_LIST_LOOP:
                playModelIv.setImageDrawable(BaseApplication.context.getResources().getDrawable(R.drawable.ic_play_list_loop,null));
                playModelTips.setText("循环列表");
                break;
            case PLAY_MODEL_SINGLE_LOOP:
                playModelIv.setImageDrawable(BaseApplication.context.getResources().getDrawable(R.drawable.ic_play_single_loop,null));
                playModelTips.setText("单曲循环");
                break;
            case PLAY_MODEL_RANDOM:
                playModelIv.setImageDrawable(BaseApplication.context.getResources().getDrawable(R.drawable.ic_play_random,null));
                playModelTips.setText("随机播放");
                break;
        }
    }


    private void initEvent() {

        //点击关闭按钮
        closePopupWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


    }

    @Override
    public void onItemClick(int position) {
        mXmPlayerManager.play(position);
        mAdapter.notifyDataSetChanged();
        if (updateCurrentPagerLisener!=null){
            updateCurrentPagerLisener.updateCurrentPager(position);
        }
    }

    public void refreshData() {
        if (mAdapter!=null){
            mAdapter.notifyDataSetChanged();
        }
    }

    public interface UpdateCurrentPagerLisener{
        void updateCurrentPager(int position);
    }

    public void setUpdateCurrentPagerLisener(UpdateCurrentPagerLisener lisener) {
        this.updateCurrentPagerLisener = lisener;
    }
}
