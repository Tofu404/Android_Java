package com.example.showandhindactionbar;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取ActionBar对象
        final ActionBar actionBar = getSupportActionBar();
        //获取按钮对象
        final Button show_action = findViewById(R.id.show_action_bar);
        Button hind_action = findViewById(R.id.hind_action_bar);
        //为按键添加监听器
        show_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionBar.show();
            }
        });

        hind_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionBar.hide();
            }
        });
    }
}
