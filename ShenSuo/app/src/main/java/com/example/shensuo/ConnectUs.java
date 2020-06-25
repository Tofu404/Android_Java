package com.example.shensuo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class ConnectUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_us);
        ActionBar bar = getSupportActionBar();
        bar.setTitle("联系我们");
    }

    public static void startConnectUsActivity(Context context){
        Intent intent = new Intent(context,ConnectUs.class);
        context.startActivity(intent);
    }
}
