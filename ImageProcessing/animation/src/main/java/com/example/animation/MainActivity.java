package com.example.animation;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
/**
 * 制作动画资源的要在drawable文件中创建动画资源
 */
public class MainActivity extends AppCompatActivity {
    private boolean flag = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout linearLayout = findViewById(R.id.layout);
        final AnimationDrawable anim = (AnimationDrawable) linearLayout.getBackground();//获取动画资源
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag){
                    anim.start();
                    flag = false;
                }else{
                    anim.stop();
                    flag = true;
                }
            }
        });
    }
}
