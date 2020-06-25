package com.example.myview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取FrameLayout对象
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.myLayout);
        final ItHome ithome = new ItHome(this);
        ithome.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //获取屏幕上的位置
                ithome.x = event.getX();
                ithome.y = event.getY();
                //重新画位图（在视觉上形成一个可以拖动的效果）
                ithome.invalidate();
                return true;
            }
        });
        //将图片的视图添加到主视图中
        frameLayout.addView(ithome);
    }
}
