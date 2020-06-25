package com.example.replacehead;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


/**
 * 两个Activity间交换数据的流程
 * 1、在两个Activity中交换数据用的的是intent对象和bundle对象完成的
 * 2、在MainActivity中创建intent对象并通过startActivityForResult（参数一：intent对象，
 * 参数二：Activity识别码）方法启动要启动的Activity（注意：要启动该Activity的时候要注册）
 * 3、在headActivity中的创建intent对象的时候直接通过getintent（）方法获取intent对象就可以了
 * 4、在headActivity中将要交换的数据存放到bundle（key，value）对象中（该对象要在headActivity中创建）
 * 5、将存放了数据的bundle对象添加到intent对象当中，通过intent对象在两个Activity间交换数据
 * 6、在MainActivity中的处理返回的数据，通过重写onActivityResult方法来处理数据
 * 7、在onActivityResult方法中通过data.getExtras()方法获取bundle对象，通过bundle对象中getExtras方法获取对应的数据
 */
public class MainActivity extends AppCompatActivity {

    //重写onActivityResult方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0x11 && resultCode == 0x11){
            Bundle bundle = data.getExtras();
            int img = bundle.getInt("image");
            ImageView imageView = findViewById(R.id.image);
            imageView.setImageResource(img);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.submitBut);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,HeadActivity.class);
                startActivityForResult(intent,0x11);
            }
        });


    }
}
