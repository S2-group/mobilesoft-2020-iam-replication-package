package com.pushwoosh.internal.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class b
{
  private static final e a = new c(null);
  private static final e b = new b(null);
  private static final e c = new d(null);
  private static final e d = new a(null);
  
  static
  {
    b.a(c);
    c.a(a);
    a.a(d);
  }
  
  public static String a(Context paramContext)
  {
    return b.b(paramContext);
  }
  
  private static String a(String paramString)
  {
    String str;
    if ((paramString == null) || (paramString.length() == 0)) {
      str = "";
    }
    char c1;
    do
    {
      return str;
      c1 = paramString.charAt(0);
      str = paramString;
    } while (Character.isUpperCase(c1));
    return Character.toUpperCase(c1) + paramString.substring(1);
  }
  
  public static boolean a()
  {
    try
    {
      Class.forName("com.amazon.device.messaging.ADM");
      return true;
    }
    catch (ClassNotFoundException localClassNotFoundException) {}
    return false;
  }
  
  public static long b()
  {
    try
    {
      StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
      long l = localStatFs.getBlockSize();
      l = localStatFs.getAvailableBlocks() * l / 1048576L;
      return l;
    }
    catch (Exception localException) {}
    return -1L;
  }
  
  @SuppressLint({"InlinedApi"})
  public static boolean b(Context paramContext)
  {
    return (paramContext.getResources().getConfiguration().screenLayout & 0x4) == 4;
  }
  
  public static long c()
  {
    try
    {
      StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
      long l = localStatFs.getBlockSize();
      l = localStatFs.getBlockCount() * l / 1048576L;
      return l;
    }
    catch (Exception localException) {}
    return -1L;
  }
  
  public static boolean c(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    return (paramContext != null) && (paramContext.isConnected());
  }
  
  public static float d(Context paramContext)
  {
    try
    {
      paramContext = paramContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
      int i = paramContext.getIntExtra("level", -1);
      int j = paramContext.getIntExtra("scale", -1);
      if ((i == -1) || (j == -1)) {
        return -1.0F;
      }
      return i / j * 100.0F;
    }
    catch (Exception paramContext) {}
    return -1.0F;
  }
  
  public static long d()
  {
    try
    {
      StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
      long l = localStatFs.getBlockSize();
      l = localStatFs.getAvailableBlocks() * l / 1048576L;
      return l;
    }
    catch (Exception localException) {}
    return -1L;
  }
  
  public static int e(Context paramContext)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics.widthPixels;
  }
  
  public static long e()
  {
    try
    {
      StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
      long l = localStatFs.getBlockSize();
      l = localStatFs.getBlockCount() * l / 1048576L;
      return l;
    }
    catch (Exception localException) {}
    return -1L;
  }
  
  public static int f(Context paramContext)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics.heightPixels;
  }
  
  public static String f()
  {
    String str1 = Build.MANUFACTURER;
    String str2 = Build.MODEL;
    if (str2.startsWith(str1)) {
      return a(str2);
    }
    return a(str1) + " " + str2;
  }
  
  public static String g(Context paramContext)
  {
    String str = paramContext.getPackageName();
    return paramContext.getPackageManager().getInstallerPackageName(str);
  }
  
  public static List<String> h(Context paramContext)
  {
    try
    {
      ArrayList localArrayList = new ArrayList();
      paramContext = paramContext.getPackageManager().getInstalledApplications(128).iterator();
      while (paramContext.hasNext()) {
        localArrayList.add(((ApplicationInfo)paramContext.next()).packageName);
      }
      return localArrayList;
    }
    catch (Exception paramContext)
    {
      return null;
    }
  }
  
  static class a
    extends b.e
  {
    private a() {}
    
    protected String a(Context paramContext)
    {
      String str = h.k(paramContext);
      if (!TextUtils.isEmpty(str)) {
        return str;
      }
      str = UUID.randomUUID().toString();
      h.e(paramContext, str);
      return str;
    }
  }
  
  static class b
    extends b.e
  {
    private b() {}
    
    protected String a(Context paramContext)
    {
      return Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
    }
  }
  
  static class c
    extends b.e
  {
    private c() {}
    
    protected String a(Context paramContext)
    {
      String str = Build.SERIAL;
      paramContext = str;
      if (TextUtils.equals("unknown", str)) {
        paramContext = "";
      }
      return paramContext;
    }
  }
  
  static class d
    extends b.e
  {
    private d() {}
    
    protected String a(Context paramContext)
    {
      try
      {
        paramContext = ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
        return paramContext;
      }
      catch (RuntimeException paramContext)
      {
        PWLog.error("DeviceTelephonyUUID", paramContext);
      }
      return "";
    }
  }
  
  static abstract class e
  {
    private static List<String> b = new ArrayList();
    e a;
    
    public e()
    {
      b.add("9774d56d682e549c");
      b.add("1234567");
      b.add("abcdef");
      b.add("dead00beef");
      b.add("unknown");
    }
    
    private boolean a(String paramString)
    {
      if ((TextUtils.isEmpty(paramString)) || (TextUtils.isEmpty(paramString.replace('0', ' ').replace('-', ' ').trim()))) {}
      while (b.contains(paramString.toLowerCase())) {
        return true;
      }
      return false;
    }
    
    protected abstract String a(Context paramContext);
    
    public void a(e paramE)
    {
      this.a = paramE;
    }
    
    public String b(Context paramContext)
    {
      String str2 = a(paramContext);
      String str1 = str2;
      if (a(str2))
      {
        str1 = str2;
        if (this.a != null) {
          str1 = this.a.b(paramContext);
        }
      }
      return str1;
    }
  }
}
