package com.tianze.mybrowser;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tianze.mybrowser.statusbar.StatusBarUtil;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBar();
        setContentView(setLayoutId());
    }

    //设置布局
    public abstract int setLayoutId();

    //设置StatusBar的颜色
    private void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (isUseFullScreenMode()) {
                StatusBarUtil.transparencyBar(this);
            } else {
                StatusBarUtil.setStatusBarColor(this, setStatusBarColor());

            }

            if (isUseBlackFontWithStatusBar()) {
                StatusBarUtil.setLightStatusBar(this, true, isUseFullScreenMode());
            }
        }
    }

    //获取StatusBar的颜色
    protected int setStatusBarColor() {
        return R.color.white;
    }

    //是否使用黑色字体
    protected boolean isUseBlackFontWithStatusBar() {
        return true;
    }

    //是否为全屏模式
    protected boolean isUseFullScreenMode() {
        return false;
    }
}
