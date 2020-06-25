package com.tianze.recycleviewlooper;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mLooper;
    private LooperAdapter mLooperAdapter;
    private int[] mImages;
    Handler handler = new Handler();
    private int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        autoSeichToNext();
        Log.e("TAG", "onCreate: nihao");
    }

    private void autoSeichToNext() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                currentPosition++;
                mLooper.scrollToPosition(currentPosition);
                autoSeichToNext();
            }
        },2000);
    }

    private void initData() {
        mImages = new int[]{
                R.drawable.bg1,
                R.drawable.bg2,
                R.drawable.bg3
        };
        currentPosition = mImages.length*100;
    }

    private void initView() {
        mLooper = findViewById(R.id.looper);
        mLooper.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mLooperAdapter = new LooperAdapter();
        mLooperAdapter.setData(mImages);
        mLooper.setAdapter(mLooperAdapter);
        mLooper.scrollToPosition(currentPosition);
    }

}
