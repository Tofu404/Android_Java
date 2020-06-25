package com.example.musicservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageButton button = findViewById(R.id.imageButton);
        final Intent intent = new Intent(MainActivity.this,MusicService.class);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MusicService.isplay == false){//要是没有播放音乐的话，那就可是播放音乐
                    startService(intent);
                    ((ImageButton)v).setImageDrawable(getResources().getDrawable(R.drawable.stop));
                }else{
                    stopService(intent);
                    ((ImageButton)v).setImageDrawable(getResources().getDrawable(R.drawable.play));
                }
            }
        });
    }

    @Override
    protected void onStart() {
        startService(new Intent(MainActivity.this,MusicService.class));
        super.onStart();
    }
}
