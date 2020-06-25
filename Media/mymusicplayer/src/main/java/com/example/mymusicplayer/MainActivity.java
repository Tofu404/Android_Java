package com.example.mymusicplayer;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private boolean flag = true;
    File file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageButton button_play = findViewById(R.id.but_play);
        ImageButton button_stop = findViewById(R.id.but_stop);
        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.hello);
        button_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag){
                    mediaPlayer.start();
                    button_play.setImageDrawable(getDrawable(R.drawable.pause));
                    flag = false;
                }else{
                    mediaPlayer.pause();
                    button_play.setImageDrawable(getDrawable(R.drawable.play));
                    flag = true;
                }
            }
        });

        button_stop.setOnClickListener(new View.OnClickListener() {//执行了这个方法之后，再从新按下播放按钮无法重新播放
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                button_play.setImageDrawable(getDrawable(R.drawable.play));
                flag = true;
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {//为音乐播放器设置一个CompletionListener监听音乐是否播放完成
            @Override
            public void onCompletion(MediaPlayer mp) {
                play();
            }
        });
    }

    private void play() {
        mediaPlayer.reset();
            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.hello);
        Toast.makeText(MainActivity.this,""+getPackageName(),Toast.LENGTH_SHORT).show();
        try {
            mediaPlayer.setDataSource(MainActivity.this,uri);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {  //当前Activity销毁时,停止正在播放的音频,并释放MediaPlayer所占用的资源
        if (mediaPlayer.isPlaying()) {             //如果音频处于播放状态
            mediaPlayer.stop();                    //停止音频的播放
        }
        mediaPlayer.release();                    //释放资源
        super.onDestroy();
    }
}
