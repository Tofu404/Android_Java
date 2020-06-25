package com.example.javaandxmllayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;

/**
 * 在这个操作当中的有个很低级的问题需要记录一下
 * 在导入图片资源的时候我竟然将图片的名称命名为
 * 数字的形式，虽然在电脑上没有什么错误，但是在
 * Java的语法当中是不可以用数字作为一个关键字，
 * 谨记这样的低级错误希望不要在犯！！！！
 */
public class MainActivity extends AppCompatActivity{
    private ImageView[] image = new ImageView[12];
    private int[] imagePath = new int[]{
            R.mipmap.bg1, R.mipmap.bg2, R.mipmap.bg3,
            R.mipmap.bg4, R.mipmap.bg5, R.mipmap.bg6,
            R.mipmap.bg7, R.mipmap.bg8, R.mipmap.bg9,
            R.mipmap.bg10, R.mipmap.bg11, R.mipmap.bg12
    };
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //设置容器视图
        setContentView(R.layout.activity_main);
        //然后获取layout对象
        GridLayout layout = (GridLayout)findViewById(R.id.layout);
        //通过for循环输出和处理图片
        for(int i = 0;i < imagePath.length;i++){
            //创建ImageView对象，让其存放在ImageView数组当中
            image[i]  = new ImageView(MainActivity.this);
            //设置image对象的图片资源的路径
            image[i].setImageResource(imagePath[i]);
            //设置各个图片之间的内边距
            image[i].setPadding(5,5,5,5);
            //设置图片的大小，通过ViewGroup.layoutParams方法进行设置参数
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(216,348);
            image[i].setLayoutParams(params);
            //将图片视图添加到主视图当中
            layout.addView(image[i]);
        }

    }
}