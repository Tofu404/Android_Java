package com.example.progress;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final int TIME = 60;//这个进度完成的时间，完成的时间为六十秒
    private int jindu = 0;//记录进度
    ProgressBar progressBar;
    double i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progress);
        progressBar.setProgress(0);
        handler.sendEmptyMessage(0x1212);
    }

    //创建Handle对象，实现1秒更新一次进度
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (TIME - jindu > 0){
                jindu++;
                i = ((double)jindu/TIME)*100;
                progressBar.setProgress((int)i);
                handler.sendEmptyMessageDelayed(0x1212,1000);//经过1秒的时间发送消息
            }else{
                Toast.makeText(MainActivity.this,"时间到！",Toast.LENGTH_SHORT).show();
            }
        }
    };
}
