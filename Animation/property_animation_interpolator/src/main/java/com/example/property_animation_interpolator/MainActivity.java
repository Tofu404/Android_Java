package com.example.property_animation_interpolator;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    int ttt = 0;
    private boolean flag = true;
    private int res[] = new int[]{
            R.id.imageView1,
            R.id.imageView2,
            R.id.imageView3,
            R.id.imageView4,
            R.id.imageView5
    };

    private List<ImageView> imageViews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initList();
        hindIcon();
        imageViews.get(0).setOnClickListener(this);
    }



    private void initList(){
        for (int i = 0; i < res.length ; i++) {
            ImageView imageView = findViewById(res[i]);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, "你点击了图片", Toast.LENGTH_SHORT).show();
                }
            });
            imageViews.add(imageView);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageView1:
                if (flag){
                    showEnterAnim(130);
                }else {
                    claseicon(130);
                }
                break;
            default:
                break;
        }
    }

    private void claseicon(int dp) {
        //for循环来开始小图标的出现动画
        for (int i = 1; i < res.length; i++) {
            AnimatorSet set = new AnimatorSet();
            double x = -Math.cos(0.5/(res.length-2)*(i-1)*Math.PI)* MainActivity.dip2px(this,dp);    //0.5/(res.length-4)*(i-1)*Math.PI
            double y = -Math.sin(0.5/(res.length-2)*(i-1)*Math.PI)* MainActivity.dip2px(this,dp);    //0.5/(res.length-4)*(i-1)*Math.PI
            Log.i(TAG, "Cos: "+Math.cos(0.5/(res.length-2)*(i-1)*Math.PI));
            Log.i(TAG, "Sin: "+Math.sin(0.5/(res.length-2)*(i-1)*Math.PI));
            set.playTogether(
                    ObjectAnimator.ofFloat(imageViews.get(i),"translationX",(float)x,0),
                    ObjectAnimator.ofFloat(imageViews.get(i),"translationY",(float)y,0)
                    ,ObjectAnimator.ofFloat(imageViews.get(i),"alpha",1,0).setDuration(2000)
            );
            set.setInterpolator(new BounceInterpolator());
            set.setDuration(800).setStartDelay(100*i);
            set.start();
        }
        //转动加号大图标本身45°
        ObjectAnimator rotate = ObjectAnimator.ofFloat(imageViews.get(0),"rotation",360,0).setDuration(1000);
        rotate.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                Toast.makeText(MainActivity.this, "动画已经播放完毕！", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        rotate.setInterpolator(new BounceInterpolator());
        rotate.start();

        //菜单状态置打开
        flag = true;
    }

    private void showEnterAnim(int dp) {
        //for循环来开始小图标的出现动画
        for (int i = 1; i < res.length; i++) {
            AnimatorSet set = new AnimatorSet();
            double x = -Math.cos(0.5*Math.PI/(res.length-2)*(i-1))* MainActivity.dip2px(this,dp);    //0.5/(res.length-4)*(i-1)*Math.PI
            double y = -Math.sin(0.5*Math.PI/(res.length-2)*(i-1))* MainActivity.dip2px(this,dp);    //0.5/(res.length-4)*(i-1)*Math.PI
            set.playTogether(
                    ObjectAnimator.ofFloat(imageViews.get(i),"translationX",(float) (0.1*x*i),(float)x),
                    ObjectAnimator.ofFloat(imageViews.get(i),"translationY",(float) (0.1*y*i),(float)y)
                    ,ObjectAnimator.ofFloat(imageViews.get(i),"alpha",0,1).setDuration(2000)
            );
            set.setInterpolator(new BounceInterpolator());
            set.setDuration(800).setStartDelay(100*i);
            set.start();
        }
        //转动加号大图标本身45°
        ObjectAnimator rotate = ObjectAnimator.ofFloat(imageViews.get(0),"rotation",0,360).setDuration(1000);
        rotate.setInterpolator(new BounceInterpolator());
        rotate.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Toast.makeText(MainActivity.this, "动画已经播放完毕！", Toast.LENGTH_SHORT).show();
            }
        });
        rotate.start();

        //菜单状态置打开
        flag = false;
    }

    private void hindIcon(){
        for (int i = 1; i < res.length ; i++) {
            ObjectAnimator.ofFloat(imageViews.get(i), "alpha", 1,0).start();
        }
    }



    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale);
    }
}