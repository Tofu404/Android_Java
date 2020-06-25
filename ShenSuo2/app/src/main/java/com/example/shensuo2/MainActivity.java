package com.example.shensuo2;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.CycleInterpolator;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    EditText majorpw;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        majorpw = findViewById(R.id.major_pw);
        findViewById(R.id.enter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str;
                SharedPreferences sharedPreferences = getSharedPreferences("isFirst",MODE_PRIVATE);
                str = sharedPreferences.getString("majorPW","");
                majorpw = findViewById(R.id.major_pw);
                if (TextUtils.isEmpty(majorpw.getText().toString())){
                    Toast.makeText(MainActivity.this,"请输入密码！",Toast.LENGTH_SHORT).show();
                }else {
                    if (majorpw.getText().toString().equals(str)){
                        //密码输入正确的话，就跳转到FragmentContainer页面
                        FragmentContainer.startFragmentContainer(MainActivity.this);
                        finish();
                    }else {
                        Toast.makeText(MainActivity.this,"输入密码错误！",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    //启动MainActivity函数
    public static void startMainActivity(Context context){
        Intent intent = new Intent(context,MainActivity.class);
        context.startActivity(intent);
    }
}
