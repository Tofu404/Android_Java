package com.tianze.my_dagger_demo.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.tianze.my_dagger_demo.Component.DaggerMainCom;
import com.tianze.my_dagger_demo.R;
import com.tianze.my_dagger_demo.module.MainModule;
import com.tianze.my_dagger_demo.presenter.MainPresenter;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    private Button mClickBtn;

    @Inject
    public MainPresenter mMainPresenter= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaggerMainCom.builder().mainModule(new MainModule(this)).build().Inject(this);
        initView();
        initEvent();
    }

    private void initEvent() {
        mClickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo:触发点击事件
                if (mMainPresenter != null) {
                    mMainPresenter.showToast();
                }
            }
        });
    }

    private void initView() {
        mClickBtn = findViewById(R.id.click_btn);
    }

}
