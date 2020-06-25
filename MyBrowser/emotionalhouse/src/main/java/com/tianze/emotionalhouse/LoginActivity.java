package com.tianze.emotionalhouse;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tianze.emotionalHouse.R;
import com.tianze.emotionalhouse.util.MyUtils;
import com.tianze.emotionalhouse.util.SharedPreferencesUtil;

public class LoginActivity extends AppCompatActivity {

    private ImageView mUploadImage;
    private EditText mUserAccount;
    private EditText mUserPW;
    private ImageView mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findView();
        initEvent();
        initAnimation();
    }

    private void initAnimation() {

        //登陆按下动画

        //动画弹起动画
        Animation loginBtnDownAnimation = new ScaleAnimation(1.0f,0.6f,1.0f,0.6f);
    }

    private void initEvent() {

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!mUserAccount.getText().toString().equals("")&&!mUserPW.getText().toString().equals("")){
                    // 点击登陆的时候自动保存用户的账号和密码
                    String builder = mUserAccount.getText().toString() +
                            "（：" +
                            mUserPW.getText().toString();
                    SharedPreferencesUtil.getInstance(LoginActivity.this).saveInfo(builder);
                    finish();
                }else {
                    Toast toast = Toast.makeText(LoginActivity.this, "", Toast.LENGTH_SHORT);
                    toast.setText("请输入账号和密码");
                    toast.show();
                }
            }
        });
    }

    private void findView() {
        mUploadImage = findViewById(R.id.upload_image);
        mUserAccount = findViewById(R.id.user_account);
        mUserPW = findViewById(R.id.user_pw);
        mLogin = findViewById(R.id.login_btn);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, LoginActivity.class);
        context.startActivity(starter);
    }
}