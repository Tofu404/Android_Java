package com.tianze.xiangting.base;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.tianze.xiangting.utils.MyLogUtil;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.player.XmPlayerManager;
import com.ximalaya.ting.android.opensdk.player.service.XmPlayerConfig;

public class BaseApplication extends Application {

    private static Handler myHandler = null;

    public static Handler getHandle() {
        if (myHandler != null) {
            return myHandler;
        }
        return null;
    }

    @SuppressLint("StaticFieldLeak")
    public static Context context = null;


    /**
     * application创建的时候调用
     */
    @Override
    public void onCreate() {
        super.onCreate();
        CommonRequest mXimalaya = CommonRequest.getInstanse();
        mXimalaya.setAppkey("bd05fc547e72c6b078d51688b1ea900c");
        mXimalaya.setPackid("com.tianze.xiangting");
        mXimalaya.init(this ,"27748302de9c1d1121279ffb1dcd8306");

        MyLogUtil.initMyLog(getPackageName(),false);

        myHandler = new Handler();

        context = getApplicationContext();

        //初始化播放器
        XmPlayerManager.getInstance(this).init();
        //配置播放器属性
        XmPlayerConfig xmPlayerConfig = XmPlayerConfig.getInstance(this);
        //设置播放器默认使用高品质声音播放
        xmPlayerConfig.setUseTrackHighBitrate(true);
        //设置声音焦点
        xmPlayerConfig.setSDKHandleAudioFocus(true);
        xmPlayerConfig.setSDKHandlePhoneComeAudioFocus(true);
    }


    /**
     * application注销的时候调用
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        //释放播放器的资源
        XmPlayerManager.release();
    }
}
