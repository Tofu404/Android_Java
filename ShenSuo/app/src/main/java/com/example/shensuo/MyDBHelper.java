package com.example.shensuo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDBHelper extends SQLiteOpenHelper {

    private final String createTableSQL = "create table tb (id integer primary key autoincrement,platformName text,count text,password text,remark text, imageID integer)";

    private  Context myContext;
    //构造方法
    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        myContext = context;
    }

    //创建数据库
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(createTableSQL);
        Toast.makeText(myContext, "MyDBHelper：创建成功~", Toast.LENGTH_SHORT).show();
    }

    //对数据进行版本升级
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
