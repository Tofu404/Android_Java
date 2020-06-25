package com.example.down_and_up;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

/**
 * 面试的时候面试官给我的代码要求
 *
 * 就是点击按钮然后移动view
 *
 */
public class MainActivity extends AppCompatActivity {

    TranslateAnimation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageView imageView = findViewById(R.id.image_view);
        Button button = findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            boolean isClick = true;
            @Override
            public void onClick(View view) {
                if (isClick){
                    animation = new TranslateAnimation(10, 10, 10, -800);
                    animation.setDuration(1000);
                    imageView.startAnimation(animation);
                    animation.setFillAfter(true);
                    isClick = false;
                }else{
                    //animation = new TranslateAnimation(10, 10, -800, 10);
                    AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
                    alphaAnimation.setDuration(1000);
                    //animation.setDuration(1000);
                    imageView.startAnimation(alphaAnimation);
                    animation.setFillAfter(true);
                    isClick = true;
                }
            }
        });
    }
}
