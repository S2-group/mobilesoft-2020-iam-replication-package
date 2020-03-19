package com.gameone.one.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Bundle;
import android.text.TextUtils;
import com.gameone.one.plugin.BaseApplication;
import com.gameone.one.plugin.g;
import com.gameone.one.plugin.o;
import com.gameone.one.plugin.s;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class A
{
  public static int a()
  {
    try
    {
      int i = g.a.getPackageManager().getPackageInfo(g.a.getPackageName(), 0).versionCode;
      return i;
    }
    catch (Exception localException)
    {
      f.a("Get Version Code Error!!!", localException);
    }
    return -1;
  }
  
  public static int a(Context paramContext)
  {
    if (!a(paramContext, "android.permission.ACCESS_NETWORK_STATE")) {
      return 0;
    }
    Object localObject1 = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if (localObject1 == null) {
      return 0;
    }
    paramContext = ((ConnectivityManager)localObject1).getActiveNetworkInfo();
    if ((paramContext == null) || (!paramContext.isAvailable())) {
      return 0;
    }
    Object localObject2 = ((ConnectivityManager)localObject1).getNetworkInfo(1);
    if (localObject2 != null)
    {
      localObject2 = ((NetworkInfo)localObject2).getState();
      if ((localObject2 != null) && ((localObject2 == NetworkInfo.State.CONNECTED) || (localObject2 == NetworkInfo.State.CONNECTING))) {
        return 1;
      }
    }
    localObject2 = ((ConnectivityManager)localObject1).getNetworkInfo(0);
    if (localObject2 != null)
    {
      localObject1 = ((NetworkInfo)localObject2).getState();
      localObject2 = ((NetworkInfo)localObject2).getSubtypeName();
      if ((localObject1 != null) && ((localObject1 == NetworkInfo.State.CONNECTED) || (localObject1 == NetworkInfo.State.CONNECTING)))
      {
        switch (paramContext.getSubtype())
        {
        default: 
          if (("TD-SCDMA".equalsIgnoreCase((String)localObject2)) || ("WCDMA".equalsIgnoreCase((String)localObject2)) || ("CDMA2000".equalsIgnoreCase((String)localObject2))) {
            return 3;
          }
          break;
        case 1: 
        case 2: 
        case 4: 
        case 7: 
        case 11: 
          return 2;
        case 3: 
        case 5: 
        case 6: 
        case 8: 
        case 9: 
        case 10: 
        case 12: 
        case 14: 
        case 15: 
          return 3;
        case 13: 
          return 4;
        }
        return 5;
      }
    }
    return 0;
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    if (paramContext.getPackageManager().checkPermission(paramString, paramContext.getPackageName()) == 0) {}
    for (boolean bool = true;; bool = false)
    {
      if (f.a()) {
        f.b(paramString + " - permission: " + bool);
      }
      return bool;
    }
  }
  
  public static boolean a(String paramString)
  {
    try
    {
      Class.forName(paramString);
      return true;
    }
    catch (ClassNotFoundException paramString) {}
    return false;
  }
  
  public static HashSet<String> b(Context paramContext)
  {
    try
    {
      Object localObject = paramContext.getPackageManager().getInstalledPackages(0);
      paramContext = new HashSet(((List)localObject).size() - 30);
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
        if ((localPackageInfo.applicationInfo.flags & 0x1) == 0) {
          paramContext.add(localPackageInfo.packageName);
        }
      }
      return paramContext;
    }
    catch (Exception paramContext)
    {
      f.a(paramContext);
      return new HashSet();
    }
  }
  
  public static boolean b()
  {
    Object localObject;
    if (g.a.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == 0)
    {
      localObject = (ConnectivityManager)g.a.getSystemService("connectivity");
      if (localObject != null) {
        break label29;
      }
    }
    label29:
    do
    {
      return false;
      localObject = ((ConnectivityManager)localObject).getActiveNetworkInfo();
    } while (localObject == null);
    int i = ((NetworkInfo)localObject).getType();
    if (((i == 1) || (i == 0) || (i == 6)) && (((NetworkInfo)localObject).isConnected())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static boolean b(String paramString)
  {
    paramString = (s)o.b.get(paramString);
    return (paramString != null) && (c(paramString.b));
  }
  
  public static String c()
  {
    switch (a(g.a.getApplicationContext()))
    {
    default: 
      return "";
    case 1: 
      return "wifi";
    case 2: 
      return "2g";
    case 3: 
      return "3g";
    case 4: 
      return "4g";
    }
    return "5g";
  }
  
  public static boolean c(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    for (;;)
    {
      return false;
      Object localObject = null;
      try
      {
        paramString = g.a.getPackageManager().getPackageInfo(paramString, 0);
        if (paramString == null) {
          continue;
        }
        return true;
      }
      catch (PackageManager.NameNotFoundException paramString)
      {
        for (;;)
        {
          paramString = localObject;
        }
      }
    }
  }
  
  public static int d(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    for (;;)
    {
      return -1;
      Object localObject = g.a;
      try
      {
        localObject = ((Context)localObject).getPackageManager().getApplicationInfo(((Context)localObject).getPackageName(), 128);
        if (localObject != null)
        {
          localObject = ((ApplicationInfo)localObject).metaData;
          if (localObject != null) {
            return ((Bundle)localObject).getInt(paramString);
          }
        }
      }
      catch (PackageManager.NameNotFoundException paramString)
      {
        f.a("Get Meta_Data Info error:", paramString);
      }
    }
    return -1;
  }
  
  public static boolean d()
  {
    return (g.a.getResources().getConfiguration().screenLayout & 0xF) >= 3;
  }
  
  public static String e(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    for (;;)
    {
      return null;
      Object localObject = g.a;
      try
      {
        localObject = ((Context)localObject).getPackageManager().getApplicationInfo(((Context)localObject).getPackageName(), 128);
        if (localObject != null)
        {
          localObject = ((ApplicationInfo)localObject).metaData;
          if (localObject != null) {
            return ((Bundle)localObject).getString(paramString);
          }
        }
      }
      catch (PackageManager.NameNotFoundException paramString)
      {
        f.a("Get Meta_Data Info error:", paramString);
      }
    }
    return null;
  }
  
  public static boolean f(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    for (;;)
    {
      return false;
      Object localObject = g.a;
      try
      {
        localObject = ((Context)localObject).getPackageManager().getApplicationInfo(((Context)localObject).getPackageName(), 128);
        if (localObject != null)
        {
          localObject = ((ApplicationInfo)localObject).metaData;
          if (localObject != null) {
            return ((Bundle)localObject).getBoolean(paramString);
          }
        }
      }
      catch (PackageManager.NameNotFoundException paramString)
      {
        f.a("Get Meta_Data Info error:", paramString);
      }
    }
    return false;
  }
}
