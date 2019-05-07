package com.omelet.sdk.c;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import java.util.Iterator;
import java.util.List;

public final class d
{
  public d() {}
  
  public static int a(int paramInt1, int paramInt2)
  {
    if (paramInt1 < paramInt2) {
      return paramInt1;
    }
    return paramInt2;
  }
  
  public static int a(com.omelet.sdk.core.d.a paramA, int paramInt1, int paramInt2)
  {
    int i = paramInt1;
    switch (1.a[paramA.ordinal()])
    {
    default: 
      i = 0;
    case 4: 
    case 5: 
      return i;
    case 1: 
    case 2: 
      return a(paramInt1, paramInt2);
    }
    return c(paramInt1, paramInt2);
  }
  
  public static String a(int paramInt)
  {
    switch (1.b[(paramInt - 1)])
    {
    default: 
      return null;
    case 1: 
      return "WIFI";
    case 2: 
      return "CELLULAR_2G";
    case 3: 
      return "CELLULAR_3G";
    }
    return "CELLULAR_4G";
  }
  
  public static String a(Context paramContext)
  {
    paramContext = (WindowManager)paramContext.getSystemService("window");
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramContext.getDefaultDisplay().getMetrics(localDisplayMetrics);
    switch (localDisplayMetrics.densityDpi)
    {
    default: 
      return "mdpi";
    case 213: 
    case 240: 
      return "hdpi";
    case 320: 
    case 400: 
      return "xhdpi";
    }
    return "xxhdpi";
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    if (paramContext == null) {}
    while (paramContext.checkCallingOrSelfPermission(paramString) != 0) {
      return false;
    }
    return true;
  }
  
  public static boolean a(String paramString)
  {
    boolean bool = false;
    try
    {
      paramString = Class.forName(paramString);
      if (paramString != null) {
        bool = true;
      }
      return bool;
    }
    catch (ClassNotFoundException paramString)
    {
      return false;
    }
    catch (Exception paramString)
    {
      return false;
    }
    catch (NoClassDefFoundError paramString) {}
    return false;
  }
  
  public static int b(int paramInt1, int paramInt2)
  {
    return a(paramInt1, paramInt2) / 6;
  }
  
  public static int b(Context paramContext)
  {
    if (!a(paramContext, "android.permission.ACCESS_NETWORK_STATE")) {
      return a.f;
    }
    NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    if (localNetworkInfo == null) {
      return a.a;
    }
    if (localNetworkInfo.getType() == 1) {
      return a.b;
    }
    if (localNetworkInfo.getType() == 0)
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      if (paramContext == null) {
        return a.a;
      }
      switch (paramContext.getNetworkType())
      {
      default: 
        return a.f;
      case 1: 
      case 2: 
      case 4: 
      case 7: 
      case 11: 
        return a.c;
      case 3: 
      case 5: 
      case 6: 
      case 8: 
      case 9: 
      case 10: 
      case 12: 
      case 14: 
      case 15: 
        return a.d;
      }
      return a.e;
    }
    return a.f;
  }
  
  public static int b(Context paramContext, String paramString)
  {
    return paramContext.getResources().getIdentifier(paramString, "drawable", paramContext.getPackageName());
  }
  
  public static int b(com.omelet.sdk.core.d.a paramA, int paramInt1, int paramInt2)
  {
    int i = paramInt2;
    switch (1.a[paramA.ordinal()])
    {
    default: 
      i = 0;
    case 4: 
    case 5: 
      return i;
    case 1: 
    case 2: 
      return a(paramInt1, paramInt2) / 6;
    }
    return c(paramInt1, paramInt2);
  }
  
  public static int c(int paramInt1, int paramInt2)
  {
    if (paramInt1 < paramInt2) {
      return paramInt1;
    }
    return paramInt2;
  }
  
  public static boolean c(Context paramContext)
  {
    if (!a(paramContext, "android.permission.ACCESS_NETWORK_STATE")) {
      return true;
    }
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if (paramContext == null) {
      return true;
    }
    paramContext = paramContext.getActiveNetworkInfo();
    return (paramContext != null) && (paramContext.isConnected());
  }
  
  public static boolean c(Context paramContext, String paramString)
  {
    return paramContext.getResources().getIdentifier(paramString, "drawable", paramContext.getPackageName()) != 0;
  }
  
  public static int d(int paramInt1, int paramInt2)
  {
    return c(paramInt1, paramInt2);
  }
  
  public static int d(Context paramContext, String paramString)
  {
    return paramContext.getResources().getIdentifier(paramString, "xml", paramContext.getPackageName());
  }
  
  public static boolean e(Context paramContext, String paramString)
  {
    return paramContext.getResources().getIdentifier(paramString, "xml", paramContext.getPackageName()) != 0;
  }
  
  public static boolean f(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    while (paramContext.hasNext()) {
      if (((ApplicationInfo)paramContext.next()).packageName.equals(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  public static enum a
  {
    public static final int a = 1;
    public static final int b = 2;
    public static final int c = 3;
    public static final int d = 4;
    public static final int e = 5;
    public static final int f = 6;
    
    private a() {}
    
    public static int[] a()
    {
      return (int[])g.clone();
    }
  }
}
