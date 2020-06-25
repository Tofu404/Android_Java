package com.example.scrollview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout linearLayout1 = findViewById(R.id.mainLayout);
        LinearLayout linearLayout2 = new LinearLayout(MainActivity.this);
        linearLayout2.setOrientation(LinearLayout.VERTICAL);
        ScrollView scrollView = new ScrollView(MainActivity.this);
        linearLayout1.addView(scrollView);
        scrollView.addView(linearLayout2);
        TextView textView = new TextView(MainActivity.this);
        textView.setText(R.string.introdation);
        textView.setTextSize(25.0f);
        ImageView imageView = new ImageView(MainActivity.this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(params);
        imageView.setImageResource(R.drawable.picture);
        linearLayout2.addView(textView);
        linearLayout2.addView(imageView);
    }
}
