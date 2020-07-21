package create.by.taobaounion.utils;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import create.by.taobaounion.MyApplication;

public class SpUtil {

    /**
     * 保存分类信息
     * @param SpName
     * @param cacheInfoStr
     */
    public static void saveBySp(String SpName, String cacheInfoStr){
        assert MyApplication.getAppContext() != null;
        SharedPreferences sharedPreferences = MyApplication.getAppContext().getSharedPreferences(SpName,Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("categoriesCacheInfo",cacheInfoStr);
        edit.apply();
    }

    public static String getCategoriesCacheInfo(String SpName){
        assert MyApplication.getAppContext() != null;
        SharedPreferences sharedPreferences = MyApplication.getAppContext().getSharedPreferences(SpName, Context.MODE_PRIVATE);
        return sharedPreferences.getString("categoriesCacheInfo","");
    }
}
