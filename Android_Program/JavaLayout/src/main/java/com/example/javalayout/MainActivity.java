package com.example.javalayout;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //添加view布局控件
        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.setBackgroundResource(R.mipmap.bt);
        setContentView(frameLayout);
        //添加文本框
        TextView text1 = new TextView(this);
        //设置文本框的字体的大小和字体颜色
        text1.setTextSize(TypedValue.COMPLEX_UNIT_SP,30);
        text1.setTextColor(Color.rgb(100,1,35));
        text1.setText("你好呀！我是孔天泽！");
        //设置文字的位置
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        //这个是通过layout的参数然后就通过这个layout的参数设置text1的layout参数
        params.gravity = Gravity.CENTER;
        text1.setLayoutParams(params);
        //为文本组件设置点击的事件
        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this).setTitle("系统提示！")
                        .setMessage("游戏有风险，进入需谨慎！真的要进入吗？")
                        //设置一个确定的按钮
                        .setPositiveButton("确定",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Log.i("图片是飞龙","进入游戏");
                                    }
                                })
                        .setNegativeButton("取消",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Log.i("图片是飞龙","退出游戏！");
                                                finish();
                                    }
                                }).show();
            }
        });
        frameLayout.addView(text1);
    }
}
