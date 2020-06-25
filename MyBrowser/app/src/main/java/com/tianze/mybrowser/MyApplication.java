package com.tianze.mybrowser;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.tianze.mybrowser.db.entity.DaoMaster;
import com.tianze.mybrowser.db.entity.DaoSession;

public class MyApplication extends Application {

    private static final String DB_NAME = "mark_book.db";

    public static Context sContext;

    public static DaoSession sDaoSession = null;

    @Override
    public void onCreate() {
        super.onCreate();
        initAppContext();
        initDaoSession();
    }

    public void initAppContext() {
        sContext = this;
    }

    public void initDaoSession() {
        if (sDaoSession == null) {
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, DB_NAME);
            SQLiteDatabase database = helper.getWritableDatabase();
            DaoMaster daoMaster = new DaoMaster(database);
            sDaoSession = daoMaster.newSession();
        }
    }
}
