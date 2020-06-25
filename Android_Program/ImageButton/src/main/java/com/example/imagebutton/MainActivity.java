package com.example.imagebutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //这是设置软件全屏显示的设置
        getWindow().setFlags(WindowManager.LayoutParams.FLAGS_CHANGED,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ImageButton imagbut1 = (ImageButton)findViewById(R.id.imagButton1);
        ImageButton imagbut2 = (ImageButton)findViewById(R.id.imagButton2);
        imagbut1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"这是密码按钮",Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void myClick(View view){
        Toast.makeText(MainActivity.this,"这是登陆按钮",Toast.LENGTH_SHORT).show();

    }
}
