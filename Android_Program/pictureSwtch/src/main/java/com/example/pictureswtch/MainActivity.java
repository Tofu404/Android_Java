package com.example.pictureswtch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity {
    private float touchDownX;
    private float touchUpX;
    private ImageSwitcher imageSwitcher;
    private int index = 0;
    private int [] imgResourcr = new int [] {R.drawable.login,R.drawable.password,R.drawable.touxiang};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置为全屏显示
        final ImageSwitcher imageSwitcher = findViewById(R.id.pictureSwitch);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setImageResource(imgResourcr[index]);
                return imageView;
            }
        });

        imageSwitcher.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //获取用户的触摸屏幕时的坐标
                //判断用户是否触摸手机
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    touchDownX = event.getX();
                    return true;
                //判断用户的手是否离开手机屏幕
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    touchUpX = event.getX();
                    //判断用户是向左滑动还是向右滑动
                    if(touchUpX - touchDownX > 100){//要是触摸屏幕到离开屏幕间的距离超过100的话就认为是向右滑动
                        index = index == 0?imgResourcr.length-1:index-1;
                        //切换动画效果
                        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this,android.R.anim.fade_in));
                        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this,android.R.anim.fade_out));
                        //设置要切换的图片
                        imageSwitcher.setImageResource(imgResourcr[index]);
                    }else if (touchDownX - touchUpX > 100){//判断是否是向左滑动
                        index = index == imgResourcr.length-1?0:index+1;
                        //切换动画效果
                        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this,android.R.anim.fade_in));
                        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this,android.R.anim.fade_out));
                        //设置要切换的图片
                        imageSwitcher.setImageResource(imgResourcr[index]);
                    }
                    return true;
                }
                return false;
            }
        });

    }
}
