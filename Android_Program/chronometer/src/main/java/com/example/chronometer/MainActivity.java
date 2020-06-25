package com.example.chronometer;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

/**
 * 计时器的启动有以下的参数：
 * 1、setBase（）设置计时器的起始时间
 * 2、setFormat（）设置显示时间的格式
 * 3、start（）指定开始计时
 * 4、stop（）指定停止计时
 * 5、setOnChronometerTickListener（）为计时器绑定监听器，当计时器改变的时候触发该监听器
 */

public class MainActivity extends AppCompatActivity {
    Chronometer chronometer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //这个语句就是为了让软件全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //从layout中获取Chronom对象
        chronometer = findViewById(R.id.timer);
        //设置计时器的起始时间
        chronometer.setBase(SystemClock.elapsedRealtime());
        //设置显示时间的格式
        chronometer.setFormat("%s");
        //指定开始的时间
        chronometer.start();
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if (SystemClock.elapsedRealtime()-chronometer.getBase() >= 6000){
                    chronometer.stop();
                    Toast.makeText(MainActivity.this,"时间到！你赢啦",Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button button = findViewById(R.id.reStart);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
            }
        });
    }
}
