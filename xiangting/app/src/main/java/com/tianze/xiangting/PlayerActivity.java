package com.tianze.xiangting;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.tianze.xiangting.adapters.PlayerTrackViewPagerAdapter;
import com.tianze.xiangting.interfaces.IPlayerViewCallback;
import com.tianze.xiangting.presenters.PlayerPresenter;
import com.tianze.xiangting.utils.Constants;
import com.tianze.xiangting.utils.ToastUtil;
import com.tianze.xiangting.views.MyPopupWindow;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.opensdk.player.XmPlayerManager;
import com.ximalaya.ting.android.opensdk.player.service.XmPlayListControl;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlayerActivity extends AppCompatActivity implements IPlayerViewCallback, MyPopupWindow.UpdateCurrentPagerLisener {

    @BindView(R.id.track_title)
    TextView trackTitle;
    @BindView(R.id.track_cover_view_pager)
    ViewPager trackCoverViewPager;
    @BindView(R.id.current_duration)
    TextView currentDurationTv;
    @BindView(R.id.track_duration_seek_bar)
    SeekBar trackDurationSeekBar;
    @BindView(R.id.total_duration)
    TextView totalDurationTv;
    @BindView(R.id.play_model)
    ImageView playModeImage;
    @BindView(R.id.play_pre)
    ImageView playPre;
    @BindView(R.id.play_start_or_pause)
    ImageView playStartOrPause;
    @BindView(R.id.play_next)
    ImageView playNext;
    @BindView(R.id.play_list)
    ImageView playList;
    private PlayerPresenter mPlayerPresenter;
    private XmPlayerManager mXmPlayerManager;
    private MyPopupWindow mMyPopupWindow;
    private ValueAnimator mValueAnimator;
    private static Track mCurrSound;
    private static int currPos;
    private static int totalDuration;

    XmPlayListControl.PlayMode[] mPlayModes = new XmPlayListControl.PlayMode[]{
            XmPlayListControl.PlayMode.PLAY_MODEL_LIST_LOOP,
            XmPlayListControl.PlayMode.PLAY_MODEL_SINGLE_LOOP,
            XmPlayListControl.PlayMode.PLAY_MODEL_RANDOM
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        ButterKnife.bind(this);
        init();
        initEvent();
    }

    private void init() {
        mPlayerPresenter = PlayerPresenter.getInstance();
        mPlayerPresenter.registerViewCallback(this);
        mXmPlayerManager = XmPlayerManager.getInstance(this);
        //初始化播放器的播放模式
        initPlayModel();
        //设置播放按钮的状态
        setPlayerStatus();
        //初始化viewpager
        PlayerTrackViewPagerAdapter adapter = new PlayerTrackViewPagerAdapter();
        adapter.setData(mPlayerPresenter.trackList);
        trackCoverViewPager.setAdapter(adapter);

        mMyPopupWindow = new MyPopupWindow();
        mMyPopupWindow.setUpdateCurrentPagerLisener(this);

        //设置track标题和时长
        setTraTitleAndDuration();
    }

    private void initPlayModel() {
        SharedPreferences sharedPreferences = getSharedPreferences("currentPlayModel", MODE_PRIVATE);
        int currentModel = sharedPreferences.getInt("currentModel", 0);
        switch (currentModel) {
            case 0:
                mXmPlayerManager.setPlayMode(mPlayModes[0]);
                break;
            case 1:
                mXmPlayerManager.setPlayMode(mPlayModes[1]);
                break;
            case 2:
                mXmPlayerManager.setPlayMode(mPlayModes[2]);
                break;
        }
    }

    private void setTraTitleAndDuration() {
        if (mXmPlayerManager.getCurrSound() != null) {
            mCurrSound = (Track) mXmPlayerManager.getCurrSound();
        }
        setDataAndStatus(mCurrSound);
        updateTrackDuration(currPos, totalDuration);
        //转到当前track页面
        trackCoverViewPager.setCurrentItem(mXmPlayerManager.getCurrentIndex());
    }

    private void setPlayerStatus() {

        //设置播放器的播放模式
        if (mXmPlayerManager != null) {
            XmPlayListControl.PlayMode currentPlayMode = mXmPlayerManager.getPlayMode();
            setPlayModelImage(currentPlayMode,false);
        }

        if (mXmPlayerManager != null) {
            if (mXmPlayerManager.isPlaying()) {
                playStartOrPause.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_pause, null));
            } else {
                playStartOrPause.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_start, null));
            }
        }
    }

    private void initEvent() {
        //上一首
        playPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayerPresenter.myPlayPre();
            }
        });

        //下一首
        playNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayerPresenter.myPlayNext();
            }
        });

        //播放暂停
        playStartOrPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mXmPlayerManager.isPlaying()) {
                    //将播放器暂停
                    mPlayerPresenter.myPlayPause();
                    //将图片换成开始播放
                    playStartOrPause.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_start, null));
                } else {
                    //开始播放
                    mPlayerPresenter.myPlayStart();
                    //将图标换成开始播放图标
                    playStartOrPause.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_pause, null));
                }
            }
        });

        //切换播放模式
        playModeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayerPresenter.mySwitchPlayModel();
            }
        });

        //点击播放列表
        playList.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mMyPopupWindow.showAtLocation((View) v.getParent(), Gravity.BOTTOM,0,0);
                mMyPopupWindow.refreshData();
                mMyPopupWindow.refreshWindow();
                //弹出播列表面的时候将播放界面颜色调暗
                popupWindowEnterAnimation();
            }
        });

        //播放列表消失的时候恢复播放界面
        mMyPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                popupWindowExitAnimation();
            }
        });

        //拖动进度条
        trackDurationSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mPlayerPresenter.mySetCurrentDuration(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        //切换track封面
        trackCoverViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mPlayerPresenter.myPlayByIndex(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //播放列表弹出的时候动画
    private void popupWindowEnterAnimation(){
        mValueAnimator = ValueAnimator.ofFloat(1.0f,0.6f);
        mValueAnimator.setDuration(Constants.BG_CHANGE_DURATION);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                updateBgAlphaValue((Float) animation.getAnimatedValue());
            }
        });
        mValueAnimator.start();
    }

    //播放列表消失的时候动画
    private void popupWindowExitAnimation(){
        mValueAnimator = ValueAnimator.ofFloat(0.6f, 1.0f);
        mValueAnimator.setDuration(Constants.BG_CHANGE_DURATION);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                updateBgAlphaValue((Float) animation.getAnimatedValue());
            }
        });
        mValueAnimator.start();
    }

    //更新背景的透明度
    private void updateBgAlphaValue(float alphaValue){
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.alpha = alphaValue;
        window.setAttributes(attributes);

    }


    public static void start(Context context) {
        Intent starter = new Intent(context, PlayerActivity.class);
        context.startActivity(starter);
    }

    @Override
    public void setDataAndStatus(Track track) {
        if (track != null) {
            //加载Track的数据，并进行显示
            trackTitle.setText(track.getTrackTitle());
            trackTitle.setSelected(true);
            trackCoverViewPager.setCurrentItem(mXmPlayerManager.getCurrentIndex());
            setPlayerStatus();
        }
    }

    @SuppressLint("SimpleDateFormat")
    @Override
    public void updateTrackDuration(int currPos, int totalDuration) {
        PlayerActivity.currPos = currPos;
        PlayerActivity.totalDuration = totalDuration;
        trackDurationSeekBar.setMax(totalDuration);
        trackDurationSeekBar.setProgress(currPos);
        SimpleDateFormat format;
        String totalDurationStr;
        String currPosStr;
        if (60 * 60 * 1000 < totalDuration) {
            format = new SimpleDateFormat("hh:mm:ss");
            totalDurationStr = format.format(totalDuration);
            currPosStr = format.format(currPos);
        } else {
            format = new SimpleDateFormat("mm:ss");
            totalDurationStr = format.format(totalDuration);
            currPosStr = format.format(currPos);
        }
        this.currentDurationTv.setText(currPosStr);
        this.totalDurationTv.setText(totalDurationStr);
    }

    @Override
    public void resetSeekBarDuration() {
        trackDurationSeekBar.setProgress(0);
    }

    @Override
    public void setPlayModelImage(XmPlayListControl.PlayMode currentPlayMode,boolean clicked) {
        switch (currentPlayMode) {
            case PLAY_MODEL_RANDOM:
                if (clicked){
                    ToastUtil.showToast(PlayerActivity.this,"随机播放");
                }
                playModeImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_random,null));
                break;
            case PLAY_MODEL_SINGLE_LOOP:
                if (clicked) {
                    ToastUtil.showToast(PlayerActivity.this,"单曲循环");
                }
                playModeImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_single_loop,null));
                break;
            case PLAY_MODEL_LIST_LOOP:
                if (clicked) {
                    ToastUtil.showToast(PlayerActivity.this,"循环列表");
                }
                playModeImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_list_loop,null));
                break;
        }
    }

    @Override
    public void switchSound() {

    }

    @Override
    public void switchTrackCover(int position) {
        trackCoverViewPager.setCurrentItem(position);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPlayerPresenter.unregisteredViewCallback(this);
    }

    @Override
    public void updateCurrentPager(int position) {
        trackCoverViewPager.setCurrentItem(position);
    }
}
