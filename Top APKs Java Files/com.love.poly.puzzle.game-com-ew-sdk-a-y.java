package com.ew.sdk.a;

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
import com.ew.sdk.plugin.BaseApplication;
import com.ew.sdk.plugin.g;
import com.ew.sdk.plugin.o;
import com.ew.sdk.plugin.s;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class y
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
      e.a("Get Version Code Error!!!", localException);
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
    if (paramContext != null)
    {
      if (!paramContext.isAvailable()) {
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
            if (("TD-SCDMA".equalsIgnoreCase((String)localObject2)) || ("WCDMA".equalsIgnoreCase((String)localObject2))) {
              break label233;
            }
            if ("CDMA2000".equalsIgnoreCase((String)localObject2)) {
              return 3;
            }
            break;
          case 13: 
            return 4;
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
          case 1: 
          case 2: 
          case 4: 
          case 7: 
          case 11: 
            return 2;
          }
          return 5;
          label233:
          return 3;
        }
      }
      return 0;
    }
    return 0;
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    boolean bool;
    if (paramContext.getPackageManager().checkPermission(paramString, paramContext.getPackageName()) == 0) {
      bool = true;
    } else {
      bool = false;
    }
    if (e.a())
    {
      paramContext = new StringBuilder();
      paramContext.append(paramString);
      paramContext.append(" - permission: ");
      paramContext.append(bool);
      e.b(paramContext.toString());
    }
    return bool;
  }
  
  public static boolean a(String paramString)
  {
    try
    {
      Class.forName(paramString);
      return true;
    }
    catch (ClassNotFoundException paramString)
    {
      for (;;) {}
    }
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
      e.a(paramContext);
    }
    return new HashSet();
  }
  
  public static boolean b()
  {
    int i = g.a.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE");
    boolean bool2 = false;
    if (i == 0)
    {
      Object localObject = (ConnectivityManager)g.a.getSystemService("connectivity");
      if (localObject == null) {
        return false;
      }
      localObject = ((ConnectivityManager)localObject).getActiveNetworkInfo();
      if (localObject == null) {
        return false;
      }
      i = ((NetworkInfo)localObject).getType();
      boolean bool1;
      if ((i != 1) && (i != 0))
      {
        bool1 = bool2;
        if (i != 6) {}
      }
      else
      {
        bool1 = bool2;
        if (((NetworkInfo)localObject).isConnected()) {
          bool1 = true;
        }
      }
      return bool1;
    }
    return false;
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
    case 5: 
      return "5g";
    case 4: 
      return "4g";
    case 3: 
      return "3g";
    case 2: 
      return "2g";
    }
    return "wifi";
  }
  
  public static boolean c(String paramString)
  {
    boolean bool2 = TextUtils.isEmpty(paramString);
    boolean bool1 = false;
    if (bool2) {
      return false;
    }
    Object localObject = null;
    try
    {
      paramString = g.a.getPackageManager().getPackageInfo(paramString, 0);
      if (paramString != null) {
        bool1 = true;
      }
      return bool1;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      for (;;)
      {
        paramString = localObject;
      }
    }
  }
  
  public static int d(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return -1;
    }
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
      return -1;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      e.a("Get Meta_Data Info error:", paramString);
    }
    return -1;
  }
  
  public static boolean d()
  {
    return (g.a.getResources().getConfiguration().screenLayout & 0xF) >= 3;
  }
  
  public static String e(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    Object localObject = g.a;
    try
    {
      localObject = ((Context)localObject).getPackageManager().getApplicationInfo(((Context)localObject).getPackageName(), 128);
      if (localObject != null)
      {
        localObject = ((ApplicationInfo)localObject).metaData;
        if (localObject != null)
        {
          paramString = ((Bundle)localObject).getString(paramString);
          return paramString;
        }
      }
      return null;
    }
    catch (Exception paramString)
    {
      e.a("Get Meta_Data Info error:", paramString);
    }
    return null;
  }
  
  public static boolean f(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
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
      return false;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      e.a("Get Meta_Data Info error:", paramString);
    }
    return false;
  }
}
