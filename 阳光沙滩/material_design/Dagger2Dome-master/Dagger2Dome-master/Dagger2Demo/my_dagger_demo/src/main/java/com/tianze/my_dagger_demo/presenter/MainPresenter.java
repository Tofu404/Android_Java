package com.tianze.my_dagger_demo.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class MainPresenter {

    private Context mContext;

    public MainPresenter(Context context){
        mContext = context;
    }

    public void showToast(){
        Toast.makeText(mContext, "nihao", Toast.LENGTH_SHORT).show();
        Log.e("nihao", "showToast: 我被点击了" );
    }
}
