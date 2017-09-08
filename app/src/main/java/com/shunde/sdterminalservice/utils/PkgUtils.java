package com.shunde.sdterminalservice.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
/**
 * 作者：dfy on 7/9/2017 15:41
 * <p> 包管理器
 * 邮箱：dengfuyao@163.com
 */

public class PkgUtils {
    public static String getVersionName(Context context) {
        String versionName = "未知版本";
        // 包管理器
        PackageManager packageManager = context.getPackageManager();
        // packageName: 包名
        // flags : 标志位.0代表获取所有信息.
        try {
            PackageInfo packageInfo =
                    packageManager.getPackageInfo(context.getPackageName(), 0);
            versionName = packageInfo.versionName;

        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }

        return versionName;
    }

    // 获取版本号
    public static int getVersionCode(Context context) {
        int versionCode = -1;
        // 包管理器
        PackageManager packageManager = context.getPackageManager();
        // packageName: 包名
        // flags : 标志位.0代表获取所有信息.
        try {
            PackageInfo packageInfo =
                    packageManager.getPackageInfo(context.getPackageName(), 0);
            versionCode = packageInfo.versionCode;

        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }

        return versionCode;
    }
}
