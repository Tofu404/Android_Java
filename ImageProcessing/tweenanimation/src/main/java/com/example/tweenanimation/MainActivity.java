package com.example.tweenanimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageView imageView = findViewById(R.id.image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Animation anima = AnimationUtils.loadAnimation(MainActivity.this,R.anim.alpha);//透明渐变动画
                //Animation anima = AnimationUtils.loadAnimation(MainActivity.this,R.anim.rotate);//旋转动画
                //Animation anima = AnimationUtils.loadAnimation(MainActivity.this,R.anim.scale);//放大动画
                Animation anima = AnimationUtils.loadAnimation(MainActivity.this,R.anim.translate);//平移动画
                imageView.startAnimation(anima);
            }
        });
    }
}
