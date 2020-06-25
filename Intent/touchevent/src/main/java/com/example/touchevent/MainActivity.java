package com.example.touchevent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //第二部：创建并实例一个自定义的对象，并为图片添加一个事件监听器
        //在重写方法中根据触摸的位置重绘图片的位置
        final PictureView myView = new PictureView(MainActivity.this);
        myView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                myView.X = event.getX();
                myView.Y = event.getY();
                myView.invalidate();
                return true;
            }
        });

        //将图片添加到布局管理器中去
        RelativeLayout layout = findViewById(R.id.RelativeLayout);
        layout.addView(myView);
    }
}
