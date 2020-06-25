package com.tianze.xiangting.data.Daos;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tianze.xiangting.base.BaseApplication;
import com.tianze.xiangting.data.MyDBHelper;
import com.tianze.xiangting.data.interfaces.ISubDao;
import com.tianze.xiangting.data.interfaces.ISubViewCallback;
import com.tianze.xiangting.utils.Constants;
import com.ximalaya.ting.android.opensdk.model.album.Album;
import com.ximalaya.ting.android.opensdk.model.album.Announcer;

import java.util.ArrayList;
import java.util.List;

public class SubDao implements ISubDao {

    private static SubDao sSubDao = null;
    private final MyDBHelper mMyDBHelper;

    private List<ISubViewCallback> mViewCallbacks = new ArrayList<>();

    private SubDao() {
        mMyDBHelper = new MyDBHelper(BaseApplication.context);
    }

    public static SubDao getInstance() {
        if (sSubDao == null) {
            sSubDao = new SubDao();
        }
        return sSubDao;
    }

    @Override
    public void addAlbum(Album album) {
        SQLiteDatabase db = null;
        boolean addSuccess = false;
        try {
            db = mMyDBHelper.getWritableDatabase();
            db.beginTransaction();
            //封装数据
            ContentValues values = new ContentValues();
            values.put(Constants.MY_ALBUM_ID, album.getId());
            values.put(Constants.MY_TITLE, album.getAlbumTitle());
            values.put(Constants.MY_DESCRIPTION, album.getAlbumIntro());
            values.put(Constants.MY_TRACKS_COUNT, album.getIncludeTrackCount());
            values.put(Constants.MY_PLAY_COUNT, album.getPlayCount());
            values.put(Constants.MY_AUTHOR_NAME, album.getAnnouncer().getNickname());
            values.put(Constants.MY_COVER_URL, album.getCoverUrlLarge());
            db.insert(Constants.MY_TB_NAME, null, values);
            db.setTransactionSuccessful();
            addSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
            addSuccess = false;
        } finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
            //将结果返回界面层
            for (ISubViewCallback viewCallback : mViewCallbacks) {
                viewCallback.addResult(addSuccess);
            }

        }

    }

    @Override
    public void deleteAlbum(long albumId) {
        //删除数据
        SQLiteDatabase db = null;
        boolean deleteSuccess = false;
        try {
            db = mMyDBHelper.getWritableDatabase();
            db.beginTransaction();
            db.delete(Constants.MY_TB_NAME, Constants.MY_ALBUM_ID + "= ?", new String[]{String.valueOf(albumId)});
            db.setTransactionSuccessful();
            deleteSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
            deleteSuccess = false;
        } finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
            //将结果返回界面层
            for (ISubViewCallback viewCallback : mViewCallbacks) {
                viewCallback.deleteResult(deleteSuccess);
            }
        }
    }

    @Override
    public void queryAlbum() {
        //查询数据
        SQLiteDatabase db = mMyDBHelper.getReadableDatabase();
        List<Album> subAlbum = new ArrayList<>();
        try {
            db.beginTransaction();
            Cursor query = db.query(Constants.MY_TB_NAME, null, null, null, null, null, null);
            if (query.moveToFirst()) {
                while (!query.isAfterLast()) {
                    Album album = new Album();
                    album.setId(query.getLong(query.getColumnIndex(Constants.MY_ALBUM_ID)));
                    album.setCoverUrlLarge(query.getString(query.getColumnIndex(Constants.MY_COVER_URL)));
                    album.setAlbumTitle(query.getString(query.getColumnIndex(Constants.MY_TITLE)));
                    album.setAlbumIntro(query.getString(query.getColumnIndex(Constants.MY_DESCRIPTION)));
                    album.setPlayCount(query.getInt(query.getColumnIndex(Constants.MY_PLAY_COUNT)));
                    album.setIncludeTrackCount(query.getInt(query.getColumnIndex(Constants.MY_TRACKS_COUNT)));
                    Announcer announcer = new Announcer();
                    announcer.setNickname(query.getString(query.getColumnIndex(Constants.MY_AUTHOR_NAME)));
                    album.setAnnouncer(announcer);
                    subAlbum.add(album);
                    query.moveToNext();
                }
            }
            query.close();
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }

            for (ISubViewCallback viewCallback : mViewCallbacks) {
                viewCallback.queryResult(subAlbum);
            }
        }
    }

    @Override
    public void registerViewCallback(ISubViewCallback iSubViewCallback) {
        if (iSubViewCallback != null && !mViewCallbacks.contains(iSubViewCallback)) {
            mViewCallbacks.add(iSubViewCallback);
        }
    }

    @Override
    public void unregisteredViewCallback(ISubViewCallback iSubViewCallback) {
        mViewCallbacks.remove(iSubViewCallback);
    }

}
