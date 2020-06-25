package com.tianze.xiangting.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {

    private static Toast toast = null;
    private static String oldMsg;
    private static long oneTime = 0;
    private static long twoTime = 0;


    public static void showToast(Context context, String msg) {
        if (toast == null) {
            toast = Toast.makeText(context, null, Toast.LENGTH_SHORT);
            toast.setText(msg);
            toast.show();
            oneTime = System.currentTimeMillis();
        } else {
            twoTime = System.currentTimeMillis();
            if (msg.equals(oldMsg)) {
                if (twoTime - oneTime > Toast.LENGTH_SHORT) {
                    toast.show();
                }
            } else {
                oldMsg = msg;
                toast.setText(msg);
                toast.show();
            }
        }
        oneTime = twoTime;
    }

    public static void showToast(Context context, int resId){
        String msg = context.getString(resId);
        showToast(context, msg);
    }

}
