/*
 * Copyright (C) 2015 75py
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.nagopy.android.mypkgs.util;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.UserHandle;
import android.widget.TextView;

import java.util.List;

public class Logic {

    /**
     * ApplicationInfoを取得する.
     *
     * @param packageManager PackageManager
     * @param packageName    パッケージ名
     * @return ApplicationInfo.<br>見つからない場合はnull
     */
    public static ApplicationInfo getApplicationInfo(PackageManager packageManager, String packageName) {
        List<ApplicationInfo> installedAppList = packageManager.getInstalledApplications(getRetrieveFlags());
        for (ApplicationInfo info : installedAppList) {
            if (info.packageName.equals(packageName)) {
                return info;
            }
        }
        return null; // NOT_FOUND
    }

    /**
     * {@link android.content.pm.PackageManager#getInstalledApplications(int)}の引数に使う値を返す.<br>
     * 以下のクラスを参照。<br>
     * /packages/apps/Settings/src/com/android/settings/applications/ApplicationsState.java
     */
    public static int getRetrieveFlags() {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            // 4.1以下
            return PackageManager.GET_UNINSTALLED_PACKAGES |
                    PackageManager.GET_DISABLED_COMPONENTS;
        }

        // 4.2以上
        // > Only the owner can see all apps.
        // とのことなので、IDが0（＝オーナー）は全部見られる、的なフラグ設定らしい
        MethodReflectWrapper myUserIdMethod = new MethodReflectWrapper(UserHandle.class, "myUserId");
        int myUserId = (int) myUserIdMethod.invoke(null);
        if (myUserId == 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                // 4.3以上
                return PackageManager.GET_UNINSTALLED_PACKAGES |
                        PackageManager.GET_DISABLED_COMPONENTS |
                        PackageManager.GET_DISABLED_UNTIL_USED_COMPONENTS;
            } else {
                // 4.2
                return PackageManager.GET_UNINSTALLED_PACKAGES |
                        PackageManager.GET_DISABLED_COMPONENTS;
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                // 4.3以上
                return PackageManager.GET_DISABLED_COMPONENTS |
                        PackageManager.GET_DISABLED_UNTIL_USED_COMPONENTS;
            } else {
                // 4.2
                return PackageManager.GET_DISABLED_COMPONENTS;
            }
        }
    }

    /**
     * TextViewの左側（START側）に画像を表示する。
     *
     * @param textView     対象View
     * @param drawable     画像
     * @param drawableSize 画像サイズ
     */
    public static void setIcon(TextView textView, Drawable drawable, int drawableSize) {
        drawable.setBounds(0, 0, drawableSize, drawableSize);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            textView.setCompoundDrawables(drawable, null, null, null);
        } else {
            textView.setCompoundDrawablesRelative(drawable, null, null, null);
        }
        drawable.setCallback(null);
    }

}
