package com.tianze.mybrowser;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.PaintDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.tianze.emotionalhouse.LoginActivity;
import com.tianze.emotionalhouse.util.SharedPreferencesUtil;


public class MyPopupWindow extends PopupWindow {
    private ConstraintLayout mTeachMe;
    private Context mContext;
    private ConstraintLayout mVersion;
    private ConstraintLayout mUseGoogle;
    private ConstraintLayout mUserNulogin;
    private TextView mLogout;
    private ConstraintLayout mUserLogin;
    private TextView mIntoTheNewWord;
    private Animation mRotateAnimation;

    //空构造方法
    public MyPopupWindow(Context context, int width, int height) {
        super(width, height);
        mContext = context;
        setFocusable(true);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_my_popup_window, null, false);
        findView(view);
        initEvent();
        initAnimation();
        setContentView(view);
        //6.0以下要添加一个背景（解决展现之后点击事件没有响应的问题）
        setBackgroundDrawable(new PaintDrawable());
        setOutsideTouchable(true);
        setTouchable(true);
    }

    private void initAnimation() {
        mRotateAnimation = new RotateAnimation(0f,20f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        mRotateAnimation.setInterpolator(new BounceInterpolator());
        mRotateAnimation.setRepeatCount(-1);
        mRotateAnimation.setDuration(1000);
    }

    //找到控件
    public void findView(View view) {
        mTeachMe = view.findViewById(R.id.teach_me);
        mVersion = view.findViewById(R.id.version);
        mUseGoogle = view.findViewById(R.id.use_google);
        mUserNulogin = view.findViewById(R.id.user_nulogin);
        mUserLogin = view.findViewById(R.id.user_login);
        mLogout = view.findViewById(R.id.logout);
        mIntoTheNewWord = view.findViewById(R.id.into_the_new_word);
    }

    private void initEvent() {

        mTeachMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUtils.MyToast(mContext, "功能还在开发中……");
            }
        });

        mVersion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUtils.MyToast(mContext, "不要再点啦，版本信息都写得明明白白了");
            }
        });

        mUserNulogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.start(mContext);
            }
        });

        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferencesUtil.getInstance(mContext).saveInfo("");
                hindLoginOption();
            }
        });
    }

    @Override
    public void showAsDropDown(View anchor) {
        super.showAsDropDown(anchor);
        hindLoginOption();
    }

    private void hindLoginOption() {
        // 判断本地是否有密码
        String s = SharedPreferencesUtil.getInstance(mContext).readInfo();
        if (s.length() > 0){
            mUserNulogin.setVisibility(View.GONE);
        }else {
            mUserNulogin.setVisibility(View.VISIBLE);
            mLogout.setVisibility(View.GONE);
            mUserLogin.setVisibility(View.GONE);
        }

        // 已登陆的时候为“进入新世界”按钮设置动画
        if (mIntoTheNewWord.getVisibility() == View.VISIBLE){

            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mIntoTheNewWord,"rotation",0f,20f,-20f,0);
            objectAnimator.setDuration(1000);

            objectAnimator.setInterpolator(new AccelerateInterpolator());
            objectAnimator.setRepeatCount(-1);
            //mIntoTheNewWord.startAnimation(mRotateAnimation);
            objectAnimator.start();
        }
    }
}
