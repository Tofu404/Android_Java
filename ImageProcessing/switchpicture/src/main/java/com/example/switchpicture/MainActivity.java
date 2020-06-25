package com.example.switchpicture;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {
    ViewFlipper flipper;//定义ViewFlipper
    GestureDetector detector; //定义手势检测器
    int imageId [] = new int[]{
            R.drawable.picture1, R.drawable.picture2,
            R.drawable.picture3, R.drawable.picture4,
            R.drawable.picture5, R.drawable.picture6,
            R.drawable.picture7, R.drawable.picture8,
            R.drawable.picture9
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        detector = new GestureDetector(this,this);
        flipper = findViewById(R.id.flipper);
        //将图片添加到ViewFlipper里面
        for (int i=0;i<imageId.length;i++){
            ImageView imageView = new ImageView(MainActivity.this);
            imageView.setImageResource(imageId[i]);
            flipper.addView(imageView);
        }
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {//滑动手势
        if (e1.getX() - e2.getX() > 50){
            flipper.setInAnimation(MainActivity.this,R.anim.anim_in);
            flipper.setOutAnimation(MainActivity.this,R.anim.anim_out);
            flipper.showPrevious();
            return true;
        }
        if (e2.getX() - e1.getX() > 50){
            flipper.setInAnimation(MainActivity.this,R.anim.anim_in);
            flipper.setOutAnimation(MainActivity.this,R.anim.anim_out);
            flipper.showPrevious();
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //将Activity上的触摸事件通过手势识别进行处理
        return detector.onTouchEvent(event);
    }
}
