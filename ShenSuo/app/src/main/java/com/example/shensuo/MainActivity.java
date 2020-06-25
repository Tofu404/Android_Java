package com.example.shensuo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    //状态栏的颜色 #02584C
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText majorPwext = findViewById(R.id.user_major_password);
        Button sure = findViewById(R.id.sure);
        mActionBar();
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(majorPwext.getText().toString())){
                    Toast.makeText(MainActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                }else {
                    if (majorPwext.getText().toString().equals(getPW())){
                        OprationActivity.startActivity(MainActivity.this);
                        finish();
                    }else {
                        Toast.makeText(MainActivity.this, "很伤心，你的密码错误~", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public static void startMainActivity(Context context){
        Intent intent = new Intent(context,MainActivity.class);
        context.startActivity(intent);

    }

    //获取用户注册时留下的主密码
    private String getPW(){
        SharedPreferences sharedPreferences = getSharedPreferences("isFirst", MODE_PRIVATE);
        String pw = sharedPreferences.getString("pw", "");
        return pw;
    }

    private void mActionBar(){
        ActionBar bar = getSupportActionBar();
        bar.setDisplayShowCustomEnabled(true);
        bar.setCustomView(R.layout.action_bar);
    }
}
