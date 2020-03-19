package com.ironsource.a;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.AudioManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.StatFs;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import org.json.JSONObject;

public final class c
{
  private static String a;
  
  public static long a(String paramString)
  {
    paramString = new StatFs(new File(paramString).getPath());
    long l;
    if (Build.VERSION.SDK_INT < 19) {
      l = paramString.getAvailableBlocks() * paramString.getBlockSize();
    } else {
      l = paramString.getAvailableBlocksLong() * paramString.getBlockSizeLong();
    }
    return l / 1048576L;
  }
  
  public static String a()
  {
    return Build.VERSION.RELEASE;
  }
  
  public static JSONObject a(Context paramContext, boolean paramBoolean)
  {
    Object localObject1 = new Intent("android.intent.action.MAIN", null);
    ((Intent)localObject1).addCategory("android.intent.category.LAUNCHER");
    localObject1 = paramContext.getPackageManager().queryIntentActivities((Intent)localObject1, 0);
    JSONObject localJSONObject = new JSONObject();
    paramContext = paramContext.getPackageManager();
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    int i = 0;
    if (i < ((List)localObject1).size())
    {
      localObject2 = (ResolveInfo)((List)localObject1).get(i);
      if (paramBoolean) {}
    }
    try
    {
      if ((((ResolveInfo)localObject2).activityInfo.applicationInfo.flags & 0x1) == 0) {
        break label179;
      }
      j = 1;
    }
    catch (Exception localException)
    {
      int j;
      label111:
      for (;;) {}
    }
    Object localObject2 = localSimpleDateFormat.format(new Date(paramContext.getPackageInfo(((ResolveInfo)localObject2).activityInfo.packageName, 4096).firstInstallTime));
    localJSONObject.put((String)localObject2, localJSONObject.optInt((String)localObject2, 0) + 1);
    break label169;
    ((Exception)localObject2).printStackTrace();
    for (;;)
    {
      label169:
      i += 1;
      break;
      return localJSONObject;
      label179:
      j = 0;
      if (j == 0) {
        break label111;
      }
    }
  }
  
  @TargetApi(19)
  public static boolean a(Activity paramActivity)
  {
    int i = paramActivity.getWindow().getDecorView().getSystemUiVisibility();
    return ((i | 0x1000) == i) || ((i | 0x800) == i);
  }
  
  public static String[] a(Context paramContext)
    throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException
  {
    Object localObject = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
    paramContext = ((Class)localObject).getMethod("getAdvertisingIdInfo", new Class[] { Context.class }).invoke(localObject, new Object[] { paramContext });
    localObject = paramContext.getClass().getMethod("getId", new Class[0]);
    Method localMethod = paramContext.getClass().getMethod("isLimitAdTrackingEnabled", new Class[0]);
    return new String[] { ((Method)localObject).invoke(paramContext, new Object[0]).toString(), String.valueOf(((Boolean)localMethod.invoke(paramContext, new Object[0])).booleanValue()) };
  }
  
  public static int b()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public static String b(Context paramContext)
  {
    return ((TelephonyManager)paramContext.getSystemService("phone")).getNetworkOperatorName();
  }
  
  private static boolean b(String paramString)
  {
    int i = 0;
    for (;;)
    {
      if (i < 8) {}
      try
      {
        String str = new String[] { "/sbin/", "/system/bin/", "/system/xbin/", "/data/local/xbin/", "/data/local/bin/", "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/" }[i];
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(str);
        localStringBuilder.append(paramString);
        boolean bool = new File(localStringBuilder.toString()).exists();
        if (bool) {
          return true;
        }
        i += 1;
      }
      catch (Exception paramString)
      {
        for (;;) {}
      }
    }
    return false;
  }
  
  public static int c(Context paramContext)
  {
    return ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getRotation();
  }
  
  public static String c()
  {
    return Build.MODEL;
  }
  
  public static float d(Context paramContext)
  {
    paramContext = (AudioManager)paramContext.getSystemService("audio");
    return paramContext.getStreamVolume(3) / paramContext.getStreamMaxVolume(3);
  }
  
  public static String d()
  {
    return Build.MANUFACTURER;
  }
  
  public static int e(Context paramContext)
  {
    if ((paramContext instanceof Activity)) {
      return ((Activity)paramContext).getRequestedOrientation();
    }
    return -1;
  }
  
  public static String e()
  {
    return "android";
  }
  
  public static int f(Context paramContext)
  {
    return paramContext.getResources().getConfiguration().orientation;
  }
  
  public static boolean f()
  {
    return b("su");
  }
  
  public static int g()
  {
    return Resources.getSystem().getDisplayMetrics().widthPixels;
  }
  
  public static List<ApplicationInfo> g(Context paramContext)
  {
    return paramContext.getPackageManager().getInstalledApplications(0);
  }
  
  public static int h()
  {
    return Resources.getSystem().getDisplayMetrics().heightPixels;
  }
  
  public static boolean h(Context paramContext)
  {
    return Settings.System.getInt(paramContext.getContentResolver(), "accelerometer_rotation", 0) != 1;
  }
  
  public static float i()
  {
    return Resources.getSystem().getDisplayMetrics().density;
  }
  
  public static File i(Context paramContext)
  {
    return paramContext.getExternalCacheDir();
  }
  
  public static String j(Context paramContext)
  {
    paramContext = paramContext.getCacheDir();
    if (paramContext != null) {
      return paramContext.getPath();
    }
    return null;
  }
  
  public static int k(Context paramContext)
  {
    for (;;)
    {
      try
      {
        paramContext = paramContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        int j = 0;
        if (paramContext == null) {
          break label75;
        }
        i = paramContext.getIntExtra("level", -1);
        if (paramContext != null) {
          j = paramContext.getIntExtra("scale", -1);
        }
        if ((i != -1) && (j != -1)) {
          return (int)(i / j * 100.0F);
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
      return -1;
      label75:
      int i = 0;
    }
  }
  
  public static String l(Context paramContext)
  {
    try
    {
      if (!TextUtils.isEmpty(a))
      {
        paramContext = a;
        return paramContext;
      }
      try
      {
        paramContext = paramContext.getSharedPreferences("Mediation_Shared_Preferences", 0);
        if (paramContext.getBoolean("uuidEnabled", true))
        {
          String str = paramContext.getString("cachedUUID", "");
          if (TextUtils.isEmpty(str))
          {
            a = UUID.randomUUID().toString();
            paramContext = paramContext.edit();
            paramContext.putString("cachedUUID", a);
            paramContext.apply();
          }
          else
          {
            a = str;
          }
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
      paramContext = a;
      return paramContext;
    }
    finally {}
  }
}
