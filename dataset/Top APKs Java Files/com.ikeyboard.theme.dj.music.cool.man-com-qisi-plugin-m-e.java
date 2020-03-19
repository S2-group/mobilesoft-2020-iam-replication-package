package com.qisi.plugin.m;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.text.TextUtils;
import com.google.gson.JsonSyntaxException;
import com.qisi.a.d.g;
import com.qisi.plugin.manager.App;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class e
{
  public static d a(Context paramContext)
  {
    paramContext = net.afpro.c.a.e.b(paramContext, "pref_key_installed_theme_packages", "");
    if (TextUtils.isEmpty(paramContext)) {
      return null;
    }
    try
    {
      paramContext = d.a(paramContext);
      return paramContext;
    }
    catch (JsonSyntaxException paramContext) {}
    return null;
  }
  
  private static String a(String paramString1, String paramString2)
  {
    int i = 0;
    if (c(paramString2)) {
      return "theme";
    }
    if (d(paramString2)) {
      return "emoji";
    }
    if (e(paramString2)) {
      return "sticker";
    }
    if (f(paramString2)) {
      return "sound";
    }
    int j;
    if ((b(paramString2)) && (a(paramString1)))
    {
      paramString1 = paramString1.split("\\.");
      if ((paramString1 != null) && (paramString1.length >= 3))
      {
        int k = paramString1.length;
        j = 0;
        if (i < k)
        {
          paramString2 = paramString1[i];
          j += 1;
          if (j >= 3) {}
        }
      }
    }
    do
    {
      i += 1;
      break;
      if (j > 3) {
        return "";
      }
      if ("theme".equals(paramString2)) {
        return "theme";
      }
      if ("emoji".equals(paramString2)) {
        return "emoji";
      }
    } while (!"sticker".equals(paramString2));
    return "sticker";
  }
  
  public static List<ApplicationInfo> a()
  {
    localArrayList = new ArrayList();
    try
    {
      Context localContext = App.a();
      Iterator localIterator = localContext.getPackageManager().getInstalledApplications(128).iterator();
      while (localIterator.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
        if (localApplicationInfo != null)
        {
          String str = localApplicationInfo.packageName;
          if ((!TextUtils.isEmpty(str)) && ((f(localContext, str)) || (g(localContext, str)))) {
            localArrayList.add(localApplicationInfo);
          }
        }
      }
      return localArrayList;
    }
    catch (Exception localException) {}
  }
  
  public static void a(Context paramContext, d paramD)
  {
    paramD = paramD.a();
    if (!TextUtils.isEmpty(paramD)) {
      net.afpro.c.a.e.a(paramContext, "pref_key_installed_theme_packages", paramD);
    }
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    paramContext = a(paramContext);
    return (paramContext != null) && (paramContext.a != null) && (paramContext.a.size() > 0) && (((String)paramContext.a.get(0)).equals(paramString));
  }
  
  private static boolean a(Context paramContext, String paramString1, String paramString2)
  {
    return "theme".equals(a(paramString1, paramString2));
  }
  
  private static boolean a(String paramString)
  {
    String[] arrayOfString = new String[8];
    arrayOfString[0] = "387247d1a078f8660770bcdcbdaf54ed";
    arrayOfString[1] = "2125bbe526aba7eb3656ab652f1771f8";
    arrayOfString[2] = "34b3f04994d67dbcf347e6348673bbdb";
    arrayOfString[3] = "492a16c12ad73263d07636a5afdc38de";
    arrayOfString[4] = "5bf4d439c08cb1c74cced31c2fa4d8e0";
    arrayOfString[5] = "04240718c8553a1a87201e32ce991f41";
    arrayOfString[6] = "80666a73080142a27221e27d869a687a";
    arrayOfString[7] = "5735444e77aa70540ac3d3da45f11a92";
    paramString = g.a(paramString);
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      if (arrayOfString[i].equals(paramString)) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  public static d b(Context paramContext)
  {
    if (!com.qisi.plugin.b.j.booleanValue()) {}
    do
    {
      return null;
      paramContext = net.afpro.c.a.e.b(paramContext, "pref_key_notification_enabled_pkgs", "");
    } while (TextUtils.isEmpty(paramContext));
    try
    {
      paramContext = d.a(paramContext);
      return paramContext;
    }
    catch (JsonSyntaxException paramContext) {}
    return null;
  }
  
  public static void b(Context paramContext, d paramD)
  {
    if (!com.qisi.plugin.b.j.booleanValue()) {}
    do
    {
      return;
      paramD = paramD.a();
    } while (TextUtils.isEmpty(paramD));
    net.afpro.c.a.e.a(paramContext, "pref_key_notification_enabled_pkgs", paramD);
  }
  
  public static boolean b(Context paramContext, String paramString)
  {
    if (!com.qisi.plugin.b.j.booleanValue()) {
      return false;
    }
    paramContext = a(paramContext);
    if ((paramContext != null) && (paramContext.a != null) && (paramContext.a.size() > 0) && (((String)paramContext.a.get(0)).equals(paramString))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private static boolean b(Context paramContext, String paramString1, String paramString2)
  {
    return "emoji".equals(a(paramString1, paramString2));
  }
  
  private static boolean b(String paramString)
  {
    return b.a.contains(paramString);
  }
  
  public static d c(Context paramContext)
  {
    if (!com.qisi.plugin.b.l.booleanValue()) {}
    do
    {
      return null;
      paramContext = net.afpro.c.a.e.b(paramContext, "pref_key_theme_store_enabled_pkgs", "");
    } while (TextUtils.isEmpty(paramContext));
    try
    {
      paramContext = d.a(paramContext);
      return paramContext;
    }
    catch (JsonSyntaxException paramContext) {}
    return null;
  }
  
  public static void c(Context paramContext, d paramD)
  {
    if (!com.qisi.plugin.b.l.booleanValue()) {}
    do
    {
      return;
      paramD = paramD.a();
    } while (TextUtils.isEmpty(paramD));
    net.afpro.c.a.e.a(paramContext, "pref_key_theme_store_enabled_pkgs", paramD);
  }
  
  public static void c(Context paramContext, String paramString)
  {
    if (!com.qisi.plugin.b.l.booleanValue()) {}
    while (TextUtils.isEmpty(paramString)) {
      return;
    }
    net.afpro.c.a.e.a(paramContext, "pref_key_theme_store_enabled_pkgs", paramString);
  }
  
  private static boolean c(Context paramContext, String paramString1, String paramString2)
  {
    return a(paramContext, paramString1, paramString2);
  }
  
  private static boolean c(String paramString)
  {
    return b.b.contains(paramString);
  }
  
  public static String d(Context paramContext)
  {
    if (!com.qisi.plugin.b.l.booleanValue()) {
      return "";
    }
    return net.afpro.c.a.e.b(paramContext, "pref_key_theme_store_active_pkg", "");
  }
  
  public static void d(Context paramContext, d paramD)
  {
    if (!com.qisi.plugin.b.l.booleanValue()) {
      return;
    }
    c(paramContext, paramD.a());
  }
  
  public static void d(Context paramContext, String paramString)
  {
    String str = paramString;
    if (paramString == null) {
      str = "";
    }
    net.afpro.c.a.e.a(paramContext, "pref_key_theme_store_active_pkg", str);
  }
  
  private static boolean d(Context paramContext, String paramString1, String paramString2)
  {
    return b(paramContext, paramString1, paramString2);
  }
  
  private static boolean d(String paramString)
  {
    return b.c.contains(paramString);
  }
  
  public static boolean e(Context paramContext)
  {
    if (!com.qisi.plugin.b.l.booleanValue()) {}
    while (TextUtils.isEmpty(net.afpro.c.a.e.b(paramContext, "pref_key_theme_store_active_pkg", ""))) {
      return false;
    }
    return true;
  }
  
  public static boolean e(Context paramContext, String paramString)
  {
    if (!com.qisi.plugin.b.l.booleanValue()) {
      return false;
    }
    return TextUtils.equals(net.afpro.c.a.e.b(paramContext, "pref_key_theme_store_active_pkg", ""), paramString);
  }
  
  private static boolean e(String paramString)
  {
    return b.d.contains(paramString);
  }
  
  public static boolean f(Context paramContext, String paramString)
  {
    try
    {
      Object localObject = paramContext.getPackageManager().getPackageInfo(paramString, 64);
      if (localObject != null)
      {
        if (((PackageInfo)localObject).signatures == null) {
          return false;
        }
        localObject = ((PackageInfo)localObject).signatures;
        int j = localObject.length;
        int i = 0;
        while (i < j)
        {
          String str = g.a(localObject[i].toCharsString());
          if (!TextUtils.isEmpty(str))
          {
            boolean bool = c(paramContext, paramString, str);
            if (bool) {
              return true;
            }
          }
          i += 1;
        }
      }
      return false;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  private static boolean f(String paramString)
  {
    return b.e.contains(paramString);
  }
  
  public static boolean g(Context paramContext, String paramString)
  {
    try
    {
      Object localObject = paramContext.getPackageManager().getPackageInfo(paramString, 64);
      if (localObject != null)
      {
        if (((PackageInfo)localObject).signatures == null) {
          return false;
        }
        localObject = ((PackageInfo)localObject).signatures;
        int j = localObject.length;
        int i = 0;
        while (i < j)
        {
          String str = g.a(localObject[i].toCharsString());
          if (!TextUtils.isEmpty(str))
          {
            boolean bool = d(paramContext, paramString, str);
            if (bool) {
              return true;
            }
          }
          i += 1;
        }
      }
      return false;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
  }
}
