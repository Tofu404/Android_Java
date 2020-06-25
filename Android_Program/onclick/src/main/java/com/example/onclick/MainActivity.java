package com.example.onclick;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button but1 = (Button) findViewById(R.id.but1);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"单击了按钮1",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void myClickForBut2(View view){
        Toast.makeText(MainActivity.this,"单击了按钮2",Toast.LENGTH_SHORT).show();
    }

    public void myClickForBut3(View view){
        Toast.makeText(MainActivity.this,"你已经成功授权登陆刺激战场！",Toast.LENGTH_SHORT).show();
    }
}
