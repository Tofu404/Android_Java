package com.tianze.xiangting.utils;

import android.util.Log;

public class MyLogUtil {
    public static String BASE_TAG = "MyLog";
    public static boolean IsRelease = false;

    public static void initMyLog(String PageName, boolean isRelease) {
        BASE_TAG = PageName;
        IsRelease = isRelease;
    }

    public static void d(String TAG, String msg) {
        if (!IsRelease) {
            Log.d(BASE_TAG + "." + TAG, " == > " + msg);
        }
    }

    public static void v(String TAG, String msg) {
        if (!IsRelease) {
            Log.v(BASE_TAG + "." + TAG, " == > " + msg);
        }
    }

    public static void i(String TAG, String msg) {
        if (!IsRelease) {
            Log.i(BASE_TAG + "." + TAG, " == > " + msg);
        }
    }

    public static void w(String TAG, String msg) {
        if (!IsRelease) {
            Log.w(BASE_TAG + "." + TAG, " == > " + msg);
        }
    }

    public static void e(String TAG, String msg) {
        if (!IsRelease) {
            Log.e(BASE_TAG + "." + TAG, " == > " + msg);
        }
    }
}
