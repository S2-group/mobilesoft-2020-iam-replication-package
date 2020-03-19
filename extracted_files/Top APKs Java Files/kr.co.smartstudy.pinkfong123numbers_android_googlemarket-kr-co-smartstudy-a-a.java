package kr.co.smartstudy.a;

import android.app.Application;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import java.util.HashSet;
import java.util.List;
import kr.co.smartstudy.sspatcher.j;
import kr.co.smartstudy.sspatcher.j.a;

public class a
{
  public static boolean a = false;
  public static Handler b = new Handler();
  
  public static String a(String paramString1, String paramString2)
  {
    paramString1 = paramString1.trim().toLowerCase();
    boolean bool = a().contains(paramString1);
    if (bool) {
      return paramString1;
    }
    if ((!bool) && (!c.a(paramString2)))
    {
      paramString1 = new Intent("android.intent.action.VIEW", Uri.parse(paramString2));
      paramString1 = c.a().getPackageManager().queryIntentActivities(paramString1, 0);
      if (!paramString1.isEmpty()) {
        return ((ResolveInfo)paramString1.get(0)).activityInfo.packageName;
      }
    }
    return "";
  }
  
  public static HashSet<String> a()
  {
    HashSet localHashSet = new HashSet();
    List localList = c.a().getPackageManager().getInstalledPackages(0);
    int i = 0;
    while (i < localList.size())
    {
      PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
      String str1 = localPackageInfo.packageName.toLowerCase();
      String str2 = localPackageInfo.versionName;
      int j = localPackageInfo.versionCode;
      localHashSet.add(str1);
      i += 1;
    }
    return localHashSet;
  }
  
  public static void a(boolean paramBoolean)
  {
    a = paramBoolean;
  }
  
  private static boolean a(int paramInt, final String paramString, final j.a paramA)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    paramString = new Runnable()
    {
      public void run()
      {
        j.a(c.a(), this.a, paramString, false, true, paramA);
      }
    };
    if (a) {
      paramString.run();
    }
    for (;;)
    {
      return true;
      b.postDelayed(paramString, 500L);
    }
  }
  
  public static boolean a(String paramString)
  {
    return c(paramString, null);
  }
  
  public static boolean a(String paramString1, String paramString2, j.a paramA)
  {
    if (TextUtils.isEmpty(paramString2)) {
      return false;
    }
    return a(j.a(paramString1.toLowerCase().trim()), paramString2, paramA);
  }
  
  public static boolean b(String paramString)
  {
    try
    {
      paramString = c.a().getPackageManager().getLaunchIntentForPackage(paramString);
      paramString.addCategory("android.intent.category.LAUNCHER");
      paramString.setAction("android.intent.action.MAIN");
      paramString.addFlags(805306368);
      c.a().startActivity(paramString);
      return true;
    }
    catch (Exception paramString)
    {
      Log.e("SSAppLaunch", "", paramString);
    }
    return false;
  }
  
  public static boolean b(String paramString1, String paramString2)
  {
    return !a(paramString1, paramString2).isEmpty();
  }
  
  public static boolean c(String paramString1, String paramString2)
  {
    boolean bool = false;
    try
    {
      c.a().getPackageManager();
      paramString1 = new Intent("android.intent.action.VIEW", Uri.parse(paramString1));
      if (!TextUtils.isEmpty(paramString2)) {
        paramString1.setPackage(paramString2);
      }
      if (!c.a().getPackageManager().queryIntentActivities(paramString1, 0).isEmpty())
      {
        paramString1.addFlags(872415232);
        c.a().startActivity(paramString1);
        bool = true;
      }
      return bool;
    }
    catch (Exception paramString1)
    {
      Log.e("SSAppLaunch", "", paramString1);
    }
    return false;
  }
}
