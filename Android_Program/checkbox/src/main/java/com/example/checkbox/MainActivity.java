package com.example.checkbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //定义Button类型和CheckBox类型的全局变量
    Button but;
    CheckBox checkBox1,checkBox2,checkBox3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        but = findViewById(R.id.but_login);
        checkBox1 = findViewById(R.id.checkbox1);
        checkBox2 = findViewById(R.id.checkbox2);
        checkBox3 = findViewById(R.id.checkbox3);

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = "获取的权限为：\n";
                if(checkBox1.isChecked()){
                    str += checkBox1.getText()+"\n";
                }
                if(checkBox2.isChecked()){
                    str += checkBox2.getText()+"\n";
                }
                if(checkBox3.isChecked()){
                    str += checkBox3.getText();
                }
                if (checkBox1.isChecked()||checkBox2.isChecked()||checkBox3.isChecked()){
                    Toast.makeText(MainActivity.this,str,Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"你已经成功授权！",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
