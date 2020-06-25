package com.tianze.xiangting;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.tianze.xiangting.adapters.IndicatorAdapter;
import com.tianze.xiangting.adapters.ViewPagerAdapter;
import com.tianze.xiangting.base.BaseApplication;
import com.tianze.xiangting.interfaces.IPlayerViewCallback;
import com.tianze.xiangting.presenters.PlayerPresenter;
import com.ximalaya.ting.android.opensdk.model.album.Announcer;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.opensdk.player.XmPlayerManager;
import com.ximalaya.ting.android.opensdk.player.service.XmPlayListControl;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


@RequiresApi(api = Build.VERSION_CODES.M)
public class MainActivity extends AppCompatActivity implements IPlayerViewCallback {

    private static final String TAG = "MainActivity";

    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.player_bar)
    RelativeLayout playerBar;
    @BindView(R.id.track_cover)
    CircleImageView trackCover;
    @BindView(R.id.track_title)
    TextView trackTitle;
    @BindView(R.id.album_author)
    TextView albumAuthor;
    @BindView(R.id.track_tips)
    LinearLayout trackTips;
    @BindView(R.id.play_start_or_pause)
    ImageView playStartOrPause;
    @BindView(R.id.search_btn)
    ImageView searchBtn;
    @BindView(R.id.me_btn)
    ImageView meBtn;
    private XmPlayerManager mXmPlayerManager;
    private PlayerPresenter mPlayerPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        initPresenter();
        initEvent();
        initView();
    }

    private void initPresenter() {
        mPlayerPresenter = PlayerPresenter.getInstance();
        mPlayerPresenter.registerViewCallback(this);
    }

    private void initEvent() {
        meBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MeActivity.start(MainActivity.this);
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchActivity.start(MainActivity.this);
            }
        });

        playStartOrPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mXmPlayerManager.isPlaying()) {
                    mXmPlayerManager.pause();
                    playStartOrPause.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_start, null));
                } else {
                    mXmPlayerManager.play();
                    playStartOrPause.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_pause, null));
                }
            }
        });
    }

    private void init() {
        mXmPlayerManager = XmPlayerManager.getInstance(this);
    }

    private void initView() {
        //初始化title名称
        List<String> mTitleDataList = new ArrayList<>();
        mTitleDataList.add("推荐");
        mTitleDataList.add("订阅");
        mTitleDataList.add("历史");
        MagicIndicator magicIndicator = findViewById(R.id.magic_indicator);
        CommonNavigator commonNavigator = new CommonNavigator(this);
        IndicatorAdapter indicatorAdapter = new IndicatorAdapter();
        indicatorAdapter.setTitleData(mTitleDataList);

        //设置item点击事件
        indicatorAdapter.setOnsimplePagerTitleViewClickListener(index -> viewPager.setCurrentItem(index));
        commonNavigator.setAdapter(indicatorAdapter);
        commonNavigator.setAdjustMode(true);
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, viewPager);

        //为viewpager设置适配器
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapter);

        //点击playerBar跳转到播放界面
        playerBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerActivity.start(MainActivity.this);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        List<Track> playList = mXmPlayerManager.getPlayList();
        if (playList != null && playList.size() > 0) {
            playerBar.setVisibility(View.VISIBLE);
        } else {
            playerBar.setVisibility(View.GONE);
        }

        initPlayBarStatus();
    }

    private void initPlayBarStatus() {

        Track currTrack = (Track) mXmPlayerManager.getCurrSound();
        if (currTrack != null) {
            trackTitle.setText(currTrack.getTrackTitle());
            trackTitle.setSelected(true);
            Glide.with(trackCover.getContext()).load(currTrack.getCoverUrlLarge()).into(trackCover);
            Announcer announcer = currTrack.getAnnouncer();
            albumAuthor.setText(announcer.getNickname());
        }

        if (!mXmPlayerManager.isPlaying()) {
            playStartOrPause.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_start, null));

            //过一段时间之后再看是否为暂停状态，之后再更新一下
            assert BaseApplication.getHandle() != null;
            BaseApplication.getHandle().postDelayed(new Runnable() {
                @Override
                public void run() {
                    playStartOrPause
                            .setImageDrawable(getResources()
                                    .getDrawable(mXmPlayerManager.isPlaying() ?
                                            R.drawable.ic_play_pause : R.drawable.ic_play_start, null));
                }
            }, 300);
        } else {
            playStartOrPause.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_pause, null));
        }
    }

    @Override
    public void setDataAndStatus(Track track) {

    }

    @Override
    public void updateTrackDuration(int currPos, int duration) {

    }

    @Override
    public void resetSeekBarDuration() {

    }

    @Override
    public void setPlayModelImage(XmPlayListControl.PlayMode playMode, boolean clicked) {

    }


    @Override
    public void switchSound() {
        initPlayBarStatus();
    }

    @Override
    public void switchTrackCover(int position) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPlayerPresenter.unregisteredViewCallback(this);
    }
}
