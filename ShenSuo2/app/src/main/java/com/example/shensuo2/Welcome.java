package com.example.shensuo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Welcome extends AppCompatActivity {
    /**
     * 这个页面是软件第一次运行的时候弹出来的页面
     * 这个页面主要实现两个功能：
     * 1、一个是引导用户创建主密码，并村粗用户的主密码
     *
     * 2、记录软件是否第一次运行软件，要是第一次运行并完成注册，则再次运行软件就不再跳出该页面
     * @param savedInstanceState
     */
    private EditText majorPW1;
    private EditText majorPW2;
    private Button finishRegister;
    private boolean isFirst = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //判断是否第一次正确运行软件
        isFirstEnter();
        //初始化控件
        initControl();
        //为“完成注册”按钮添加监听事件
        finishRegister.setOnClickListener(listener);
    }

    //初始化所有的控件
    private void initControl(){
        majorPW1 = findViewById(R.id.major_pw_1);
        majorPW2 = findViewById(R.id.major_pw_2);
        finishRegister = findViewById(R.id.finish_register);
    }

    //按钮事件监听器
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (TextUtils.isEmpty(majorPW1.getText().toString()) || TextUtils.isEmpty(majorPW2.getText().toString())){
                Toast.makeText(Welcome.this,"请完整填写密码信息！",Toast.LENGTH_SHORT).show();
            }else{
                if (majorPW1.getText().toString().equals(majorPW2.getText().toString())){
                    //用户正确填写注册信息，则将用户的主密码信息和用户是否第一次运行该软件存到文件中
                    isFirst = true;
                    SharedPreferences.Editor editor = getSharedPreferences("isFirst",MODE_PRIVATE).edit();
                    editor.putString("majorPW",majorPW2.getText().toString());
                    editor.putBoolean("isFirstEnter",isFirst);
                    editor.apply();
                    MainActivity.startMainActivity(Welcome.this);
                    finish();
                }else {
                    Toast.makeText(Welcome.this,"两次输入密码不一致，请改正！",Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    //从“isFirst”文件中取出“isFirstEnter”变量判断是否第一次启动该页面
    private void isFirstEnter(){
        SharedPreferences sharedPreferences = getSharedPreferences("isFirst",MODE_PRIVATE);
        boolean b = sharedPreferences.getBoolean("isFirstEnter",false);
        if (b){
            MainActivity.startMainActivity(Welcome.this);
            finish();
        }
    }
}
