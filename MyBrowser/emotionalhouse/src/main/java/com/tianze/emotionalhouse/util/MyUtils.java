package com.tianze.emotionalhouse.util;

import android.util.Base64;

public class MyUtils {

    // 加密
    public static void encryptCode(byte[] bytes) {
        byte[] decode = Base64.decode(bytes, Base64.DEFAULT);
    }

    // 解密
    public static void DecryptionCode(byte[] bytes) {

    }
}
