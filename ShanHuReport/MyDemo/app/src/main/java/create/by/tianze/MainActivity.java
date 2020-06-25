package create.by.tianze;

import android.Manifest;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.pw.WinLib;
import com.pw.us.AdInfo;
import com.pw.us.IAdListener;
import com.pw.us.IRewardAdListener;
import com.pw.us.Setting;
import com.pw.view.NativeAdContainer;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "winss";
    //private static final String NATIVE = "17972_04384";
    //private static final String VIDEO = "17972_39931";
    private static final String NATIVE =BuildConfig.NATIVEADID;
    private static final String VIDEO =BuildConfig.REWARDADID;

    private ImageView mAppIcon;
    private TextView mTitle;
    private TextView mDesc;
    private Button mShowNativeAd;
    private Button mShowRewardAd;
    private NativeAdContainer mContainer;
    private LinearLayout mAdLayout;
    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setListener();

        // 动态申请权限
        Utils.requsetPermission(this, new String[]{Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.REQUEST_INSTALL_PACKAGES, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE});
    }

    private void initView() {
        mAppIcon = findViewById(R.id.app_icon);
        mTitle = findViewById(R.id.title);
        mDesc = findViewById(R.id.desc);
        mContainer = findViewById(R.id.ad_container);
        mAdLayout = findViewById(R.id.ad_layout);
        mShowRewardAd = findViewById(R.id.show_reward_ad);
        mShowNativeAd = findViewById(R.id.show_native_ad);
        ((TextView) findViewById(R.id.pack_name)).setText(this.getPackageName());
    }

    private void setListener() {
        mShowNativeAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNativeAd();
            }
        });

        mShowRewardAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRewardAd();
            }
        });
    }

    private void showRewardAd() {
        Setting setting = new Setting(MainActivity.this, Setting.TYPE_REWARDED, VIDEO, new IRewardAdListener() {
            @Override
            public void onError(String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoaded(final Setting setting) {
                Toast.makeText(MainActivity.this, "加载成功！", Toast.LENGTH_SHORT).show();
                WinLib.show(setting);
            }

            @Override
            public void onShowed() {

            }

            @Override
            public void onClosed() {

            }

            @Override
            public void onVideoComplete() {

            }

            @Override
            public void onDownloadStarted(String s) {

            }

            @Override
            public void onDownloadFinished(String s, String s1) {

            }

            @Override
            public void onInstalled(String s, String s1) {

            }
        });
        WinLib.load(setting);
    }

    private void showNativeAd() {
        Setting setting = new Setting(MainActivity.this, Setting.TYPE_NATIVE, NATIVE, new IAdListener() {

            @Override
            public void onError(String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoaded(AdInfo adInfo, Setting setting) {
                if (adInfo != null) {
                    Glide.with(mAppIcon.getContext()).load(adInfo.getIconUrl()).into(mAppIcon);
                    mTitle.setText(adInfo.getTitle());
                    mDesc.setText(adInfo.getDesc());
                    WinLib.regView(setting);
                }
            }

            @Override
            public void onShowed() {

            }

            @Override
            public void onClicked() {

            }

            @Override
            public void onDownloadStarted() {

            }

            @Override
            public void onDownloadFinished(String s) {
                mMediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.tip_sound);
                mMediaPlayer.start();
            }

            @Override
            public void onInstalled() {

            }
        });

        setting.setAdViewContainer(mContainer);
        setting.setAdViewGroup(mAdLayout);
        WinLib.load(setting);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMediaPlayer.release();
        mMediaPlayer = null;
    }
}