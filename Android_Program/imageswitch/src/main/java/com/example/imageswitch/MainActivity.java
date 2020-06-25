package com.example.imageswitch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageSwitcher imageSwitcher = findViewById(R.id.imgswitch);
        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this,android.R.anim.fade_out));
        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this,android.R.anim.fade_in));

        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(MainActivity.this);
                imageSwitcher.setImageResource(R.drawable.bg1);
                return imageView;
            }
        });

        imageSwitcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ImageSwitcher)v).setImageResource(R.drawable.bg2);
            }
        });
    }
}
