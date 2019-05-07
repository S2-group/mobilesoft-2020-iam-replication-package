package com.xmcamera.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Process;
import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

public class a
{
  public static String a()
  {
    return Build.CPU_ABI;
  }
  
  public static String a(Context paramContext)
  {
    return d();
  }
  
  public static String a(Context paramContext, String paramString)
  {
    paramContext = paramContext.getApplicationContext();
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
      if (paramContext.metaData == null) {
        return null;
      }
      paramContext = paramContext.metaData.get(paramString);
      if (paramContext != null)
      {
        com.xmcamera.utils.d.a.b("MetaGet", "metakey:" + paramString + " obj:" + paramContext.toString());
        paramContext = paramContext.toString();
        return paramContext;
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static boolean a(Context paramContext, Intent paramIntent)
  {
    return paramIntent.resolveActivity(paramContext.getPackageManager()) != null;
  }
  
  public static boolean a(Context paramContext, Class paramClass)
  {
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningTasks(25);
    if (paramContext != null)
    {
      paramContext = paramContext.iterator();
      while (paramContext.hasNext()) {
        if (((ActivityManager.RunningTaskInfo)paramContext.next()).topActivity.getClassName().equals(paramClass.getName())) {
          return true;
        }
      }
    }
    return false;
  }
  
  public static boolean a(Context paramContext, String paramString1, String paramString2)
  {
    boolean bool2 = false;
    paramString1 = o.a(paramString1, '.');
    paramString2 = o.a(paramString2, '.');
    int i = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      int j;
      int k;
      if (i < paramString2.size())
      {
        j = Integer.valueOf((String)paramString2.get(i)).intValue();
        if (paramString1.get(i) != null) {
          break label86;
        }
        paramContext = "0";
        k = Integer.valueOf(paramContext).intValue();
        if (j <= k) {
          break label100;
        }
        bool1 = true;
      }
      label86:
      label100:
      do
      {
        return bool1;
        paramContext = (String)paramString1.get(i);
        break;
        bool1 = bool2;
      } while (j < k);
      i += 1;
    }
  }
  
  public static int b(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (Exception paramContext) {}
    return 0;
  }
  
  public static String b()
  {
    return Build.VERSION.RELEASE;
  }
  
  public static boolean b(Context paramContext, Class paramClass)
  {
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningTasks(15);
    if (paramContext != null)
    {
      paramContext = paramContext.iterator();
      while (paramContext.hasNext()) {
        if (((ActivityManager.RunningTaskInfo)paramContext.next()).baseActivity.getClassName().equals(paramClass.getName())) {
          return true;
        }
      }
    }
    return false;
  }
  
  public static boolean b(Context paramContext, String paramString)
  {
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
    int i = Process.myPid();
    paramContext = paramContext.iterator();
    while (paramContext.hasNext())
    {
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
      if (localRunningAppProcessInfo.pid == i) {
        return localRunningAppProcessInfo.processName.equals(paramString);
      }
    }
    return false;
  }
  
  public static boolean b(Context paramContext, String paramString1, String paramString2)
  {
    boolean bool = false;
    try
    {
      paramString1 = "market://details?id=" + paramString1;
      Intent localIntent = new Intent("android.intent.action.VIEW");
      localIntent.setData(Uri.parse(paramString1));
      if (!TextUtils.isEmpty(paramString2)) {
        localIntent.setPackage(paramString2);
      }
      localIntent.addFlags(268435456);
      if (localIntent.resolveActivity(paramContext.getPackageManager()) != null)
      {
        paramContext.startActivity(localIntent);
        bool = true;
      }
      return bool;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static String c()
  {
    return Build.MODEL;
  }
  
  public static boolean c(Context paramContext)
  {
    Iterator localIterator = ((ActivityManager)paramContext.getSystemService("activity")).getRunningTasks(25).iterator();
    while (localIterator.hasNext())
    {
      ActivityManager.RunningTaskInfo localRunningTaskInfo = (ActivityManager.RunningTaskInfo)localIterator.next();
      if (localRunningTaskInfo.topActivity.getPackageName().equals(paramContext.getPackageName()))
      {
        com.xmcamera.utils.d.a.b("Xgreceiver", "topActivity " + localRunningTaskInfo.topActivity.toString());
        return true;
      }
    }
    return false;
  }
  
  public static boolean c(Context paramContext, String paramString)
  {
    boolean bool2 = false;
    paramContext = f(paramContext);
    paramString = o.a(paramString, '.');
    List localList = o.a(paramContext, '.');
    int i = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      int j;
      int k;
      if (i < paramString.size())
      {
        j = Integer.valueOf((String)paramString.get(i)).intValue();
        if (localList.get(i) != null) {
          break label91;
        }
        paramContext = "0";
        k = Integer.valueOf(paramContext).intValue();
        if (j <= k) {
          break label106;
        }
        bool1 = true;
      }
      label91:
      label106:
      do
      {
        return bool1;
        paramContext = (String)localList.get(i);
        break;
        bool1 = bool2;
      } while (j < k);
      i += 1;
    }
  }
  
  public static ActivityManager.RunningTaskInfo d(Context paramContext)
  {
    Iterator localIterator = ((ActivityManager)paramContext.getSystemService("activity")).getRunningTasks(25).iterator();
    while (localIterator.hasNext())
    {
      ActivityManager.RunningTaskInfo localRunningTaskInfo = (ActivityManager.RunningTaskInfo)localIterator.next();
      if (localRunningTaskInfo.topActivity.getPackageName().equals(paramContext.getPackageName())) {
        return localRunningTaskInfo;
      }
    }
    return null;
  }
  
  public static String d()
  {
    String str1 = "35" + Build.BOARD.length() % 10 + Build.BRAND.length() % 10 + Build.CPU_ABI.length() % 10 + Build.DEVICE.length() % 10 + Build.DISPLAY.length() % 10 + Build.HOST.length() % 10 + Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10 + Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10 + Build.TAGS.length() % 10 + Build.TYPE.length() % 10 + Build.USER.length() % 10;
    try
    {
      String str2 = Build.class.getField("SERIAL").get(null).toString();
      str2 = new UUID(str1.hashCode(), str2.hashCode()).toString();
      return str2;
    }
    catch (Exception localException) {}
    return new UUID(str1.hashCode(), "serial".hashCode()).toString();
  }
  
  public static boolean d(Context paramContext, String paramString)
  {
    boolean bool2 = false;
    paramContext = f(paramContext);
    paramString = o.a(paramString, '.');
    List localList = o.a(paramContext, '.');
    int i = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      int j;
      int k;
      if (i < paramString.size())
      {
        j = Integer.valueOf((String)paramString.get(i)).intValue();
        if (localList.get(i) != null) {
          break label124;
        }
        paramContext = "0";
        k = Integer.valueOf(paramContext).intValue();
        if (j <= k) {
          break label139;
        }
        bool1 = bool2;
        if (Integer.valueOf((String)paramString.get(paramString.size() - 1)).intValue() > 1000) {
          bool1 = true;
        }
      }
      label124:
      label139:
      do
      {
        return bool1;
        paramContext = (String)localList.get(i);
        break;
        bool1 = bool2;
      } while (j < k);
      i += 1;
    }
  }
  
  public static boolean e()
  {
    if (Build.MANUFACTURER.equals("Xiaomi")) {}
    for (;;)
    {
      try
      {
        Properties localProperties = new Properties();
        localProperties.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
        if ((localProperties.getProperty("ro.miui.ui.version.code", null) != null) || (localProperties.getProperty("ro.miui.ui.version.name", null) != null)) {
          break label129;
        }
        if (localProperties.getProperty("ro.miui.internal.storage", null) != null)
        {
          break label129;
          com.xmcamera.utils.d.a.d("AAAAAAAAAA", "isMIUI:" + bool);
          return bool;
        }
        bool = false;
        continue;
        com.xmcamera.utils.d.a.d("AAAAAAAAAA", "isMIUI:false");
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
      }
      return false;
      label129:
      boolean bool = true;
    }
  }
  
  public static boolean e(Context paramContext)
  {
    Iterator localIterator = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().iterator();
    while (localIterator.hasNext())
    {
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)localIterator.next();
      if (localRunningAppProcessInfo.processName.equals(paramContext.getPackageName())) {
        return (localRunningAppProcessInfo.importance != 100) && (localRunningAppProcessInfo.importance != 200);
      }
    }
    return true;
  }
  
  public static boolean e(Context paramContext, String paramString)
  {
    boolean bool2 = false;
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i < paramContext.size())
      {
        if (((PackageInfo)paramContext.get(i)).packageName.equalsIgnoreCase(paramString)) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  public static String f(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return "";
  }
  
  public static boolean f()
  {
    return ("thl T9 Pro".toLowerCase().equals(Build.MODEL.toLowerCase())) || (Build.MODEL.toLowerCase().contains("oppo r9"));
  }
  
  public static String g(Context paramContext)
  {
    return paramContext.getPackageName();
  }
  
  public static boolean g()
  {
    return true;
  }
  
  /* Error */
  public static String h(Context paramContext)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aload_0
    //   3: invokevirtual 26	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   6: invokevirtual 30	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   9: astore_1
    //   10: aload_1
    //   11: aload_0
    //   12: invokevirtual 33	android/content/Context:getPackageName	()Ljava/lang/String;
    //   15: iconst_0
    //   16: invokevirtual 39	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   19: astore_0
    //   20: aload_1
    //   21: aload_0
    //   22: invokevirtual 400	android/content/pm/PackageManager:getApplicationLabel	(Landroid/content/pm/ApplicationInfo;)Ljava/lang/CharSequence;
    //   25: checkcast 133	java/lang/String
    //   28: areturn
    //   29: astore_0
    //   30: aconst_null
    //   31: astore_1
    //   32: aload_2
    //   33: astore_0
    //   34: goto -14 -> 20
    //   37: astore_0
    //   38: aload_2
    //   39: astore_0
    //   40: goto -20 -> 20
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	43	0	paramContext	Context
    //   9	23	1	localPackageManager	PackageManager
    //   1	38	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	10	29	android/content/pm/PackageManager$NameNotFoundException
    //   10	20	37	android/content/pm/PackageManager$NameNotFoundException
  }
  
  public static String i(Context paramContext)
  {
    Object localObject = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
    paramContext.getPackageName();
    int i = Process.myPid();
    paramContext = ((List)localObject).iterator();
    while (paramContext.hasNext())
    {
      localObject = (ActivityManager.RunningAppProcessInfo)paramContext.next();
      if (((ActivityManager.RunningAppProcessInfo)localObject).pid == i) {
        return ((ActivityManager.RunningAppProcessInfo)localObject).processName;
      }
    }
    return "";
  }
  
  public static void j(Context paramContext)
  {
    try
    {
      Intent localIntent1 = new Intent("miui.intent.action.APP_PERM_EDITOR");
      localIntent1.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity");
      localIntent1.putExtra("extra_pkgname", paramContext.getPackageName());
      paramContext.startActivity(localIntent1);
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      try
      {
        Intent localIntent2 = new Intent("miui.intent.action.APP_PERM_EDITOR");
        localIntent2.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
        localIntent2.putExtra("extra_pkgname", paramContext.getPackageName());
        paramContext.startActivity(localIntent2);
        return;
      }
      catch (Exception localException)
      {
        Intent localIntent3 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        localIntent3.setData(Uri.fromParts("package", paramContext.getPackageName(), null));
        paramContext.startActivity(localIntent3);
      }
    }
  }
}
