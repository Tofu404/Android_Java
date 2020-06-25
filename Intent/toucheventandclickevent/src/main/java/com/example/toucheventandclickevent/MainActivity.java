package com.example.toucheventandclickevent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

/**
 * 单击和触摸的区别：
 * 要是为某个控件添加了触摸事件的话添加的触摸事件就可以触发两个事件
 * 一个就是触摸事件 另一个是点击事件。两个事件先触发触摸事件，然后再
 * 触发点击事件。要是return了false就说明这个事件的处理还没有处理完成
 * 要是返回了true的话就说明这个事件已经处理完成了，就不会触发点击事件
 *
 * 点击事件的话，就是只会触发点击事件
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("onClick","点击了按钮");
            }
        });

        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    Log.i("onTouch","按下");
                }else if (event.getAction() == MotionEvent.ACTION_UP){
                    Log.i("onTouch","抬起");
                }
                //return false;
                return true;
            }
        });
    }
}
