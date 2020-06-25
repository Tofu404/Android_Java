package com.tianze.emotionalhouse.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtil {

    private static SharedPreferences sSharedPreferences = null;
    private static SharedPreferencesUtil sSharedPreferencesUtil = null;

    private SharedPreferencesUtil(Context context) {
        sSharedPreferences = context.getSharedPreferences("LoginInfo", Context.MODE_PRIVATE);
    }

    public static SharedPreferencesUtil getInstance (Context context) {
        if (sSharedPreferencesUtil == null) {
            synchronized (SharedPreferencesUtil.class) {
                if (sSharedPreferencesUtil == null) {
                    sSharedPreferencesUtil = new SharedPreferencesUtil(context);
                }
            }
        }
        return sSharedPreferencesUtil;
    }

    public void saveInfo(String str) {
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        editor.putString("accountAndPw", str);
        editor.apply();
    }

    public String readInfo() {
        return sSharedPreferences.getString("accountAndPw","");
    }
}
