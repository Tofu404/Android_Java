package com.example.radiobuttontest;

import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioGroup = findViewById(R.id.rg);
        Button button = findViewById(R.id.btu);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0;i < radioGroup.getChildCount();i++){
                    RadioButton rb = (RadioButton) radioGroup.getChildAt(i);
                    if(rb.isChecked()){
                        if(rb.getText().equals("B: 100")){
                            Toast.makeText(MainActivity.this, "恭喜你！答对啦", Toast.LENGTH_SHORT).show();
                        }else{
                            AlertDialog.Builder ab = new AlertDialog.Builder(MainActivity.this);
                            ab.setMessage("回答错误！下面请看解析：当张三换了零钱后，给了顾客75块和价值25块的商品，自己还剩下25块，当李四发现钱是假的时候，张三将剩下的25块钱另外还自己掏75块钱给会李四，所以这个过程中，张三一共就损失了25块的商品和给李四的75块共100块");
                            ab.setPositiveButton("确定",null).show();
                        }
                        break;
                    }
                }
            }
        });

    }
}
