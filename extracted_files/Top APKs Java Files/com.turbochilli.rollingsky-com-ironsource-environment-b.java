package com.ironsource.environment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import org.json.JSONObject;

public final class b
{
  private static String a = null;
  
  public static long a(String paramString)
  {
    paramString = new StatFs(new File(paramString).getPath());
    if (Build.VERSION.SDK_INT < 19) {
      l = paramString.getAvailableBlocks();
    }
    for (long l = paramString.getBlockSize() * l;; l = paramString.getBlockSizeLong() * l)
    {
      return l / 1048576L;
      l = paramString.getAvailableBlocksLong();
    }
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
    Object localObject2;
    if (i < ((List)localObject1).size())
    {
      localObject2 = (ResolveInfo)((List)localObject1).get(i);
      if (paramBoolean) {}
    }
    label181:
    label188:
    label191:
    for (;;)
    {
      int j;
      try
      {
        if ((((ResolveInfo)localObject2).activityInfo.applicationInfo.flags & 0x1) == 0) {
          break label188;
        }
        j = 1;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        break label181;
      }
      localObject2 = localSimpleDateFormat.format(new Date(paramContext.getPackageInfo(((ResolveInfo)localObject2).activityInfo.packageName, 4096).firstInstallTime));
      localJSONObject.put((String)localObject2, localJSONObject.optInt((String)localObject2, 0) + 1);
      break label181;
      return localJSONObject;
      for (;;)
      {
        if (j == 0) {
          break label191;
        }
        i += 1;
        break;
        j = 0;
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
    Object localObject1 = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
    paramContext = ((Class)localObject1).getMethod("getAdvertisingIdInfo", new Class[] { Context.class }).invoke(localObject1, new Object[] { paramContext });
    Object localObject2 = paramContext.getClass().getMethod("getId", new Class[0]);
    localObject1 = paramContext.getClass().getMethod("isLimitAdTrackingEnabled", new Class[0]);
    localObject2 = ((Method)localObject2).invoke(paramContext, new Object[0]).toString();
    boolean bool = ((Boolean)((Method)localObject1).invoke(paramContext, new Object[0])).booleanValue();
    return new String[] { localObject2, bool };
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
        boolean bool = new File(str + paramString).exists();
        if (bool) {
          return true;
        }
        i += 1;
      }
      catch (Exception paramString) {}
    }
    return false;
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
    Object localObject = null;
    File localFile = paramContext.getCacheDir();
    paramContext = localObject;
    if (localFile != null) {
      paramContext = localFile.getPath();
    }
    return paramContext;
  }
  
  public static int k(Context paramContext)
  {
    int j = 0;
    int m = -1;
    try
    {
      paramContext = paramContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
      if (paramContext != null) {}
      for (int i = paramContext.getIntExtra("level", -1);; i = 0)
      {
        if (paramContext != null) {
          j = paramContext.getIntExtra("scale", -1);
        }
        int k = m;
        if (i != -1)
        {
          k = m;
          if (j != -1) {
            k = (int)(i / j * 100.0F);
          }
        }
        return k;
      }
      return -1;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  /* Error */
  public static String l(Context paramContext)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 10	com/ironsource/environment/b:a	Ljava/lang/String;
    //   6: invokestatic 408	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   9: ifne +12 -> 21
    //   12: getstatic 10	com/ironsource/environment/b:a	Ljava/lang/String;
    //   15: astore_0
    //   16: ldc 2
    //   18: monitorexit
    //   19: aload_0
    //   20: areturn
    //   21: aload_0
    //   22: ldc_w 410
    //   25: iconst_0
    //   26: invokevirtual 414	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   29: astore_0
    //   30: aload_0
    //   31: ldc_w 416
    //   34: iconst_1
    //   35: invokeinterface 422 3 0
    //   40: ifeq +58 -> 98
    //   43: aload_0
    //   44: ldc_w 424
    //   47: ldc_w 426
    //   50: invokeinterface 430 3 0
    //   55: astore_1
    //   56: aload_1
    //   57: invokestatic 408	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   60: ifeq +45 -> 105
    //   63: invokestatic 436	java/util/UUID:randomUUID	()Ljava/util/UUID;
    //   66: invokevirtual 437	java/util/UUID:toString	()Ljava/lang/String;
    //   69: putstatic 10	com/ironsource/environment/b:a	Ljava/lang/String;
    //   72: aload_0
    //   73: invokeinterface 441 1 0
    //   78: astore_0
    //   79: aload_0
    //   80: ldc_w 424
    //   83: getstatic 10	com/ironsource/environment/b:a	Ljava/lang/String;
    //   86: invokeinterface 447 3 0
    //   91: pop
    //   92: aload_0
    //   93: invokeinterface 450 1 0
    //   98: getstatic 10	com/ironsource/environment/b:a	Ljava/lang/String;
    //   101: astore_0
    //   102: goto -86 -> 16
    //   105: aload_1
    //   106: putstatic 10	com/ironsource/environment/b:a	Ljava/lang/String;
    //   109: goto -11 -> 98
    //   112: astore_0
    //   113: aload_0
    //   114: invokevirtual 154	java/lang/Exception:printStackTrace	()V
    //   117: goto -19 -> 98
    //   120: astore_0
    //   121: ldc 2
    //   123: monitorexit
    //   124: aload_0
    //   125: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	126	0	paramContext	Context
    //   55	51	1	str	String
    // Exception table:
    //   from	to	target	type
    //   21	98	112	java/lang/Exception
    //   105	109	112	java/lang/Exception
    //   3	16	120	finally
    //   21	98	120	finally
    //   98	102	120	finally
    //   105	109	120	finally
    //   113	117	120	finally
  }
}
