package com.example.shensuo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

public class CountDetails extends AppCompatActivity {

    private String ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_details);
        mActionBar();
        ID = getIntent().getStringExtra("countMessageID");
        MyDBHelper dbHelper = new MyDBHelper(this,"record.db",null,1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from tb where id = "+ID,null);
        EditText platformName = findViewById(R.id.count_details_platform_name);
        EditText name = findViewById(R.id.count_details_name);
        EditText pw = findViewById(R.id.count_details_pw);
        EditText remark = findViewById(R.id.count_details_remark);
        ImageView imageView = findViewById(R.id.count_details_imageID);
        if (cursor.moveToFirst()) {
            platformName.setText(cursor.getString(1));
            name.setText(cursor.getString(2));
            pw.setText(cursor.getString(3));
            remark.setText(cursor.getString(4));
            imageView.setImageResource(cursor.getInt(5));
        }
        cursor.close();
    }

    //启动显示账号详情页面的时候根据记录的ID进行详情的显示
    public static  void startCountDetailsActivity(Context context,int countMessageID){
        Intent intent = new Intent(context,CountDetails.class);
        intent.putExtra("countMessageID",String.valueOf(countMessageID));
        context.startActivity(intent);
    }

    //actionbar样式
    private void mActionBar(){
        ActionBar bar = getSupportActionBar();
        bar.setDisplayShowCustomEnabled(true);
        bar.setCustomView(R.layout.action_bar);
    }
}
