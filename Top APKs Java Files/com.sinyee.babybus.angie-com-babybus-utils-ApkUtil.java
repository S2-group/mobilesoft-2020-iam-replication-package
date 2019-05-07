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
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.babybus.app.App;
import com.babybus.app.Const;
import com.babybus.bean.ApkInfoBean;
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
    if (!TextUtils.isEmpty(paramString))
    {
      LogUtil.e("path ==== " + paramString.substring(paramString.lastIndexOf("/") + 1, paramString.lastIndexOf(".")));
      return paramString.substring(paramString.lastIndexOf("/") + 1, paramString.lastIndexOf("."));
    }
    return "";
  }
  
  public static int getApkVersionCode(String paramString)
  {
    int j = 0;
    Iterator localIterator = App.get().getPackageManager().getInstalledPackages(0).iterator();
    PackageInfo localPackageInfo;
    do
    {
      i = j;
      if (!localIterator.hasNext()) {
        break;
      }
      localPackageInfo = (PackageInfo)localIterator.next();
    } while (!paramString.equals(localPackageInfo.packageName));
    int i = localPackageInfo.versionCode;
    return i;
  }
  
  public static String getInstalledAppInfo()
  {
    Gson localGson = new Gson();
    ArrayList localArrayList = new ArrayList();
    PackageManager localPackageManager = App.get().getPackageManager();
    Iterator localIterator = localPackageManager.getInstalledPackages(0).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if ((localPackageInfo.applicationInfo.flags & 0x1) == 0) {
        localArrayList.add(new ApkInfoBean(localPackageInfo.applicationInfo.loadLabel(localPackageManager).toString(), localPackageInfo.versionName, localPackageInfo.packageName));
      }
    }
    return localGson.toJson(localArrayList);
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
      BBSuperDownloadUtil.get().install(App.get(), str, "8_selfad");
      break;
      if (App.get().u3d) {
        str = App.get().getExternalFilesDir("apks") + "/" + paramString + ".apk";
      }
    }
    return "-1";
  }
  
  public static void installApp(File paramFile)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setFlags(268435456);
    localIntent.setDataAndType(Uri.fromFile(paramFile), "application/vnd.android.package-archive");
    App.get().startActivity(localIntent);
  }
  
  public static boolean isAppInstalled(String paramString)
  {
    return isInstalled(paramString);
  }
  
  public static boolean isInstalled(String paramString)
  {
    PackageManager localPackageManager = App.get().getPackageManager();
    try
    {
      localPackageManager.getPackageInfo(paramString, 1);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return false;
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
      App.get().mainActivity.startActivityForResult(localIntent, paramInt);
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
        localArrayList.add(new ApkInfoBean(localPackageInfo.applicationInfo.loadLabel(localPackageManager).toString(), localPackageInfo.versionName, localPackageInfo.packageName));
      }
    }
    SpUtil.putString("INSTALL_APP_INFO", localGson.toJson(localArrayList));
  }
  
  public static void showContinueCancelDialog(Context paramContext, int paramInt, String paramString1, String paramString2, DialogInterface.OnClickListener paramOnClickListener1, DialogInterface.OnClickListener paramOnClickListener2)
  {
    paramContext = new AlertDialog.Builder(App.get());
    paramContext.setCancelable(true);
    paramContext.setIcon(paramInt);
    paramContext.setTitle(paramString1);
    paramContext.setMessage(paramString2);
    paramContext.setInverseBackgroundForced(true);
    paramContext.setPositiveButton("Continue", paramOnClickListener1);
    paramContext.setNegativeButton("Cancel", paramOnClickListener2);
    paramContext.create().show();
  }
  
  public static void uninstallApp(String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.DELETE", Uri.parse(32 + "package:" + paramString));
    localIntent.addFlags(536870912);
    App.get().unInstallApk = paramString;
    App.get().mainActivity.startActivityForResult(localIntent, 10);
  }
}
