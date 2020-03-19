/*
 * Copyright (c) 2015 Martino Lessio -
 * www.martinolessio.com
 * martino [at] iziozi [dot] org
 *
 *
 * This file is part of the IziOzi project.
 *
 * IziOzi is free software:
 * you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * IziOzi is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with IziOzi.
 * If not, see http://www.gnu.org/licenses/.
 */

package it.iziozi.iziozi.helpers;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Display;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.iziozi.iziozi.core.IOInfoObject;

/**
 * Created by martinolessio on 14/03/15.
 */
public class IOHelper {

    public enum Orientation {
        VERTICAL,
        HORIZONTAL
    }

    private static String TAG = "IOHelper";

    public static final int IO_PERMISSIONS_READ_STORAGE_FOR_LOADING = 1;
    public static final int IO_PERMISSIONS_WRITE_STORAGE_FOR_SAVINGAS = 2;
    public static final int IO_PERMISSIONS_WRITE_STORAGE_FOR_SAVING = 3;

    public static final int IO_PERMISSIONS_GENERIC_REQUEST = 99;

    public static final String[] IO_REQUIRED_PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.INTERNET,
            Manifest.permission.BLUETOOTH,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.GET_ACCOUNTS
    };

    public static Boolean canGoImmersive() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            return true;
        return false;
    }

    public static int mod(int x, int y) {
        int result = x % y;
        return result < 0 ? result + y : result;
    }

    //Unused yet
    public static ArrayList<IOInfoObject> getInstalledApps(boolean getSysPackages, Context context) {
        ArrayList<IOInfoObject> res = new ArrayList<IOInfoObject>();
        List<PackageInfo> packs = context.getPackageManager().getInstalledPackages(0);
        for(int i=0;i<packs.size();i++) {
            PackageInfo p = packs.get(i);
            if ((!getSysPackages) && (p.versionName == null)) {
                continue ;
            }
            IOInfoObject newInfo = new IOInfoObject();
            newInfo.appname = p.applicationInfo.loadLabel(context.getPackageManager()).toString();
            newInfo.pname = p.packageName;
            newInfo.versionName = p.versionName;
            newInfo.versionCode = p.versionCode;
            newInfo.icon = p.applicationInfo.loadIcon(context.getPackageManager());
            res.add(newInfo);
        }
        return res;
    }


    public static boolean checkForRequiredPermissions(Activity targetActivity) {

        List<String> neededPermissions = new ArrayList<>();

        for (String permission : IO_REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(targetActivity, permission) != PackageManager.PERMISSION_GRANTED)
                neededPermissions.add(permission);
        }

        Log.d(TAG, Arrays.toString(neededPermissions.toArray()));

        if (!neededPermissions.isEmpty()) {
            String[] params = neededPermissions.toArray(new String[neededPermissions.size()]);

            ActivityCompat.requestPermissions(targetActivity,params, IO_PERMISSIONS_GENERIC_REQUEST);

            // No permissions, wait if needed...
            return false;
        } else {
            // All ok guys!
            return true;
        }

    }

    public static Orientation getOrientation(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        if (size.x > size.y) return Orientation.HORIZONTAL;
        return Orientation.VERTICAL;
    }
}

