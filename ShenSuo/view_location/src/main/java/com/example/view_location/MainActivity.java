package com.example.view_location;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.app.ActionBar;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private boolean isClike = true;
    private static final String TAG = "MainActivity";
    Button button1;
    Button button2;
    ScrollView scrollView;
    LinearLayout A1;
    LinearLayout A2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text);
        button1 = findViewById(R.id.b1);
        button2 = findViewById(R.id.b2);
        scrollView = findViewById(R.id.scroll);
        A2 = findViewById(R.id.linear2);
        A1 = findViewById(R.id.linear1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isClike){
                    ObjectAnimator animator = ObjectAnimator.ofFloat(A2,"translationY",0f,(float) A1.getHeight());
                    animator.setInterpolator(new BounceInterpolator());
                    animator.setDuration(1000);
                    animator.start();
                    TextView textView = findViewById(R.id.test);
                    int j = A1.getHeight();
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins(0,0,0,j);
                    textView.setLayoutParams(layoutParams);
                    isClike = false;
                }else {
                    ObjectAnimator animator = ObjectAnimator.ofFloat(A2,"translationY",(float) A1.getHeight(),0f);
                    animator.setInterpolator(new BounceInterpolator());
                    animator.setDuration(1000);
                    animator.start();
                    TextView textView = findViewById(R.id.test);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins(0,0,0,0);
                    textView.setLayoutParams(layoutParams);
                    isClike = true;
                }
            }
        });
    }
}