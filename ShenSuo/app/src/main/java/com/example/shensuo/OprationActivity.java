package com.example.shensuo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

public class OprationActivity extends AppCompatActivity implements View.OnClickListener {

    private Fragment countFragment;
    private Fragment meFragment;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private long exitTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opration_activity_layout);

        //actionBar模块
        ActionBar bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(false);
        bar.setHomeButtonEnabled(false);
        bar.setDisplayShowHomeEnabled(false);
        bar.setDisplayShowCustomEnabled(true);
        //bar.setBackgroundDrawable(new ColorDrawable(0xffff1));
        bar.setCustomView(R.layout.actionbar_layout);


        findViewById(R.id.count_page).setOnClickListener(this);
        findViewById(R.id.me_pge).setOnClickListener(this);
        findViewById(R.id.add).setOnClickListener(this);


        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        countFragment = new CountFragment();
        transaction.add(R.id.fargment_contanor, countFragment);
        transaction.commit();
    }


    @Override
    public void onClick(View view) {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        switch (view.getId()){
            case R.id.count_page:
                findViewById(R.id.add).setVisibility(View.VISIBLE);
                if (meFragment != null){
                    transaction.hide(meFragment);
                }
                transaction.show(countFragment);
                transaction.commit();
                break;
            case R.id.me_pge:
                findViewById(R.id.add).setVisibility(View.GONE);
                if (meFragment != null){
                    transaction.hide(countFragment);
                    transaction.show(meFragment);
                    transaction.commit();
                }else {
                    transaction.hide(countFragment);
                    meFragment = new MeFragment();
                    transaction.add(R.id.fargment_contanor, meFragment);
                    transaction.commit();
                }
                break;
            case R.id.add:
                AddRecord.startActivity(OprationActivity.this);
                break;
        }
    }


    public static void startActivity(Context context){
        Intent intent = new Intent(context,OprationActivity.class);
        context.startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK){
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
        }
        return false;
    }
}
