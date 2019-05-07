package kr.co.smartstudy.f;

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
import kr.co.smartstudy.sspatcher.bo;

public class e
{
  public static boolean a = false;
  public static Handler b = new Handler();
  
  public e() {}
  
  public static String a(String paramString1, String paramString2)
  {
    paramString1 = paramString1.trim().toLowerCase();
    boolean bool = a().contains(paramString1);
    if (bool) {
      return paramString1;
    }
    if ((!bool) && (!p.b(paramString2)))
    {
      paramString1 = new Intent("android.intent.action.VIEW", Uri.parse(paramString2));
      paramString1 = p.a().getPackageManager().queryIntentActivities(paramString1, 0);
      if (!paramString1.isEmpty()) {
        return ((ResolveInfo)paramString1.get(0)).activityInfo.packageName;
      }
    }
    return "";
  }
  
  public static HashSet<String> a()
  {
    HashSet localHashSet = new HashSet();
    List localList = p.a().getPackageManager().getInstalledPackages(0);
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
  
  private static boolean a(int paramInt, String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    paramString = new f(paramInt, paramString);
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
    boolean bool = false;
    try
    {
      p.a().getPackageManager();
      paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
      if (!p.a().getPackageManager().queryIntentActivities(paramString, 0).isEmpty())
      {
        paramString.addFlags(872415232);
        p.a().startActivity(paramString);
        bool = true;
      }
      return bool;
    }
    catch (Exception paramString)
    {
      Log.e("SSAppLaunch", "", paramString);
    }
    return false;
  }
  
  public static boolean a(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    paramString2 = a(paramString2, paramString3);
    try
    {
      if (!paramString2.isEmpty()) {
        return b(paramString2);
      }
      boolean bool = c(paramString1, paramString4);
      return bool;
    }
    catch (Exception paramString1)
    {
      Log.e("SSAppLaunch", "", paramString1);
    }
    return false;
  }
  
  public static boolean b(String paramString)
  {
    try
    {
      paramString = p.a().getPackageManager().getLaunchIntentForPackage(paramString);
      paramString.addCategory("android.intent.category.LAUNCHER");
      paramString.setAction("android.intent.action.MAIN");
      paramString.addFlags(805306368);
      p.a().startActivity(paramString);
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
  
  public static boolean c(String paramString)
  {
    return a(1, paramString);
  }
  
  public static boolean c(String paramString1, String paramString2)
  {
    if (TextUtils.isEmpty(paramString2)) {
      return false;
    }
    return a(bo.a(paramString1.toLowerCase().trim()), paramString2);
  }
  
  public static boolean d(String paramString)
  {
    return a(4, paramString);
  }
  
  public static boolean e(String paramString)
  {
    return a(5, paramString);
  }
  
  public static boolean f(String paramString)
  {
    return a(6, paramString);
  }
  
  public static boolean g(String paramString)
  {
    return a(7, paramString);
  }
  
  public static boolean h(String paramString)
  {
    return a(8, paramString);
  }
  
  public static boolean i(String paramString)
  {
    return a(3, paramString);
  }
}
