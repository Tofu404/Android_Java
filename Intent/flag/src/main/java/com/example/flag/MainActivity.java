package com.example.flag;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //第一钟的时候是在没有添加flag的时候的按返回案件的时候会停留在Activity的历史队列当中的
                Intent intent = new Intent(MainActivity.this,Activity_nihao.class);
                //第二钟的时候是在没有添加flag的时候的按返回案件的时候是不会停留在Activity的历史队列当中的
                //intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);//这个属性是设置Activity不保留在Activity栈队当中的
                startActivity(intent);

            }
        });
    }
}
