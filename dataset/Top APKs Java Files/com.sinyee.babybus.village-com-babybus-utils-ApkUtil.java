package com.babybus.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.babybus.app.App;
import com.babybus.app.C.Path;
import com.babybus.bean.AppInfoBean;
import com.babybus.bean.AppInfoForWonderlandWBean;
import com.babybus.managers.ApkDownloadManager;
import com.babybus.utils.downloadutils.InstallInfo;
import com.babybus.utils.downloadutils.InstallUtil;
import com.babybus.utils.permissionsutils.PermissionUtil;
import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ApkUtil
{
  private static long LOCK_TIME = 1000L;
  
  public ApkUtil() {}
  
  public static boolean apkIsComplete(String paramString)
  {
    return App.get().getPackageManager().getPackageArchiveInfo(paramString, 1) != null;
  }
  
  public static boolean chetByQQ(Activity paramActivity, String paramString)
  {
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("mqqwpa://im/chat?chat_type=wpa&uin=");
      localStringBuilder.append(paramString);
      localStringBuilder.append("&version=1");
      paramString = localStringBuilder.toString();
      if (ShellCmdBuilder.isCmdStartActivity())
      {
        ShellCmdBuilder.get().setAction("android.intent.action.VIEW").addFlags(268435456).setDataUri(paramString).execShellCmd();
        return true;
      }
      paramActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString)));
      return true;
    }
    catch (Exception paramActivity)
    {
      for (;;) {}
    }
    ToastUtil.toastShort("您的设备上还没有安装QQ哦~");
    return false;
  }
  
  public static boolean clickLock(long paramLong)
  {
    return Math.abs(paramLong - App.get().lastTime) < LOCK_TIME;
  }
  
  public static String dealPackageName(String paramString)
  {
    if (paramString.endsWith(".huawei")) {
      return paramString.substring(0, paramString.indexOf(".huawei"));
    }
    String str = paramString;
    if (paramString.endsWith(".nearme.gamecenter")) {
      str = paramString.substring(0, paramString.indexOf(".nearme.gamecenter"));
    }
    return str;
  }
  
  public static boolean downloadManagerIsEnabled()
  {
    boolean bool3 = false;
    boolean bool2 = false;
    try
    {
      int i = App.get().getPackageManager().getApplicationEnabledSetting("com.android.providers.downloads");
      if (Build.VERSION.SDK_INT > 18)
      {
        bool1 = bool2;
        if (i != 2)
        {
          bool1 = bool2;
          if (i != 3)
          {
            bool1 = bool2;
            if (i != 4) {
              bool1 = true;
            }
          }
        }
        return bool1;
      }
      boolean bool1 = bool3;
      if (i != 2)
      {
        bool1 = bool3;
        if (i != 3) {
          bool1 = true;
        }
      }
      return bool1;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    LogUtil.d("com.android.providers.downloads is no found");
    return false;
  }
  
  public static int getApkVersionCode(String paramString)
  {
    Iterator localIterator = App.get().getPackageManager().getInstalledPackages(0).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if (TextUtils.equals(paramString, localPackageInfo.packageName)) {
        return localPackageInfo.versionCode;
      }
    }
    return -1;
  }
  
  public static String getAppName()
  {
    try
    {
      int i = App.get().getApplicationContext().getPackageManager().getPackageInfo(App.get().getPackageName(), 0).applicationInfo.labelRes;
      String str = App.get().getResources().getString(i);
      return str;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return "";
  }
  
  public static List<AppInfoBean> getBabyBusInstalledAppList()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = getInstalledAppList().iterator();
    while (localIterator.hasNext())
    {
      AppInfoBean localAppInfoBean = (AppInfoBean)localIterator.next();
      if ((!TextUtils.equals(localAppInfoBean.getPackageName(), "com.sinyee.babybus.recommendapp")) && (!TextUtils.equals(localAppInfoBean.getPackageName(), "com.sinyee.babybus.wonderland")) && (!TextUtils.equals(localAppInfoBean.getPackageName(), "com.sinyee.babybus.bbtime.android")) && (!TextUtils.equals(localAppInfoBean.getPackageName(), "com.sinyee.babybus.recommendapp")) && (!TextUtils.equals(localAppInfoBean.getPackageName(), "com.sinyee.babybus.chants")) && (!TextUtils.equals(localAppInfoBean.getPackageName(), "com.sinyee.babybus.talk2kiki")) && (!TextUtils.equals(localAppInfoBean.getPackageName(), App.get().packName))) {
        localArrayList.add(localAppInfoBean);
      }
    }
    return localArrayList;
  }
  
  public static String getInstalledAppInfo()
  {
    ArrayList localArrayList = new ArrayList();
    PackageManager localPackageManager = App.get().getPackageManager();
    if (localPackageManager != null)
    {
      Iterator localIterator = localPackageManager.getInstalledPackages(0).iterator();
      while (localIterator.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        if ((localPackageInfo.applicationInfo.flags & 0x1) == 0) {
          localArrayList.add(new AppInfoForWonderlandWBean(localPackageInfo.applicationInfo.loadLabel(localPackageManager).toString(), localPackageInfo.versionName, localPackageInfo.packageName));
        }
      }
    }
    return new Gson().toJson(localArrayList);
  }
  
  public static List<AppInfoBean> getInstalledAppList()
  {
    ArrayList localArrayList = new ArrayList();
    PackageManager localPackageManager = App.get().getPackageManager();
    Iterator localIterator = localPackageManager.getInstalledPackages(0).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if ((localPackageInfo.applicationInfo.flags & 0x1) == 0)
      {
        String str = localPackageInfo.packageName;
        if (isMediaBabybusApp(str))
        {
          AppInfoBean localAppInfoBean = new AppInfoBean();
          localAppInfoBean.setPackageName(str);
          localAppInfoBean.setName(localPackageInfo.applicationInfo.loadLabel(localPackageManager).toString());
          localAppInfoBean.setVersion(localPackageInfo.versionName);
          localAppInfoBean.setVersionCode(localPackageInfo.versionCode);
          localAppInfoBean.setApplicationInfo(localPackageInfo.applicationInfo);
          localAppInfoBean.setIcon(localPackageManager.getApplicationIcon(localPackageInfo.applicationInfo));
          localArrayList.add(localAppInfoBean);
        }
      }
    }
    return localArrayList;
  }
  
  public static List<AppInfoBean> getInstalledAppListWithOutLianyong()
  {
    ArrayList localArrayList = new ArrayList();
    PackageManager localPackageManager = App.get().getPackageManager();
    Iterator localIterator = localPackageManager.getInstalledPackages(0).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if ((localPackageInfo.applicationInfo.flags & 0x1) == 0)
      {
        String str = localPackageInfo.packageName;
        if (str.startsWith("com.sinyee"))
        {
          AppInfoBean localAppInfoBean = new AppInfoBean();
          localAppInfoBean.setPackageName(str);
          localAppInfoBean.setName(localPackageInfo.applicationInfo.loadLabel(localPackageManager).toString());
          localAppInfoBean.setVersion(localPackageInfo.versionName);
          localAppInfoBean.setVersionCode(localPackageInfo.versionCode);
          localAppInfoBean.setApplicationInfo(localPackageInfo.applicationInfo);
          localAppInfoBean.setIcon(localPackageManager.getApplicationIcon(localPackageInfo.applicationInfo));
          localArrayList.add(localAppInfoBean);
        }
      }
    }
    return localArrayList;
  }
  
  public static List<String> getInstalledBabyBusAppPackageName()
  {
    try
    {
      ArrayList localArrayList = new ArrayList();
      Object localObject = App.get().getPackageManager();
      int i = 0;
      localObject = ((PackageManager)localObject).getInstalledPackages(0);
      int j = ((List)localObject).size();
      while (i < j)
      {
        String str = ((PackageInfo)((List)localObject).get(i)).packageName;
        if (isMediaBabybusApp(str)) {
          localArrayList.add(str);
        }
        i += 1;
      }
      return localArrayList;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }
  
  private static long getPackageFirstInstallTime(Context paramContext)
  {
    String str = paramContext.getPackageName();
    try
    {
      long l = paramContext.getPackageManager().getPackageInfo(str, 0).firstInstallTime;
      return l;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0L;
  }
  
  private static long getPackageLastUpdateTime(Context paramContext)
  {
    String str = paramContext.getPackageName();
    try
    {
      long l = paramContext.getPackageManager().getPackageInfo(str, 0).lastUpdateTime;
      return l;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0L;
  }
  
  public static String getPlatform()
  {
    if (isInternationalApp()) {
      return "3";
    }
    return "2";
  }
  
  public static List<String> getUninstalledAppList()
  {
    ArrayList localArrayList = new ArrayList();
    if (PermissionUtil.hasPermission("android.permission.WRITE_EXTERNAL_STORAGE"))
    {
      File[] arrayOfFile = new File(C.Path.APK_PATH).listFiles();
      if ((arrayOfFile != null) && (arrayOfFile.length > 0))
      {
        int j = arrayOfFile.length;
        int i = 0;
        while (i < j)
        {
          File localFile = arrayOfFile[i];
          if ((localFile != null) && (apkIsComplete(localFile.getPath()))) {
            localArrayList.add(StringUtil.getFileNameWithOutExtension(localFile.getPath()));
          }
          i += 1;
        }
      }
    }
    return localArrayList;
  }
  
  public static int getVersionCodeUtil(String paramString)
  {
    try
    {
      int i = App.get().getPackageManager().getPackageInfo(paramString, 0).versionCode;
      return i;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return 0;
  }
  
  public static String installApk(String paramString)
  {
    Object localObject;
    if ("com.sinyee.babybus.wonderland".equals(App.get().getPackageName()))
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(C.Path.WONDERLAND_APK_PATH);
      ((StringBuilder)localObject).append("/");
      ((StringBuilder)localObject).append(paramString);
      ((StringBuilder)localObject).append(".apk");
      localObject = ((StringBuilder)localObject).toString();
      InstallUtil.get().installApkWithPath((String)localObject, new InstallInfo(paramString));
      paramString = (String)localObject;
    }
    else
    {
      localObject = new InstallInfo(paramString);
      if (!App.writeSDCard)
      {
        paramString = new StringBuilder();
        paramString.append(App.get().getExternalFilesDir("apks"));
        paramString.append("/");
        paramString.append(((InstallInfo)localObject).getPackageName());
        paramString.append(".apk");
        paramString = paramString.toString();
        InstallUtil.get().installPrivateDirApk((InstallInfo)localObject);
      }
      else
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(C.Path.WONDERLAND_APK_PATH);
        localStringBuilder.append("/");
        localStringBuilder.append(paramString);
        localStringBuilder.append(".apk");
        paramString = localStringBuilder.toString();
        InstallUtil.get().installPublicDirApk((InstallInfo)localObject);
      }
    }
    paramString = new File(paramString);
    try
    {
      long l = BBFileUtil.fileSize(paramString);
      paramString = new DecimalFormat("#.00").format(l * 1.0D / 1000.0D / 1000.0D);
      return paramString;
    }
    catch (IOException paramString)
    {
      paramString.printStackTrace();
    }
    return "-1";
  }
  
  public static void installApkInSDCard(String paramString)
  {
    installApkWithInfo(paramString, "");
  }
  
  public static void installApkWithInfo(String paramString1, String paramString2)
  {
    if (PermissionUtil.hasPermission("android.permission.WRITE_EXTERNAL_STORAGE"))
    {
      ADUtil.sendUM4Install(paramString2);
      ApkDownloadManager.get().installPublicApk(paramString1, paramString2);
      return;
    }
    ApkDownloadManager.get().installPrivateApk(paramString1, paramString2);
  }
  
  public static void installApp(File paramFile)
  {
    if (ShellCmdBuilder.isCmdStartActivity())
    {
      ShellCmdBuilder.get().setAction("android.intent.action.VIEW").addFlags(268435456).setDataUri(Uri.fromFile(paramFile).toString()).setMimeType("application/vnd.android.package-archive").execShellCmd();
      return;
    }
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setFlags(268435456);
    localIntent.setDataAndType(Uri.fromFile(paramFile), "application/vnd.android.package-archive");
    App.get().startActivity(localIntent);
  }
  
  public static boolean isApkInDebug(Context paramContext)
  {
    boolean bool = false;
    try
    {
      int i = paramContext.getApplicationInfo().flags;
      if ((i & 0x2) != 0) {
        bool = true;
      }
      return bool;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  @Deprecated
  public static boolean isAppInstalled(String paramString)
  {
    if ((paramString != null) && (!"".equals(paramString)))
    {
      List localList = App.get().getPackageManager().getInstalledPackages(0);
      int i = 0;
      while (i < localList.size())
      {
        if (paramString.endsWith(((PackageInfo)localList.get(i)).packageName)) {
          return true;
        }
        i += 1;
      }
    }
    return false;
  }
  
  public static boolean isBabybusPackage(String paramString)
  {
    return (!TextUtils.isEmpty(paramString)) && (isMediaBabybusApp(paramString));
  }
  
  public static boolean isDomesticChannelInternationalApp()
  {
    return (isXiaomiInternationalApp()) || (isHuaweiInternationalApp()) || (isSamsungInternationalApp()) || (isOppoInternationalApp());
  }
  
  public static boolean isDownloaded(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(C.Path.APK_PATH);
    localStringBuilder.append("/");
    localStringBuilder.append(paramString);
    localStringBuilder.append(".apk");
    paramString = localStringBuilder.toString();
    return (BBFileUtil.checkFile(paramString)) && (apkIsComplete(paramString));
  }
  
  public static Map<String, Boolean> isDownloadeds(List<String> paramList)
  {
    HashMap localHashMap = new HashMap();
    if (paramList == null) {
      return localHashMap;
    }
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      String str = (String)paramList.next();
      localHashMap.put(str, Boolean.valueOf(isDownloaded(str)));
    }
    return localHashMap;
  }
  
  public static boolean isFirstInstall()
  {
    return getPackageFirstInstallTime(App.get()) == getPackageLastUpdateTime(App.get());
  }
  
  public static boolean isHuaweiInternationalApp()
  {
    return isInternationalApp("A023");
  }
  
  public static boolean isInstalled(String paramString)
  {
    PackageManager localPackageManager = App.get().getPackageManager();
    try
    {
      localPackageManager.getPackageInfo(paramString, 0);
      return true;
    }
    catch (Exception paramString) {}
    return false;
  }
  
  public static boolean isInstalledWeiXin()
  {
    return isAppInstalled("com.tencent.mm");
  }
  
  public static Map<String, Boolean> isInstalleds(List<String> paramList)
  {
    HashMap localHashMap = new HashMap();
    if (paramList == null) {
      return localHashMap;
    }
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      String str = (String)paramList.next();
      localHashMap.put(str, Boolean.valueOf(isInstalled(str)));
    }
    return localHashMap;
  }
  
  public static boolean isInternationalApp()
  {
    return ("A005".equals(App.get().channel)) || ("A030".equals(App.get().channel));
  }
  
  private static boolean isInternationalApp(String paramString)
  {
    return (paramString.equals(App.get().channel)) && (!"zh".equals(UIUtil.getLanguage()));
  }
  
  public static Boolean isLY()
  {
    return Boolean.valueOf(App.getMetaData().getBoolean("IS_LY", false));
  }
  
  private static boolean isMediaBabybusApp(String paramString)
  {
    return (paramString.contains("com.sinyee")) || (paramString.contains("com.kid58.lianyong")) || (paramString.contains("com.yw"));
  }
  
  public static boolean isOppoInternationalApp()
  {
    return isInternationalApp("A016");
  }
  
  public static boolean isRecommendApp(String paramString)
  {
    return "com.sinyee.babybus.recommendapp".equals(paramString);
  }
  
  public static boolean isRunBabyApp()
  {
    Object localObject = queryAllRunningAppInfo().iterator();
    while (((Iterator)localObject).hasNext())
    {
      String str = (String)((Iterator)localObject).next();
      if (str.startsWith("com.sinyee"))
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("isRunBabyApp");
        localStringBuilder.append(str);
        LogUtil.e(localStringBuilder.toString());
        if ((!TextUtils.equals(str, "com.sinyee.babybus.recommendapp")) && (!TextUtils.equals(str, "com.sinyee.babybus.wonderland")) && (!TextUtils.equals(str, "com.sinyee.babybus.bbtime.android")) && (!TextUtils.equals(str, "com.sinyee.babybus.recommendapp")) && (!TextUtils.equals(str, "com.sinyee.babybus.chants")) && (!TextUtils.equals(str, "com.sinyee.babybus.talk2kiki")) && (!TextUtils.equals(str, App.get().packName)))
        {
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("isRunBabyApp gl ");
          ((StringBuilder)localObject).append(str);
          LogUtil.e(((StringBuilder)localObject).toString());
          return false;
        }
      }
    }
    return true;
  }
  
  public static boolean isSamsungInternationalApp()
  {
    return isInternationalApp("A022");
  }
  
  public static boolean isXiaomiInternationalApp()
  {
    return isInternationalApp("A004");
  }
  
  public static void launchApp(String paramString, boolean paramBoolean)
  {
    try
    {
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("launchApp:");
      ((StringBuilder)localObject).append(paramString);
      ((StringBuilder)localObject).append("--");
      ((StringBuilder)localObject).append(paramBoolean);
      Log.e("Test", ((StringBuilder)localObject).toString());
      localObject = App.get().getPackageManager().getLaunchIntentForPackage(paramString);
      if (ShellCmdBuilder.isCmdStartActivity())
      {
        localObject = ((Intent)localObject).getComponent();
        ShellCmdBuilder.get().addFlags(268435456).setComponent(paramString, ((ComponentName)localObject).getClassName()).execShellCmd();
      }
      else
      {
        ((Intent)localObject).setFlags(268435456);
        App.get().startActivity((Intent)localObject);
      }
      if (!paramBoolean) {
        break label122;
      }
      App.get().exit();
      return;
    }
    catch (Exception paramString)
    {
      label122:
      for (;;) {}
    }
    LogUtil.t("launchApp error");
  }
  
  public static void launchApp4Recommend(String paramString)
  {
    if (ShellCmdBuilder.isCmdStartActivity())
    {
      ShellCmdBuilder.get().setAction("android.intent.action.MAIN").addFlags(268435456).setComponent("com.sinyee.babybus.recommendapp", "com.sinyee.babybus.recommendapp.Main").execShellCmd();
      return;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("num ===");
    localStringBuilder.append(paramString);
    Log.e("babybus", localStringBuilder.toString());
    paramString = new Intent("android.intent.action.MAIN");
    paramString.setComponent(new ComponentName("com.sinyee.babybus.recommendapp", "com.sinyee.babybus.recommendapp.Main"));
    App.get().mainActivity.startActivity(paramString);
  }
  
  public static void launchApp4wonderland(String paramString, boolean paramBoolean)
  {
    Object localObject = App.get().getPackageManager().getLaunchIntentForPackage(paramString);
    if ("com.sinyee.babybus.wonderland".equals(App.get().getPackageName())) {
      KeyChainUtil.get().writeRealTime("wonderland", paramString);
    }
    if (ShellCmdBuilder.isCmdStartActivity())
    {
      localObject = ((Intent)localObject).getComponent();
      ShellCmdBuilder.get().addFlags(268435456).setComponent(paramString, ((ComponentName)localObject).getClassName()).execShellCmd();
    }
    else
    {
      App.get().startActivity((Intent)localObject);
    }
    if (paramBoolean) {
      App.get().exit();
    }
  }
  
  public static void launchSubPackage(String paramString)
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("ApkUtil launchSubPackage:");
    ((StringBuilder)localObject).append(paramString);
    Log.e("Test", ((StringBuilder)localObject).toString());
    localObject = App.get().mainActivity.getPackageManager().getLaunchIntentForPackage(paramString);
    if (ShellCmdBuilder.isCmdStartActivity())
    {
      localObject = ((Intent)localObject).getComponent();
      ShellCmdBuilder.get().addFlags(268435456).setComponent(paramString, ((ComponentName)localObject).getClassName()).addPram("IS_FROM_WONDERLAND", Boolean.valueOf(true)).execShellCmd();
      return;
    }
    ((Intent)localObject).setFlags(268435456);
    ((Intent)localObject).putExtra("IS_FROM_WONDERLAND", true);
    App.get().mainActivity.startActivity((Intent)localObject);
  }
  
  public static void openBrowser(String paramString, int paramInt)
  {
    if ((paramString != null) && (!"".equals(paramString)))
    {
      Intent localIntent = new Intent();
      localIntent.setAction("android.intent.action.VIEW");
      localIntent.addFlags(268435456);
      localIntent.setData(Uri.parse(paramString));
      App.get().getCurrentAct().startActivityForResult(localIntent, paramInt);
    }
  }
  
  private static List<String> queryAllRunningAppInfo()
  {
    ArrayList localArrayList = new ArrayList();
    try
    {
      Object localObject1 = App.get().getPackageManager().getInstalledApplications(8192);
      HashMap localHashMap = new HashMap();
      Object localObject2 = ((ActivityManager)App.get().getSystemService("activity")).getRunningAppProcesses().iterator();
      while (((Iterator)localObject2).hasNext())
      {
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject2).next();
        String[] arrayOfString = localRunningAppProcessInfo.pkgList;
        int i = 0;
        while (i < arrayOfString.length)
        {
          localHashMap.put(arrayOfString[i], localRunningAppProcessInfo);
          i += 1;
        }
      }
      localObject1 = ((List)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (ApplicationInfo)((Iterator)localObject1).next();
        if (localHashMap.containsKey(((ApplicationInfo)localObject2).packageName)) {
          localArrayList.add(((ApplicationInfo)localObject2).packageName);
        }
      }
      return localArrayList;
    }
    catch (Exception localException) {}
    return localArrayList;
  }
  
  public static void refreshInstalledAppInfo()
  {
    Gson localGson = new Gson();
    ArrayList localArrayList = new ArrayList();
    PackageManager localPackageManager = App.get().getPackageManager();
    Iterator localIterator = localPackageManager.getInstalledPackages(0).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if ((localPackageInfo.applicationInfo.flags & 0x1) == 0) {
        localArrayList.add(new AppInfoForWonderlandWBean(localPackageInfo.applicationInfo.loadLabel(localPackageManager).toString(), localPackageInfo.versionName, localPackageInfo.packageName));
      }
    }
    SpUtil.putString("INSTALL_APP_INFO", localGson.toJson(localArrayList));
  }
  
  public static void showContinueCancelDialog(Context paramContext, final int paramInt, final String paramString1, final String paramString2, final DialogInterface.OnClickListener paramOnClickListener1, final DialogInterface.OnClickListener paramOnClickListener2)
  {
    UIUtil.runOnUiThread(new Runnable()
    {
      public void run()
      {
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(this.val$context);
        localBuilder.setCancelable(true);
        localBuilder.setIcon(paramInt);
        localBuilder.setTitle(paramString1);
        localBuilder.setMessage(paramString2);
        localBuilder.setInverseBackgroundForced(true);
        localBuilder.setPositiveButton("Continue", paramOnClickListener1);
        localBuilder.setNegativeButton("Cancel", paramOnClickListener2);
        localBuilder.create().show();
      }
    });
  }
  
  public static void uninstallApp(String paramString)
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("package:");
    ((StringBuilder)localObject).append(paramString);
    localObject = new Intent("android.intent.action.DELETE", Uri.parse(((StringBuilder)localObject).toString()));
    ((Intent)localObject).addFlags(536870912);
    App.get().uninstallApk = paramString;
    App.get().mainActivity.startActivityForResult((Intent)localObject, 8402);
  }
}
