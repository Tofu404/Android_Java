package com.example.radiogrouptest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //通过点击单选按钮的形式进行内容的显示
        radioGroup = (RadioGroup)findViewById(R.id.radiogroup1);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);
                Toast.makeText(MainActivity.this,"性别："+radioButton.getText(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    //通过其他的按钮的形式获取单选按钮的值
    public void myClick(View view){
        //通过循环进行单选按钮进行检测
        for (int i = 0; i < radioGroup.getChildCount();i++){
            RadioButton rb = (RadioButton) radioGroup.getChildAt(i);
            if (rb.isChecked()){
                Toast.makeText(MainActivity.this,"性别："+rb.getText(),Toast.LENGTH_SHORT).show();
            }
        }

    }
}
