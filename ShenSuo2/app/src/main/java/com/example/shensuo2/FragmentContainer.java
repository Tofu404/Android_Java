package com.example.shensuo2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.shensuo2.countFragment.CountFragment;
import com.example.shensuo2.meFragment.MeFragment;

public class FragmentContainer extends AppCompatActivity {

    private Button countFragmentPage;
    private Button meFragmentPage;
    private Button addData;
    private FrameLayout fragmentContainer;
    private CountFragment countFragment;
    private MeFragment meFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);
        //自定义ActionBar
        MyActionBar();
        //初始化控件
        initControl();
        //启动该界面的时候将countFragmemt加载进来
        lodeCountFragment();
        //当用户点击该页面按钮的时候响应点击事件
        clickBtn();
    }

    //启动FragmentContainer Activity
    public static void startFragmentContainer(Context context){
        Intent intent = new Intent(context,FragmentContainer.class);
        context.startActivity(intent);
    }

    //初始化控件
    private void initControl(){
        countFragmentPage = findViewById(R.id.count_page);
        meFragmentPage = findViewById(R.id.me_page);
        fragmentContainer = findViewById(R.id.fragment_container);
        addData = findViewById(R.id.add_data);
    }

    //lode countFragment
    private void lodeCountFragment(){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        countFragment = new CountFragment();
        transaction.add(R.id.fragment_container,countFragment);
        transaction.commit();
    }

    //当用户点击该页面按钮的时候相应的事件
    private void clickBtn(){
        //点击”账号“按钮时响应的事件
        countFragmentPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                //点击“账号”按钮显示“添加记录按钮”
                addData.setVisibility(View.VISIBLE);
                if (meFragment != null){
                    transaction.hide(meFragment);
                }
                transaction.show(countFragment);
                transaction.commit();
            }
        });

        //点击”我“按钮响应的事件
        meFragmentPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                //点击“我”的页面的时候隐藏添加记录按钮
                addData.setVisibility(View.GONE);
                if (meFragment == null){
                    meFragment = new MeFragment();
                    transaction.hide(countFragment);
                    transaction.add(R.id.fragment_container,meFragment);
                    transaction.commit();
                }else {
                    transaction.show(meFragment);
                    transaction.hide(countFragment);
                    transaction.commit();
                }
            }
        });

        //点击“添加”按钮相应的事件
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddRecourd.startAddRecourd(FragmentContainer.this);
            }
        });
    }

    //修改ActionBar的样式
    private void MyActionBar(){
        ActionBar bar = getSupportActionBar();
        bar.setDisplayShowHomeEnabled(false);
        bar.setDisplayHomeAsUpEnabled(false);
        bar.setDisplayShowCustomEnabled(true);
        bar.setCustomView(R.layout.fragment_container_action_bar_layout);
    }
}
