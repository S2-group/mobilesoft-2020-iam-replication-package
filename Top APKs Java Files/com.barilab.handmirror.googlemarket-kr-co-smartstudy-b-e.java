package kr.co.smartstudy.b;

import android.app.Application;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import java.util.HashSet;
import java.util.List;

public final class e
{
  public static boolean a = false;
  public static Handler b = new Handler();
  private static String c = "tstore";
  private static String d = "ozstore";
  private static String e = "googlemarket";
  private static String f = "ssapps";
  private static String g = "amazon";
  private static String h = "xiaomi";
  
  public e() {}
  
  private static HashSet<String> a()
  {
    HashSet localHashSet = new HashSet();
    List localList = u.a().getPackageManager().getInstalledPackages(0);
    int i = 0;
    for (;;)
    {
      if (i >= localList.size()) {
        return localHashSet;
      }
      PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
      String str1 = localPackageInfo.packageName.toLowerCase();
      String str2 = localPackageInfo.versionName;
      int j = localPackageInfo.versionCode;
      localHashSet.add(str1);
      i += 1;
    }
  }
  
  private static void a(boolean paramBoolean)
  {
    a = paramBoolean;
  }
  
  private static boolean a(String paramString)
  {
    boolean bool = false;
    try
    {
      u.a().getPackageManager();
      paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
      if (!u.a().getPackageManager().queryIntentActivities(paramString, 0).isEmpty())
      {
        paramString.addFlags(872415232);
        u.a().startActivity(paramString);
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
  
  public static boolean a(String paramString1, String paramString2)
  {
    return !b(paramString1, paramString2).isEmpty();
  }
  
  private static boolean a(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    paramString2 = b(paramString2, paramString3);
    try
    {
      if (!paramString2.isEmpty()) {
        return b(paramString2);
      }
      paramString1 = paramString1.toLowerCase().trim();
      if (!paramString1.equalsIgnoreCase(c)) {
        break label91;
      }
      if (paramString4.length() <= 0) {
        break label360;
      }
      paramString1 = new f(paramString4);
      if (a)
      {
        paramString1.run();
        return true;
      }
    }
    catch (Exception paramString1)
    {
      Log.e("SSAppLaunch", "", paramString1);
      return false;
    }
    b.postDelayed(paramString1, 500L);
    return true;
    label91:
    if (paramString1.equalsIgnoreCase(d))
    {
      if (paramString4.length() > 0)
      {
        paramString1 = new g(paramString4);
        if (a)
        {
          paramString1.run();
          return true;
        }
        b.postDelayed(paramString1, 500L);
        return true;
      }
    }
    else
    {
      if (paramString1.equalsIgnoreCase(e))
      {
        if (paramString4.length() <= 0) {
          break label364;
        }
        paramString1 = new k(paramString4);
        if (a)
        {
          paramString1.run();
          return true;
        }
        b.postDelayed(paramString1, 500L);
        return true;
      }
      if (paramString1.equalsIgnoreCase(f))
      {
        if (paramString4.length() <= 0) {
          break label366;
        }
        paramString1 = new h(paramString4);
        if (a)
        {
          paramString1.run();
          return true;
        }
        b.postDelayed(paramString1, 500L);
        return true;
      }
      if (paramString1.equalsIgnoreCase(g))
      {
        if (paramString4.length() <= 0) {
          break label368;
        }
        paramString1 = new i(paramString4);
        if (a)
        {
          paramString1.run();
          return true;
        }
        b.postDelayed(paramString1, 500L);
        return true;
      }
      if (paramString1.equalsIgnoreCase(h))
      {
        if (paramString4.length() > 0)
        {
          paramString1 = new j(paramString4);
          if (a)
          {
            paramString1.run();
            return true;
          }
          b.postDelayed(paramString1, 500L);
          return true;
        }
        return false;
      }
      return false;
      label360:
      return false;
    }
    return false;
    label364:
    return false;
    label366:
    return false;
    label368:
    return false;
  }
  
  private static String b(String paramString1, String paramString2)
  {
    paramString1 = paramString1.trim().toLowerCase();
    HashSet localHashSet = new HashSet();
    List localList = u.a().getPackageManager().getInstalledPackages(0);
    int i = 0;
    boolean bool;
    for (;;)
    {
      if (i >= localList.size())
      {
        bool = localHashSet.contains(paramString1);
        if (!bool) {
          break;
        }
        return paramString1;
      }
      PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
      String str1 = localPackageInfo.packageName.toLowerCase();
      String str2 = localPackageInfo.versionName;
      int j = localPackageInfo.versionCode;
      localHashSet.add(str1);
      i += 1;
    }
    if ((!bool) && (!u.a(paramString2)))
    {
      paramString1 = new Intent("android.intent.action.VIEW", Uri.parse(paramString2));
      paramString1 = u.a().getPackageManager().queryIntentActivities(paramString1, 0);
      if (!paramString1.isEmpty()) {
        return ((ResolveInfo)paramString1.get(0)).activityInfo.packageName;
      }
    }
    return "";
  }
  
  private static boolean b(String paramString)
  {
    try
    {
      paramString = u.a().getPackageManager().getLaunchIntentForPackage(paramString);
      paramString.addCategory("android.intent.category.LAUNCHER");
      paramString.setAction("android.intent.action.MAIN");
      paramString.addFlags(805306368);
      u.a().startActivity(paramString);
      return true;
    }
    catch (Exception paramString)
    {
      Log.e("SSAppLaunch", "", paramString);
    }
    return false;
  }
  
  private static boolean c(String paramString)
  {
    if (paramString.length() > 0)
    {
      paramString = new f(paramString);
      if (a) {
        paramString.run();
      }
      for (;;)
      {
        return true;
        b.postDelayed(paramString, 500L);
      }
    }
    return false;
  }
  
  private static boolean c(String paramString1, String paramString2)
  {
    paramString1 = paramString1.toLowerCase().trim();
    if (paramString1.equalsIgnoreCase(c))
    {
      if (paramString2.length() > 0)
      {
        paramString1 = new f(paramString2);
        if (a)
        {
          paramString1.run();
          return true;
        }
        b.postDelayed(paramString1, 500L);
        return true;
      }
      return false;
    }
    if (paramString1.equalsIgnoreCase(d))
    {
      if (paramString2.length() > 0)
      {
        paramString1 = new g(paramString2);
        if (a)
        {
          paramString1.run();
          return true;
        }
        b.postDelayed(paramString1, 500L);
        return true;
      }
      return false;
    }
    if (paramString1.equalsIgnoreCase(e))
    {
      if (paramString2.length() > 0)
      {
        paramString1 = new k(paramString2);
        if (a)
        {
          paramString1.run();
          return true;
        }
        b.postDelayed(paramString1, 500L);
        return true;
      }
      return false;
    }
    if (paramString1.equalsIgnoreCase(f))
    {
      if (paramString2.length() > 0)
      {
        paramString1 = new h(paramString2);
        if (a)
        {
          paramString1.run();
          return true;
        }
        b.postDelayed(paramString1, 500L);
        return true;
      }
      return false;
    }
    if (paramString1.equalsIgnoreCase(g))
    {
      if (paramString2.length() > 0)
      {
        paramString1 = new i(paramString2);
        if (a)
        {
          paramString1.run();
          return true;
        }
        b.postDelayed(paramString1, 500L);
        return true;
      }
      return false;
    }
    if (paramString1.equalsIgnoreCase(h))
    {
      if (paramString2.length() > 0)
      {
        paramString1 = new j(paramString2);
        if (a)
        {
          paramString1.run();
          return true;
        }
        b.postDelayed(paramString1, 500L);
        return true;
      }
      return false;
    }
    return false;
  }
  
  private static boolean d(String paramString)
  {
    if (paramString.length() > 0)
    {
      paramString = new g(paramString);
      if (a) {
        paramString.run();
      }
      for (;;)
      {
        return true;
        b.postDelayed(paramString, 500L);
      }
    }
    return false;
  }
  
  private static boolean e(String paramString)
  {
    if (paramString.length() > 0)
    {
      paramString = new h(paramString);
      if (a) {
        paramString.run();
      }
      for (;;)
      {
        return true;
        b.postDelayed(paramString, 500L);
      }
    }
    return false;
  }
  
  private static boolean f(String paramString)
  {
    if (paramString.length() > 0)
    {
      paramString = new i(paramString);
      if (a) {
        paramString.run();
      }
      for (;;)
      {
        return true;
        b.postDelayed(paramString, 500L);
      }
    }
    return false;
  }
  
  private static boolean g(String paramString)
  {
    if (paramString.length() > 0)
    {
      paramString = new j(paramString);
      if (a) {
        paramString.run();
      }
      for (;;)
      {
        return true;
        b.postDelayed(paramString, 500L);
      }
    }
    return false;
  }
  
  private static boolean h(String paramString)
  {
    if (paramString.length() > 0)
    {
      paramString = new k(paramString);
      if (a) {
        paramString.run();
      }
      for (;;)
      {
        return true;
        b.postDelayed(paramString, 500L);
      }
    }
    return false;
  }
}
