package com.example.shuzu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.show_result).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a[][] = new int[][]{{1,3},{4,15}};
                for (int i = 0; i <a.length ; i++) {
                    for (int j = 0; j <a[i].length ; j++) {
                        Log.e("shuzu", "onClick: "+a[i][j] );
                    }
                }
            }
        });
    }
}
