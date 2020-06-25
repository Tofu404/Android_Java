package com.example.intent_data;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.intent_data.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton imageButton = findViewById(R.id.imageButton);
        ImageButton imageButton2 = findViewById(R.id.imageButton2);
        imageButton.setOnClickListener(listener);
        imageButton2.setOnClickListener(listener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            ImageButton imageButton = (ImageButton)v;
            switch (imageButton.getId()){
                case R.id.imageButton:
                    intent.setAction(intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:13128641075"));
                    startActivity(intent);
                    break;

                case R.id.imageButton2:
                    intent.setAction(intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("smsto:5554"));
                    intent.putExtra("sms_body","welcome to Android world!");
                    startActivity(intent);
                    break;
            }
        }
    };
}
