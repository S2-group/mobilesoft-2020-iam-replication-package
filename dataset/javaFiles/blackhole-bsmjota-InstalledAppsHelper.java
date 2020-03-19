package org.thezero.applist;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import org.thezero.applist.model.InstalledApk;

import java.util.ArrayList;

public class InstalledAppsHelper
{
    public static InstalledApk getInstalledApk(final Context context, final String s) throws PackageManager.NameNotFoundException {
        final PackageInfo packageInfo = context.getPackageManager().getPackageInfo(s, 0);
        final InstalledApk installedApk = new InstalledApk();
        installedApk.setName(packageInfo.applicationInfo.loadLabel(context.getPackageManager()).toString());
        installedApk.setPackageName(packageInfo.packageName);
        installedApk.setVersionName(packageInfo.versionName);
        installedApk.setVersionCode(packageInfo.versionCode);
        installedApk.setPath(packageInfo.applicationInfo.sourceDir);
        installedApk.setDate(installedApk.getApkFile().lastModified());
        long size = 0L;
        if (installedApk.getApkFile() != null) {
            size += installedApk.getApkFile().length();
        }
        if (installedApk.getMainObbFile() != null) {
            size += installedApk.getMainObbFile().length();
        }
        if (installedApk.getPatchObbFile() != null) {
            size += installedApk.getPatchObbFile().length();
        }
        installedApk.setSize(size);
        if (context.getPackageManager().getLaunchIntentForPackage(installedApk.getPackageName()) == null) {
            installedApk.setSystemApp(true);
        }
        else {
            installedApk.setSystemApp(false);
        }
        installedApk.setIcon(packageInfo.applicationInfo.loadIcon(context.getPackageManager()));
        return installedApk;
    }
    
    public static ArrayList<InstalledApk> getInstalledApps(final Context context, final boolean b) {
        final ArrayList<InstalledApk> list = new ArrayList<InstalledApk>();
        for (final PackageInfo packageInfo : context.getPackageManager().getInstalledPackages(0)) {
            if (b || packageInfo.versionName != null) {
                final InstalledApk installedApk = new InstalledApk();
                installedApk.setName(packageInfo.applicationInfo.loadLabel(context.getPackageManager()).toString());
                installedApk.setPackageName(packageInfo.packageName);
                installedApk.setVersionName(packageInfo.versionName);
                installedApk.setVersionCode(packageInfo.versionCode);
                installedApk.setPath(packageInfo.applicationInfo.sourceDir);
                installedApk.setDate(installedApk.getApkFile().lastModified());
                long size = 0L;
                if (installedApk.getApkFile() != null) {
                    size += installedApk.getApkFile().length();
                }
                if (installedApk.getMainObbFile() != null) {
                    size += installedApk.getMainObbFile().length();
                }
                if (installedApk.getPatchObbFile() != null) {
                    size += installedApk.getPatchObbFile().length();
                }
                installedApk.setSize(size);
                if (context.getPackageManager().getLaunchIntentForPackage(installedApk.getPackageName()) == null) {
                    installedApk.setSystemApp(true);
                }
                else {
                    installedApk.setSystemApp(false);
                }
                installedApk.setIcon(packageInfo.applicationInfo.loadIcon(context.getPackageManager()));
                list.add(installedApk);
            }
        }
        return list;
    }
}
