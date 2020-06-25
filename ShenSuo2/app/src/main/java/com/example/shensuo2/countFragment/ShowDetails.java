package com.example.shensuo2.countFragment;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.shensuo2.MyDBHelper;
import com.example.shensuo2.R;

public class ShowDetails extends AppCompatActivity {

    private EditText platformName;
    private TextView platformTextImage;
    private EditText countName;
    private EditText password;
    private EditText remark;
    private String dataID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);
        //带返回按键的actionBar
        myActionBar();
        ImageButton imageButton = findViewById(R.id.back);
        imageButton.setOnClickListener(listener);
        platformName = findViewById(R.id.platform_name);
        platformTextImage = findViewById(R.id.show_platform_text_image);
        countName = findViewById(R.id.user_name);
        password = findViewById(R.id.password);
        remark = findViewById(R.id.remark);
        Intent intent = getIntent();
        dataID = intent.getStringExtra("dataID");
        fillDataToText();
    }

    //启动ShowDetails方法
    public static void startShowDetails(Context context,String dataID){
        Intent intent = new Intent(context,ShowDetails.class);
        intent.putExtra("dataID",dataID);
        context.startActivity(intent);
    }

    //自定义actionBar
    private void myActionBar(){
        ActionBar bar = getSupportActionBar();
        // 显示返回按钮
        bar.setDisplayHomeAsUpEnabled(false);
        bar.setDisplayShowCustomEnabled(true);
        bar.setCustomView(R.layout.show_detaile_action_bar_layout);
    }

    //返回按键的监听器
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };

    //填充数据
    private void fillDataToText(){
        MyDBHelper dbHelper = new MyDBHelper(ShowDetails.this,"record.db",null,1);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from tb_record where id = ?",new String[]{dataID});
        if (cursor.moveToFirst()){
            platformName.setText(cursor.getString(1));
            countName.setText(cursor.getString(2));
            password.setText(cursor.getString(3));
            remark.setText(cursor.getString(4));
            if (cursor.getInt(6) == 1){
                platformTextImage.setBackground(getResources().getDrawable(cursor.getInt(5),null));
            }else if (cursor.getInt(6) == 0){
                platformTextImage.setBackgroundColor(getResources().getColor(cursor.getInt(5)));
                platformTextImage.setText(cursor.getString(1).substring(0,1));
            }
        }
    }
}
