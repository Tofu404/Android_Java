package com.tianze.dynamicgradientbutton;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private Button mDynamicGradientButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDynamicGradientButton = findViewById(R.id.my_btn);

        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(Color.parseColor("#FF00FF00"));
        mDynamicGradientButton.setBackground(gradientDrawable);


        // 创建渐变的shape drawable
        int colors[] = { 0xff255779 , 0xff3e7492, 0xffa6c0cd };//分别为开始颜色，中间夜色，结束颜色
        GradientDrawable gd = new GradientDrawable(GradientDrawable.Orientation.BL_TR, colors);
        mDynamicGradientButton.setBackground(gd);

        AnimationDrawable background = (AnimationDrawable) mDynamicGradientButton.getBackground();
        background.setEnterFadeDuration(2000);
        background.start();
    }

}