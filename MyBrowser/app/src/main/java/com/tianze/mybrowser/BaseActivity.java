package com.tianze.mybrowser;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tianze.mybrowser.statusbar.BlackFontStatusBarUtil;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new  BlackFontStatusBarUtil(this,true,true);
        setContentView(setLayoutId());
    }
    //设置布局
    public abstract int setLayoutId();
}
