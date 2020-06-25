package com.example.fragmenttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Fragment fragment1;
    private Fragment fragment2;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragment_test, new countFragment());
        transaction.commit();

        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        switch (view.getId()){
            case R.id.btn1:
                if (fragment2 != null){
                    transaction.remove(fragment2);
                }
                if (fragment1 == null){
                    fragment1 = new countFragment();
                }
                transaction.replace(R.id.fragment_test, fragment1);
                transaction.commit();
                break;
            case R.id.btn2:
                if (fragment1 != null){
                    transaction.remove(fragment1);
                }
                if (fragment2 == null){
                    fragment2 = new meFragment();
                }
                transaction.replace(R.id.fragment_test, fragment2);
                transaction.commit();
                break;
        }
    }
}
