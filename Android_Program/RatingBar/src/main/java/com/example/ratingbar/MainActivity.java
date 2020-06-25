package com.example.ratingbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

/**
 * 这个程序就是通过星星进度条设置当前的进度
 */

public class MainActivity extends AppCompatActivity {
    RatingBar ratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ratingBar = findViewById(R.id.ratingbar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                String str = "选中了"+ratingBar.getRating()+"颗星星！\n" + "每次改变"+ratingBar.getStepSize()+"颗星星！\n" + "进度为："+ratingBar.getProgress();
                Toast.makeText(MainActivity.this,str,Toast.LENGTH_SHORT).show();
            }
        });

        Button button = findViewById(R.id.but);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"获得了"+ratingBar.getRating()+"颗星",Toast.LENGTH_SHORT) .show();
            }
        });
    }
}
