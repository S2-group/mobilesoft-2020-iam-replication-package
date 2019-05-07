package com.sinyee.babybus.box.bo;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;
import com.sinyee.babybus.box.BoxConst;
import com.wiyun.engine.nodes.Director;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;

public class AppMarketBo
{
  public AppMarketBo() {}
  
  public static boolean checkInstallApp(String paramString)
  {
    Context localContext = Director.getInstance().getContext();
    Object localObject = null;
    try
    {
      paramString = localContext.getPackageManager().getPackageInfo(paramString, 0);
      if (paramString == null) {
        return false;
      }
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
        paramString = localObject;
      }
    }
    return true;
  }
  
  private static String getInfoByLanguage()
  {
    if (BoxConst.language.equals("zh")) {
      return "请先打开乐商店。";
    }
    if (BoxConst.language.equals("ja")) {
      return "レノボショップを開いてください。";
    }
    return "Please open Lenovo Market.";
  }
  
  public static boolean hasInstalledAmazonMarket()
  {
    List localList = Director.getInstance().getContext().getPackageManager().getInstalledPackages(0);
    int i;
    if (localList != null) {
      i = 0;
    }
    for (;;)
    {
      if (i >= localList.size()) {
        return false;
      }
      if ("com.amazon.venezia".equals(((PackageInfo)localList.get(i)).packageName)) {
        return true;
      }
      i += 1;
    }
  }
  
  public static void launchAndroidMarket()
  {
    Activity localActivity = (Activity)Director.getInstance().getContext();
    Object localObject1 = IoBo.PACKAGE_NAME;
    try
    {
      if ("LENOVO".equalsIgnoreCase(Build.BRAND))
      {
        launchLenovoMarket((String)localObject1);
        return;
      }
      if (!hasInstalledAmazonMarket()) {
        break label96;
      }
      localIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.amazon.com/gp/mas/dl/android?p=" + ((String)localObject1).trim()));
      localObject1 = localIntent;
      try
      {
        localActivity.startActivity(localIntent);
        return;
      }
      catch (Exception localException1) {}
    }
    catch (Exception localException2)
    {
      Intent localIntent;
      Object localObject2;
      for (;;) {}
    }
    localActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://market.android.com/search?q=sinyee")));
    return;
    label96:
    localIntent = new Intent("android.intent.action.VIEW", Uri.parse("market://search?q=sinyee"));
    localObject2 = localIntent;
    localActivity.startActivity(localIntent);
  }
  
  public static void launchAndroidMarket(String paramString)
  {
    Activity localActivity = (Activity)Director.getInstance().getContext();
    if (paramString.startsWith("http"))
    {
      localActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString)));
      return;
    }
    launchAndroidMarket(null, paramString);
  }
  
  public static void launchAndroidMarket(String paramString1, String paramString2)
  {
    Activity localActivity = (Activity)Director.getInstance().getContext();
    try
    {
      if ("LENOVO".equalsIgnoreCase(Build.BRAND))
      {
        launchLenovoMarket(paramString2);
        return;
      }
      if (!hasInstalledAmazonMarket()) {
        break label108;
      }
      localIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.amazon.com/gp/mas/dl/android?p=" + paramString2.trim()));
      paramString2 = localIntent;
      try
      {
        localActivity.startActivity(localIntent);
        return;
      }
      catch (Exception paramString2) {}
    }
    catch (Exception paramString2)
    {
      Intent localIntent;
      for (;;) {}
    }
    if (StringUtils.isNotEmpty(paramString1)) {
      localActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString1)));
    }
    Log.e("babybus", paramString2.toString());
    return;
    label108:
    localIntent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + paramString2.trim()));
    paramString2 = localIntent;
    localActivity.startActivity(localIntent);
  }
  
  public static void launchApp(String paramString)
  {
    Activity localActivity = (Activity)Director.getInstance().getContext();
    Object localObject = new Intent("android.intent.action.MAIN", null);
    ((Intent)localObject).setPackage(paramString);
    localObject = (ResolveInfo)localActivity.getPackageManager().queryIntentActivities((Intent)localObject, 0).iterator().next();
    if (localObject != null)
    {
      paramString = ((ResolveInfo)localObject).activityInfo.packageName;
      localObject = ((ResolveInfo)localObject).activityInfo.name;
      Intent localIntent = new Intent("android.intent.action.MAIN");
      localIntent.addCategory("android.intent.category.LAUNCHER");
      localIntent.setComponent(new ComponentName(paramString, (String)localObject));
      localIntent.putExtra("launchFlag", true);
      localActivity.startActivity(localIntent);
      new CollectDataBo().endCollection();
      localActivity.finish();
      Process.killProcess(Process.myPid());
    }
  }
  
  public static void launchLenovoMarket(String paramString)
  {
    Activity localActivity = (Activity)Director.getInstance().getContext();
    Iterator localIterator = ((ActivityManager)localActivity.getSystemService("activity")).getRunningTasks(500).iterator();
    for (;;)
    {
      int i = 0;
      if (!localIterator.hasNext()) {}
      for (;;)
      {
        if (i == 0) {
          break label130;
        }
        localActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("leapp://ptn/appinfo.do?service=ptn&packagename=" + paramString.trim())));
        return;
        String str = ((ActivityManager.RunningTaskInfo)localIterator.next()).baseActivity.getPackageName();
        if ((!"com.lenovo.leos.appstore.pad".equals(str)) && (!"com.lenovo.leos.appstore".equals(str))) {
          break;
        }
        i = 1;
      }
    }
    label130:
    localActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        Toast.makeText(Director.getInstance().getContext(), AppMarketBo.access$0(), 1).show();
      }
    });
  }
}
