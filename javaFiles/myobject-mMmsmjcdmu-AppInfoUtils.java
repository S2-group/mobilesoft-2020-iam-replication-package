package com.ds.master.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.ds.master.bean.AppInfo;
import com.ds.master.bean.RunAppInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/10.
 */
public class AppInfoUtils {

    /** 应用管理工具类*/
    private static AppInfoUtils appInfoUtils;
    /** 应用程序管理器 */
    private ActivityManager activityManager;
    /** 包管理者 */
    private final PackageManager packageManager;

    private AppInfoUtils(Context context) {
        activityManager =  (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        packageManager = context.getPackageManager();
    }

    /**
     * 创建newAppInfoUtils
     * @param context
     * @return
     */
    public static AppInfoUtils newAppInfoUtils(Context context) {
        if (appInfoUtils == null) {
            synchronized (AppInfoUtils.class) {
                if (appInfoUtils == null) {
                    appInfoUtils = new AppInfoUtils(context);
                }
            }
        }
        return appInfoUtils;
    }

    /**
     * 获取运行应用列表
     * @return
     */
    public List<RunAppInfo> getRunningAppList(boolean isSystem){
        List<RunAppInfo> list =  new ArrayList<>();
       //List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo ar : activityManager.getRunningAppProcesses()){
            //String processName = ar.processName;//进程包名
            //int pid = ar.pid;//进程id
            try {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(ar.processName, 0);//应用信息
               /* Drawable drawable = applicationInfo.loadIcon(packageManager);//图标
                String name = applicationInfo.loadLabel(packageManager).toString();//应用名称
                //获取进程所占的内存
                Debug.MemoryInfo[] processMemoryInfo = activityManager.getProcessMemoryInfo(new int[]{pid});
                String runMemory = processMemoryInfo[0].getTotalPrivateDirty() * 1024 + "";
                boolean isSystem = applicationInfo.flags == ApplicationInfo.FLAG_SYSTEM;*/
                if(isSystem == ((applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0)){
                    list.add(new RunAppInfo(ar.pkgList,applicationInfo.loadLabel(packageManager).toString()
                            ,activityManager.getProcessMemoryInfo(new int[]{ar.pid})[0].getTotalPrivateDirty()*1024 + ""
                            ,applicationInfo.loadIcon(packageManager)
                            ,(applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0));
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    /**
     * 获取手机安装应用
     * @param tag 0系统 1非系统 2全部
     * @return List<AppInfo> 信息集合
     */
    public List<AppInfo> getAppList(int tag){
        List<AppInfo> list =  new ArrayList<>();
        for (PackageInfo packageInfo : packageManager.getInstalledPackages(PackageManager.GET_INSTRUMENTATION)) {
            //packageInfo.applicationInfo.loadIcon(packageManager);//图标
            //packageInfo.applicationInfo.loadLabel(packageManager);//应用名
            //packageInfo.versionName;//版本名
            //packageInfo.versionCode;//版本码
            //packageInfo.packageName;//包名

            switch (tag){
                case AppInfo.SYSTEM_TAG://系统
                    if((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0){
                        list.add(new AppInfo(false,
                                packageInfo.applicationInfo.loadLabel(packageManager).toString(),
                                packageInfo.packageName,
                                packageInfo.versionName,
                                packageInfo.versionCode,
                                packageInfo.applicationInfo.loadIcon(packageManager)));
                    }
                    break;
                case AppInfo.NOT_SYSTEM_TAG://非系统
                    if((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0){
                        list.add(new AppInfo(false,
                                packageInfo.applicationInfo.loadLabel(packageManager).toString(),
                                packageInfo.packageName,
                                packageInfo.versionName,
                                packageInfo.versionCode,
                                packageInfo.applicationInfo.loadIcon(packageManager)));
                    }
                    break;
                case AppInfo.ALL_TAG:
                    list.add(new AppInfo(false,
                            packageInfo.applicationInfo.loadLabel(packageManager).toString(),
                            packageInfo.packageName,
                            packageInfo.versionName,
                            packageInfo.versionCode,
                            packageInfo.applicationInfo.loadIcon(packageManager)));
                    break;
            }
        }
        return list;
    }
}
