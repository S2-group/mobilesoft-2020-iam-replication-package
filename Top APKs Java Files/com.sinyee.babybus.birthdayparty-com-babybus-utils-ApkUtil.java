package com.babybus.utils;

import android.app.Activity;
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
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.babybus.app.App;
import com.babybus.app.Const;
import com.babybus.bean.AppInfoBean;
import com.babybus.bean.AppInfoForWonderlandWBean;
import com.babybus.umeng.UmengUtil;
import com.babybus.utils.downloadutils.BBSuperDownloadUtil;
import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ApkUtil
{
  public ApkUtil() {}
  
  public static boolean apkIsComplete(String paramString)
  {
    return App.get().getPackageManager().getPackageArchiveInfo(paramString, 1) != null;
  }
  
  public static void checkApkCount()
  {
    int j = 0;
    List localList = App.get().getPackageManager().getInstalledPackages(0);
    int i = 0;
    while (i < localList.size())
    {
      int k = j;
      if (((PackageInfo)localList.get(i)).packageName.contains("com.sinyee.babybus")) {
        k = j + 1;
      }
      i += 1;
      j = k;
    }
    UmengUtil.sendEvent4BabybusApkCount(j);
  }
  
  public static boolean clickLock(long paramLong)
  {
    return Math.abs(paramLong - App.get().lastTime) < 1000L;
  }
  
  public static void dlRecommandApp()
  {
    LogUtil.e("installRecommand===");
    MarketUtil.openLink("http://openbox.mobilem.360.cn/index/d/sid/3028526", "com.sinyee.babybus.recommendapp", "宝宝巴士大全", "", Integer.valueOf(1));
  }
  
  public static boolean downloadManagerIsEnabled()
  {
    int i;
    do
    {
      try
      {
        i = App.get().getPackageManager().getApplicationEnabledSetting("com.android.providers.downloads");
        if (Build.VERSION.SDK_INT <= 18) {
          continue;
        }
        if ((i != 2) && (i != 3) && (i != 4)) {
          return true;
        }
      }
      catch (Exception localException)
      {
        LogUtil.d("com.android.providers.downloads is no found");
        return false;
      }
      return false;
    } while ((i != 2) && (i != 3));
    return false;
  }
  
  public static String getApkKey(String paramString)
  {
    if (!TextUtils.isEmpty(paramString)) {
      return paramString.substring(paramString.lastIndexOf("/") + 1, paramString.lastIndexOf("."));
    }
    return "";
  }
  
  public static int getApkVersionCode(String paramString)
  {
    Iterator localIterator = App.get().getPackageManager().getInstalledPackages(0).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if (paramString.equals(localPackageInfo.packageName)) {
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
  
  public static ApplicationInfo getApplicationInfoWithAppKey(String paramString)
  {
    Iterator localIterator = App.get().getPackageManager().getInstalledPackages(0).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if (((localPackageInfo.applicationInfo.flags & 0x1) == 0) && (localPackageInfo.packageName.equals(paramString))) {
        return localPackageInfo.applicationInfo;
      }
    }
    return null;
  }
  
  public static ApplicationInfo getApplicationInfoWithPath(String paramString)
  {
    Object localObject = App.get().getPackageManager().getPackageArchiveInfo(paramString, 1);
    if (localObject != null)
    {
      localObject = ((PackageInfo)localObject).applicationInfo;
      ((ApplicationInfo)localObject).sourceDir = paramString;
      ((ApplicationInfo)localObject).publicSourceDir = paramString;
      return localObject;
    }
    return null;
  }
  
  public static Drawable getDrawableWithAppInfo(ApplicationInfo paramApplicationInfo)
  {
    if (paramApplicationInfo != null) {
      try
      {
        paramApplicationInfo = App.get().getPackageManager().getApplicationIcon(paramApplicationInfo);
        return paramApplicationInfo;
      }
      catch (Exception paramApplicationInfo)
      {
        paramApplicationInfo.printStackTrace();
      }
    }
    return null;
  }
  
  public static Drawable getInstallApp(String paramString)
  {
    PackageManager localPackageManager = App.get().getPackageManager();
    Iterator localIterator = localPackageManager.getInstalledPackages(0).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if (((localPackageInfo.applicationInfo.flags & 0x1) == 0) && (localPackageInfo.packageName.startsWith(paramString))) {
        return localPackageManager.getApplicationIcon(localPackageInfo.applicationInfo);
      }
    }
    return null;
  }
  
  public static String getInstalledAppInfo()
  {
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
  
  public static boolean hasAnyMarketInstalled()
  {
    Intent localIntent = new Intent();
    localIntent.setData(Uri.parse("market://details?id=android.browser"));
    return App.get().getPackageManager().queryIntentActivities(localIntent, 65536).size() != 0;
  }
  
  public static String installApk(String paramString)
  {
    String str = null;
    if ("com.sinyee.babybus.wonderland".equals(App.get().getPackageName()))
    {
      str = Const.WONDERLAND_APK_PATH + "/" + paramString + ".apk";
      BBSuperDownloadUtil.get().install(App.get(), str);
      paramString = new File(str);
    }
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
    if (App.writeSDCard) {
      str = Const.APK_PATH + "/" + paramString + ".apk";
    }
    for (;;)
    {
      LogUtil.e("installApk apkName = " + paramString);
      BBSuperDownloadUtil.get().install(App.get(), str, "8|selfad|" + paramString);
      break;
      if (App.get().u3d) {
        str = App.get().getExternalFilesDir("apks") + "/" + paramString + ".apk";
      }
    }
    return "-1";
  }
  
  public static void installApkInApks(String paramString1, String paramString2)
  {
    if (App.writeSDCard)
    {
      paramString1 = Const.APK_PATH + "/" + paramString1 + ".apk";
      ADUtil.sendUM4Install(paramString2);
      BBSuperDownloadUtil.get().install(App.get(), paramString1, paramString2);
    }
  }
  
  public static void installApp(File paramFile)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setFlags(268435456);
    localIntent.setDataAndType(Uri.fromFile(paramFile), "application/vnd.android.package-archive");
    App.get().startActivity(localIntent);
  }
  
  @Deprecated
  public static boolean isAppInstalled(String paramString)
  {
    return isInstalled(paramString);
  }
  
  public static boolean isDownloaded(String paramString)
  {
    paramString = Const.APK_PATH + "/" + paramString + ".apk";
    return (BBFileUtil.checkFile(paramString)) && (apkIsComplete(paramString));
  }
  
  public static boolean isInstalled(String paramString)
  {
    PackageManager localPackageManager = App.get().getPackageManager();
    try
    {
      localPackageManager.getPackageInfo(paramString, 1);
      return true;
    }
    catch (Exception paramString) {}
    return false;
  }
  
  public static boolean isInstalledAlipay()
  {
    return new Intent("android.intent.action.VIEW", Uri.parse("alipays://platformapi/startApp")).resolveActivity(App.get().getPackageManager()) != null;
  }
  
  public static boolean isInstalledWeiXin()
  {
    return isInstalled("com.tencent.mm");
  }
  
  public static boolean isInternationalApp()
  {
    return ("A005".equals(App.get().channel)) || ("A030".equals(App.get().channel));
  }
  
  public static boolean isRecommendApp(String paramString)
  {
    return "com.sinyee.babybus.recommendapp".equals(paramString);
  }
  
  public static boolean isWonderland()
  {
    return "com.sinyee.babybus.wonderland".equals(App.get().getPackageName());
  }
  
  public static void launchApp(String paramString, boolean paramBoolean)
  {
    Log.e("Test", "launchApp:" + paramString + "--" + paramBoolean);
    paramString = App.get().getPackageManager().getLaunchIntentForPackage(paramString);
    paramString.setFlags(268435456);
    App.get().startActivity(paramString);
    if (paramBoolean) {
      App.get().exit();
    }
  }
  
  public static void launchApp4Recommend(String paramString)
  {
    Log.e("babybus", "num ===" + paramString);
    paramString = new Intent("android.intent.action.MAIN");
    paramString.setComponent(new ComponentName("com.sinyee.babybus.recommendapp", "com.sinyee.babybus.recommendapp.Main"));
    App.get().mainActivity.startActivity(paramString);
  }
  
  public static void launchApp4wonderland(String paramString, boolean paramBoolean)
  {
    Intent localIntent = App.get().getPackageManager().getLaunchIntentForPackage(paramString);
    if ("com.sinyee.babybus.wonderland".equals(App.get().getPackageName())) {
      KeyChainUtil.get().writeRealTime("wonderland", paramString);
    }
    App.get().startActivity(localIntent);
    if (paramBoolean) {
      App.get().exit();
    }
  }
  
  public static void launchSubPackage(String paramString)
  {
    Log.e("Test", "ApkUtil launchSubPackage:" + paramString);
    paramString = App.get().mainActivity.getPackageManager().getLaunchIntentForPackage(paramString);
    paramString.setFlags(268435456);
    paramString.putExtra("IS_FROM_WONDERLAND", true);
    App.get().mainActivity.startActivity(paramString);
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
  
  public static void openMarketToDownload(final Activity paramActivity, String paramString)
  {
    try
    {
      paramActivity.runOnUiThread(new Runnable()
      {
        public void run()
        {
          Object localObject = Uri.parse("market://details?id=" + this.val$appKey.trim());
          if (ApkUtil.hasAnyMarketInstalled())
          {
            localObject = new Intent("android.intent.action.VIEW", (Uri)localObject);
            ((Intent)localObject).setFlags(268435456);
            paramActivity.startActivity((Intent)localObject);
            return;
          }
          try
          {
            Intent localIntent = new Intent();
            localIntent.setAction("android.intent.action.VIEW");
            localIntent.setFlags(268435456);
            localIntent.setData((Uri)localObject);
            paramActivity.startActivity(localIntent);
            return;
          }
          catch (Exception localException)
          {
            Toast.makeText(paramActivity, "Browser does not open.", 0).show();
          }
        }
      });
      return;
    }
    catch (Exception paramActivity)
    {
      paramActivity.printStackTrace();
    }
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
    Intent localIntent = new Intent("android.intent.action.DELETE", Uri.parse("package:" + paramString));
    localIntent.addFlags(536870912);
    App.get().uninstallApk = paramString;
    App.get().mainActivity.startActivityForResult(localIntent, 8402);
  }
}
