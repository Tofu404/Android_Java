package com.example.shensuo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Welcome extends AppCompatActivity {

    /**
     * 实现App首次启动的时候是一个欢迎的界面，第二次启动App的时候就是正常的一个操作页面
     *
     * 实现原理：通过存储本地数据的方式，进行一个状态的判定，如果ifFirst为true的话就直接进入
     *           操作页面，要是为false的话，及进入欢迎页面
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        if (isFirst()){
            MainActivity.startMainActivity(Welcome.this);
            finish();
        }
        findViewById(R.id.sign_up).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText major_pw1 = findViewById(R.id.major_pw1);
                EditText major_pw2 = findViewById(R.id.major_pw2);
                if (!major_pw1.getText().toString().equals(major_pw2.getText().toString())){
                    major_pw2.setText("");
                    Toast.makeText(Welcome.this, "两次输入的密码，不一致~", Toast.LENGTH_SHORT).show();
                }else {
                    //如果两次密码输入正确的话，那么就将主密码保存起来，将是否是第一次启动该页面的信息也存储起来
                    saveData(major_pw2);
                    OprationActivity.startActivity(Welcome.this);
                    finish();
                }
            }
        });
    }

    private void saveData(EditText major_pw2){
        SharedPreferences.Editor editor = getSharedPreferences("isFirst", MODE_PRIVATE).edit();
        editor.putString("pw", major_pw2.getText().toString());
        editor.putBoolean("isFirst", true);
        editor.apply();
    }

    private Boolean isFirst(){
        SharedPreferences sp = getSharedPreferences("isFirst",MODE_PRIVATE);
        Boolean b = sp.getBoolean("isFirst",false);
        return b;
    }
}
