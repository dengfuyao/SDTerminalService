package com.shunde.sdterminalservice.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
/**
 * 作者：dfy on 7/9/2017 15:51
 * <p> 轻量级存储的工具类
 * 邮箱：dengfuyao@163.com
 */

public class SPUtils {
    // 获取一个Boolean值
    public static boolean getBoolean(Context context, String key) {
        return getBoolean(context, key, false);
    }

    // 获取一个Boolean值
    public static boolean getBoolean(Context context, String key,
                                     boolean defValue) {
        SharedPreferences sp = context.getSharedPreferences(Constants.SP_NAME,
                Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }

    // 保存一个Boolean值
    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences(Constants.SP_NAME,
                Context.MODE_PRIVATE);

        Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    // 获取一个String值
    public static String getString(Context context, String key) {
        return getString(context, key, null);
    }

    // 获取一个String值
    public static String getString(Context context, String key,
                                   String defValue) {
        SharedPreferences sp = context.getSharedPreferences(Constants.SP_NAME,
                Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }

    // 保存一个String值
    public static void putString(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(Constants.SP_NAME,
                Context.MODE_PRIVATE);

        Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }
}
