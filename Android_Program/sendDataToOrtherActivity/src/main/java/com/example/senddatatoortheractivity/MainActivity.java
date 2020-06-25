package com.example.senddatatoortheractivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /**
     * 两个Activity间传递信息的要点
     * 1、创建Bundle对象和Intent对象
     * 2、调用bundle中的putXXX()方法，将要进行交互的数据放到bundle对象中
     * 3、调用intent.putExtras(bundle);将bundle作为参数，添加到Intent中
     * 4、调用startActivity方法启动另外的Activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.but);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ((EditText)findViewById(R.id.name)).getText().toString();
                String province = ((EditText)findViewById(R.id.province)).getText().toString();
                String street = ((EditText)findViewById(R.id.street)).getText().toString();
                String userAddress = ((EditText)findViewById(R.id.userAddress)).getText().toString();
                String phoneNumber = ((EditText)findViewById(R.id.phoneNumber)).getText().toString();
                String userMail = ((EditText)findViewById(R.id.userMail)).getText().toString();
                //判断用户是否将所有的信息准确输入,要是正确输入那就将信息提交到addressActivity中去，
                // 要是没有填写完那就提示用户填写完成
                if (!"".equals(name)&&!"".equals(province)&&!"".equals(street)&& !"".equals(userAddress)&&!"".equals(phoneNumber)&&!"".equals(userMail)){
                    Intent intent = new Intent(MainActivity.this,showAddressActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putCharSequence("name",name);
                    bundle.putCharSequence("province",province);
                    bundle.putCharSequence("street",street);
                    bundle.putCharSequence("userAddress",userAddress);
                    bundle.putCharSequence("phoneNumber",phoneNumber);
                    bundle.putCharSequence("userMail",userMail);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this,"请将地址信息填写完整！",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
