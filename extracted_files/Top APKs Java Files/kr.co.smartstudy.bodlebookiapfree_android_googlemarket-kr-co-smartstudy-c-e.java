package kr.co.smartstudy.c;

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
  public static String a = "tstore";
  public static String b = "ozstore";
  public static String c = "googlemarket";
  public static String d = "samsungstore";
  public static String e = "amazonstore";
  public static boolean f = false;
  
  public e() {}
  
  private static String a(String paramString1, String paramString2)
  {
    paramString1 = paramString1.trim().toLowerCase();
    HashSet localHashSet = new HashSet();
    List localList = t.a().getPackageManager().getInstalledPackages(0);
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
    if ((!bool) && (!t.a(paramString2)))
    {
      paramString1 = new Intent("android.intent.action.VIEW", Uri.parse(paramString2));
      paramString1 = t.a().getPackageManager().queryIntentActivities(paramString1, 0);
      if (!paramString1.isEmpty()) {
        return ((ResolveInfo)paramString1.get(0)).activityInfo.packageName;
      }
    }
    return "";
  }
  
  private static HashSet<String> a()
  {
    HashSet localHashSet = new HashSet();
    List localList = t.a().getPackageManager().getInstalledPackages(0);
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
    f = paramBoolean;
  }
  
  private static boolean a(String paramString)
  {
    boolean bool = false;
    try
    {
      t.a().getPackageManager();
      paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
      if (!t.a().getPackageManager().queryIntentActivities(paramString, 0).isEmpty())
      {
        paramString.addFlags(805306368);
        t.a().startActivity(paramString);
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
  
  private static boolean a(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    paramString2 = a(paramString2, paramString3);
    try
    {
      if (!paramString2.isEmpty()) {
        return b(paramString2);
      }
      paramString1 = paramString1.toLowerCase().trim();
      if (!paramString1.equalsIgnoreCase(a)) {
        break label95;
      }
      if (paramString4.length() <= 0) {
        break label327;
      }
      paramString1 = new f(paramString4);
      if (f)
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
    new Handler().postDelayed(paramString1, 500L);
    return true;
    label95:
    if (paramString1.equalsIgnoreCase(b))
    {
      if (paramString4.length() > 0)
      {
        paramString1 = new g(paramString4);
        if (f)
        {
          paramString1.run();
          return true;
        }
        new Handler().postDelayed(paramString1, 500L);
        return true;
      }
    }
    else
    {
      if (paramString1.equalsIgnoreCase(c))
      {
        if (paramString4.length() <= 0) {
          break label331;
        }
        paramString1 = new j(paramString4);
        if (f)
        {
          paramString1.run();
          return true;
        }
        new Handler().postDelayed(paramString1, 500L);
        return true;
      }
      if (paramString1.equalsIgnoreCase(d))
      {
        if (paramString4.length() <= 0) {
          break label333;
        }
        paramString1 = new h(paramString4);
        if (f)
        {
          paramString1.run();
          return true;
        }
        new Handler().postDelayed(paramString1, 500L);
        return true;
      }
      if (paramString1.equalsIgnoreCase(e))
      {
        if (paramString4.length() > 0)
        {
          paramString1 = new i(paramString4);
          if (f)
          {
            paramString1.run();
            return true;
          }
          new Handler().postDelayed(paramString1, 500L);
          return true;
        }
        return false;
      }
      return false;
      label327:
      return false;
    }
    return false;
    label331:
    return false;
    label333:
    return false;
  }
  
  private static boolean b(String paramString)
  {
    try
    {
      paramString = t.a().getPackageManager().getLaunchIntentForPackage(paramString);
      paramString.addCategory("android.intent.category.LAUNCHER");
      paramString.setAction("android.intent.action.MAIN");
      paramString.addFlags(805306368);
      t.a().startActivity(paramString);
      return true;
    }
    catch (Exception paramString)
    {
      Log.e("SSAppLaunch", "", paramString);
    }
    return false;
  }
  
  private static boolean b(String paramString1, String paramString2)
  {
    return !a(paramString1, paramString2).isEmpty();
  }
  
  private static boolean c(String paramString)
  {
    if (paramString.length() > 0)
    {
      paramString = new f(paramString);
      if (f) {
        paramString.run();
      }
      for (;;)
      {
        return true;
        new Handler().postDelayed(paramString, 500L);
      }
    }
    return false;
  }
  
  private static boolean c(String paramString1, String paramString2)
  {
    paramString1 = paramString1.toLowerCase().trim();
    if (paramString1.equalsIgnoreCase(a))
    {
      if (paramString2.length() > 0)
      {
        paramString1 = new f(paramString2);
        if (f)
        {
          paramString1.run();
          return true;
        }
        new Handler().postDelayed(paramString1, 500L);
        return true;
      }
      return false;
    }
    if (paramString1.equalsIgnoreCase(b))
    {
      if (paramString2.length() > 0)
      {
        paramString1 = new g(paramString2);
        if (f)
        {
          paramString1.run();
          return true;
        }
        new Handler().postDelayed(paramString1, 500L);
        return true;
      }
      return false;
    }
    if (paramString1.equalsIgnoreCase(c))
    {
      if (paramString2.length() > 0)
      {
        paramString1 = new j(paramString2);
        if (f)
        {
          paramString1.run();
          return true;
        }
        new Handler().postDelayed(paramString1, 500L);
        return true;
      }
      return false;
    }
    if (paramString1.equalsIgnoreCase(d))
    {
      if (paramString2.length() > 0)
      {
        paramString1 = new h(paramString2);
        if (f)
        {
          paramString1.run();
          return true;
        }
        new Handler().postDelayed(paramString1, 500L);
        return true;
      }
      return false;
    }
    if (paramString1.equalsIgnoreCase(e))
    {
      if (paramString2.length() > 0)
      {
        paramString1 = new i(paramString2);
        if (f)
        {
          paramString1.run();
          return true;
        }
        new Handler().postDelayed(paramString1, 500L);
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
      if (f) {
        paramString.run();
      }
      for (;;)
      {
        return true;
        new Handler().postDelayed(paramString, 500L);
      }
    }
    return false;
  }
  
  private static boolean e(String paramString)
  {
    if (paramString.length() > 0)
    {
      paramString = new h(paramString);
      if (f) {
        paramString.run();
      }
      for (;;)
      {
        return true;
        new Handler().postDelayed(paramString, 500L);
      }
    }
    return false;
  }
  
  private static boolean f(String paramString)
  {
    if (paramString.length() > 0)
    {
      paramString = new i(paramString);
      if (f) {
        paramString.run();
      }
      for (;;)
      {
        return true;
        new Handler().postDelayed(paramString, 500L);
      }
    }
    return false;
  }
  
  private static boolean g(String paramString)
  {
    if (paramString.length() > 0)
    {
      paramString = new j(paramString);
      if (f) {
        paramString.run();
      }
      for (;;)
      {
        return true;
        new Handler().postDelayed(paramString, 500L);
      }
    }
    return false;
  }
}
