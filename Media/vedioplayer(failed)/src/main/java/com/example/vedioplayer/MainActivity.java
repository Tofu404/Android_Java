package com.example.vedioplayer;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        VideoView video = findViewById(R.id.vedio);
        /*************加载要播放的视频*************/
        File file = new File("android.resource://" + getPackageName() + "raw/hello.mp4");
        if (file.exists()){
            video.setVideoPath(file.getAbsolutePath());
        }else{
            Toast.makeText(MainActivity.this,"要播放的视频不存在！",Toast.LENGTH_SHORT).show();
        }
        /***************控制视频的播放*****************/
        MediaController mediaController = new MediaController(MainActivity.this);
        video.setMediaController(mediaController);
        video.requestFocus();//获得焦点
        video.start();//开始播放视频
        video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(MainActivity.this,"视频播放完成！",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
