package com.example.wechat_fragment;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView1 = findViewById(R.id.image1);
        ImageView imageView2 = findViewById(R.id.image2);
        ImageView imageView3 = findViewById(R.id.image3);
        ImageView imageView4 = findViewById(R.id.image4);
        imageView1.setOnClickListener(linsener);
        imageView2.setOnClickListener(linsener);
        imageView3.setOnClickListener(linsener);
        imageView4.setOnClickListener(linsener);
    }

    //为图片添加管理器
    View.OnClickListener linsener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //在view当中获取Fragment管理器
            FragmentManager fragmentManager = getSupportFragmentManager();
            //获取Fragment的事务
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Fragment fragment = null;
            switch (v.getId()){
                case R.id.image1:
                    fragment = new Fragment1();
                    break;
                case R.id.image2:
                    fragment = new Fragment2();
                    break;
                case R.id.image3:
                    fragment = new Fragment3();
                    break;
                case R.id.image4:
                    fragment = new Fragment4();
                    break;
                default:
                    break;
            }
            fragmentTransaction.replace(R.id.fragment,fragment);
            fragmentTransaction.commit();
        }
    };
}
