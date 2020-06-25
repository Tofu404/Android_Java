package com.example.senddatatoortheractivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class showAddressActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_address);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String name = bundle.getString("name");
        String phoneNumber = bundle.getString("phoneNumber");
        String shippingAddress = bundle.getString("province")+bundle.getString("street")+bundle.getString("userAddress");
        String meil = bundle.getString("userMail");
        TextView textView1 = findViewById(R.id.text1);
        TextView textView2 = findViewById(R.id.text2);
        TextView textView3 = findViewById(R.id.text3);
        TextView textView4 = findViewById(R.id.text4);
        textView1.setText("用户姓名："+name);
        textView2.setText("用户的收货地址："+shippingAddress);
        textView3.setText("用户联系电话："+phoneNumber);
        textView4.setText("用户联系邮箱："+meil);
    }
}
