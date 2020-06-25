package com.example.musicservice;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class MusicService extends Service {

    static Boolean isplay;//定义音乐的播放的状态
    MediaPlayer musicplay;//定义音乐播放对象

    public MusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {

        super.onCreate();
        //创建音乐播放对象，并指定播放的音乐文件
        musicplay = MediaPlayer.create(MusicService.this,R.raw.nihao);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //开始播放音乐，并设置isplay的状态
        if (!musicplay.isPlaying()){
            musicplay.start();
            isplay = musicplay.isPlaying();
            Log.i("nihao","成功播放！");
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {//停止播放音乐
        musicplay.stop();
        isplay = musicplay.isPlaying();
        musicplay.release();
        Log.i("nihao","成功停止！！");
        super.onDestroy();
    }
}
