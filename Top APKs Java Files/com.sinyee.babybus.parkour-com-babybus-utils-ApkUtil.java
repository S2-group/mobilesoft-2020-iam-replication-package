package com.babybus.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build.VERSION;
import android.widget.Toast;
import com.babybus.app.App;
import java.io.File;
import java.util.Iterator;
import java.util.List;

public class ApkUtil
{
  public ApkUtil() {}
  
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
  
  public static boolean hasAnyMarketInstalled()
  {
    Intent localIntent = new Intent();
    localIntent.setData(Uri.parse("market://details?id=android.browser"));
    return App.get().getPackageManager().queryIntentActivities(localIntent, 65536).size() != 0;
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
    boolean bool2 = false;
    boolean bool1 = bool2;
    List localList;
    int i;
    if (paramString != null)
    {
      bool1 = bool2;
      if (!"".equals(paramString))
      {
        localList = App.get().getPackageManager().getInstalledPackages(0);
        i = 0;
      }
    }
    for (;;)
    {
      bool1 = bool2;
      if (i < localList.size())
      {
        if (paramString.endsWith(((PackageInfo)localList.get(i)).packageName)) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
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
    paramString = App.get().getPackageManager().getLaunchIntentForPackage(paramString);
    paramString.setFlags(268435456);
    App.get().startActivity(paramString);
    if (paramBoolean) {
      App.get().exit();
    }
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
}
