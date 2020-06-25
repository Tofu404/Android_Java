package com.tianze.xiangting.data.Daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tianze.xiangting.data.MyDBHelper;
import com.tianze.xiangting.data.interfaces.IHistoryDao;
import com.tianze.xiangting.data.interfaces.IHistoryDaoCallback;
import com.tianze.xiangting.utils.Constants;
import com.ximalaya.ting.android.opensdk.model.PlayableModel;
import com.ximalaya.ting.android.opensdk.model.album.Announcer;
import com.ximalaya.ting.android.opensdk.model.track.Track;

import java.util.ArrayList;
import java.util.List;

public class HistoryDao implements IHistoryDao {

    private IHistoryDaoCallback mHistoryCallback = null;
    private final MyDBHelper mDbHelper;

    public void setViewCallback(IHistoryDaoCallback viewCallback) {
        mHistoryCallback = viewCallback;
    }

    public HistoryDao(Context context) {
        mDbHelper = new MyDBHelper(context);
    }

    @Override
    public void addHistory(Track historyTrack) {
        SQLiteDatabase db = null;
        boolean addResult = false;
        try {
            db = mDbHelper.getWritableDatabase();
            db.beginTransaction();
            //组装数据
            ContentValues values = new ContentValues();
            values.put(Constants.HIS_TRACK_ID, historyTrack.getDataId());
            values.put(Constants.HIS_TRACK_TITLE, historyTrack.getTrackTitle());
            values.put(Constants.HIS_TRACK_PLAY_COUNT, historyTrack.getPlayCount());
            values.put(Constants.HIS_DURATION, historyTrack.getDuration());
            values.put(Constants.HIS_UPDATE_TIME, historyTrack.getUpdatedAt());
            values.put(Constants.HIS_AUTHOR, historyTrack.getAnnouncer().getNickname());
            values.put(Constants.HIS_TRACK_COVER, historyTrack.getCoverUrlLarge());
            db.insert(Constants.HIS_TB_NAME, null, values);
            db.setTransactionSuccessful();
            addResult = true;
        } catch (Exception e) {
            e.printStackTrace();
            addResult = false;
        } finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }

            if (mHistoryCallback != null) {
                mHistoryCallback.addHistoryCallback(addResult);
            }
        }
    }

    @Override
    public void delHistory(long trechId) {
        SQLiteDatabase db = null;
        boolean delResult = false;
        try {
            db = mDbHelper.getWritableDatabase();
            db.beginTransaction();
            db.delete(Constants.HIS_TB_NAME, Constants.HIS_TRACK_ID + "=?", new String[]{String.valueOf(trechId)});
            db.setTransactionSuccessful();
            delResult = true;
        } catch (Exception e) {
            e.printStackTrace();
            delResult = false;
        } finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }

            if (mHistoryCallback != null) {
                mHistoryCallback.delHistoryCallback(delResult);
            }
        }
    }

    @Override
    public void queryHistory() {
        SQLiteDatabase db = null;
        List<Track> historyTracks = new ArrayList<>();
        try {
            db = mDbHelper.getReadableDatabase();
            db.beginTransaction();
            Cursor query = db.query(Constants.HIS_TB_NAME, null, null, null, null, null, Constants.HIS_ID +" desc");
            while (query.moveToNext()) {
                Track track = new Track();
                track.setDataId(query.getInt(query.getColumnIndex(Constants.HIS_TRACK_ID)));
                track.setTrackTitle(query.getString(query.getColumnIndex(Constants.HIS_TRACK_TITLE)));
                track.setPlayCount(query.getInt(query.getColumnIndex(Constants.HIS_TRACK_PLAY_COUNT)));
                track.setDuration(query.getInt(query.getColumnIndex(Constants.HIS_DURATION)));
                track.setUpdatedAt(query.getLong(query.getColumnIndex(Constants.HIS_UPDATE_TIME)));
                Announcer announcer = new Announcer();
                announcer.setNickname(query.getString(query.getColumnIndex(Constants.HIS_AUTHOR)));
                track.setAnnouncer(announcer);
                track.setCoverUrlLarge(query.getString(query.getColumnIndex(Constants.HIS_TRACK_COVER)));
                track.setKind(PlayableModel.KIND_TRACK);
                track.setPaid(false);
                track.setFree(true);
                historyTracks.add(track);
            }
            db.setTransactionSuccessful();
            query.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }

            if (mHistoryCallback != null) {
                mHistoryCallback.queryCallback(historyTracks);
            }
        }
    }

    @Override
    public void clearHistory() {
        SQLiteDatabase db = null;
        boolean clearResult = false;
        try {
            db = mDbHelper.getWritableDatabase();
            db.beginTransaction();
            db.delete(Constants.HIS_TB_NAME, null, null);
            db.setTransactionSuccessful();
            clearResult = true;
        } catch (Exception e) {
            e.printStackTrace();
            clearResult = false;
        } finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }

            if (mHistoryCallback != null) {
                mHistoryCallback.clearHistoryCallback(clearResult);
            }
        }
    }
}
