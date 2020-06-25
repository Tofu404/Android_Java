package com.example.property_animation;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.translate).setOnClickListener(this);
        findViewById(R.id.alpha).setOnClickListener(this);
        findViewById(R.id.all).setOnClickListener(this);
        findViewById(R.id.scale).setOnClickListener(this);
        findViewById(R.id.rotate).setOnClickListener(this);
        imageView = findViewById(R.id.image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "你点击了视图", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        ObjectAnimator objectAnimator = new ObjectAnimator();
        switch (view.getId()){
            case R.id.all:

                //这里是用来测试单个动画的效果的
//                ObjectAnimator traslation = ObjectAnimator.ofFloat(imageView, "X", 0,300);
//                ObjectAnimator rotate = ObjectAnimator.ofFloat(imageView, "rotation", 0f,720f);
//                ObjectAnimator sclae = ObjectAnimator.ofFloat(imageView, "scaleX",1,2);
//                ObjectAnimator alpha = ObjectAnimator.ofFloat(imageView, "alpha", 0f,1f);
//                traslation.setDuration(2000);
//                rotate.setDuration(2000);
//                sclae.setDuration(2000);
//                alpha.setDuration(2000);

                //使用PropertyValuesHolder类对要使用的多个动画的参数进行优化和封装
//                PropertyValuesHolder p1 = PropertyValuesHolder.ofFloat("translationX", 0,300);
//                PropertyValuesHolder p2 = PropertyValuesHolder.ofFloat("rotation", 0,720);
//                PropertyValuesHolder p3 = PropertyValuesHolder.ofFloat("alpha", 0f,1f);
//                PropertyValuesHolder p4 = PropertyValuesHolder.ofFloat("scaleX", 1,2);
//                PropertyValuesHolder p5 = PropertyValuesHolder.ofFloat("scaleY", 1,2);
//                ObjectAnimator.ofPropertyValuesHolder(imageView, p1,p2,p3,p4,p5).setDuration(2000).start();

                //使用Set对象实现更多的组合动画效果
                AnimatorSet set = new AnimatorSet();
                ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(imageView, "X", 0,900,0);
                ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(imageView, "Y", 0,1350,0);
                ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(imageView, "rotation",0,1440);

                //动画一起播放的
                //set.playTogether(objectAnimator1,objectAnimator2,objectAnimator3);

                //动画按顺序播放
//                set.playSequentially(objectAnimator1,objectAnimator2,objectAnimator3);
//                set.setDuration(2000);
//                set.start();
//                AnimatorSet set1 = new AnimatorSet();
//                set1.playTogether(objectAnimator1,objectAnimator2,objectAnimator3);
//                set1.setDuration(2000);
//                set1.setStartDelay(8000);
//                set1.start();

                //更加精细的顺序动画
                //set.play(objectAnimator1).with(objectAnimator2);
                //set.play(objectAnimator3).after(objectAnimator2);
                //set.play(objectAnimator2).before(objectAnimator3);
                set.play(objectAnimator1).after(2000);
                set.setDuration(2000);
                set.start();
                //这里是用来测试多个动画的效果的

                break;
            case R.id.translate:
                objectAnimator = ObjectAnimator.ofFloat(imageView, "X", 0f,360f,0f);
                objectAnimator.setDuration(5000);
                //objectAnimator.setRepeatCount(-1); //这个参数是用来设置动画的重复的次数的，为“-1”表示无限循环，正数代表要训话多少次
                objectAnimator.start();
                break;
            case R.id.alpha:
                objectAnimator = ObjectAnimator.ofFloat(imageView, "alpha", 0f,1f);
                objectAnimator.setDuration(5000);
                //objectAnimator.setRepeatCount(-1); //这个参数是用来设置动画的重复的次数的，为“-1”表示无限循环，正数代表要训话多少次
                objectAnimator.start();
//                objectAnimator.addListener(new Animator.AnimatorListener() {
//                    @Override
//                    public void onAnimationStart(Animator animator) {
//
//                    }
//
//                    @Override
//                    public void onAnimationEnd(Animator animator) {
//                        Toast.makeText(MainActivity.this, "你点击了渐变动画按钮！",Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onAnimationCancel(Animator animator) {
//
//                    }
//
//                    @Override
//                    public void onAnimationRepeat(Animator animator) {
//
//                    }
//                });

                objectAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        Toast.makeText(MainActivity.this, "你点击了渐变动画按钮！",Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.scale:
                objectAnimator = ObjectAnimator.ofFloat(imageView, "scaleX",1f,2f,1f);
                objectAnimator.setDuration(5000);
                objectAnimator.start();
                break;
            case R.id.rotate:
                objectAnimator = ObjectAnimator.ofFloat(imageView, "rotation", 0f,360f,360,0f);
                objectAnimator.setDuration(1000);
                objectAnimator.start();
                break;
        }
    }
}
