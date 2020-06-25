package com.tianze.xiangting.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.tianze.xiangting.utils.Constants;

public class MyDBHelper extends SQLiteOpenHelper {

    public MyDBHelper(@Nullable Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建专辑列表数据表
        String subSql = "create table " + Constants.MY_TB_NAME + "(" +
                Constants.MY_ID + " integer primary key autoincrement, " +
                Constants.MY_COVER_URL + " varchar, " +
                Constants.MY_TITLE + " varchar," +
                Constants.MY_DESCRIPTION + " varchar," +
                Constants.MY_PLAY_COUNT + " integer," +
                Constants.MY_TRACKS_COUNT + " integer," +
                Constants.MY_AUTHOR_NAME + " varchar," +
                Constants.MY_ALBUM_ID + " integer" +
                ")";
        db.execSQL(subSql);

        //创建播放历史数据表
        String historySql = "create table " + Constants.HIS_TB_NAME + "(" +
                Constants.HIS_ID + " integer primary key autoincrement," +
                Constants.HIS_TRACK_ID + " integer, " +
                Constants.HIS_TRACK_TITLE + " varchar," +
                Constants.HIS_TRACK_PLAY_COUNT + " integer," +
                Constants.HIS_DURATION + " integer ," +
                Constants.HIS_UPDATE_TIME + " integer," +
                Constants.HIS_AUTHOR + " varchar," +
                Constants.HIS_TRACK_KIND + "varchar," +
                Constants.HIS_TRACK_IS_PAID + "BOOLEAN," +
                Constants.HIS_TRACK_IS_FREE + "BOOLEAN," +
                Constants.HIS_TRACK_COVER + " varchar)";
        db.execSQL(historySql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
