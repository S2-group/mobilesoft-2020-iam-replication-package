package com.xw.wallpaper.utils;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Locale;

public class CommUtils
{
  public static final String GOOGLE_PLAY_CHANNEL = "GooglePlay";
  public static final String GOOGLE_PLAY_PACKAGENAME = "com.android.vending";
  
  public CommUtils() {}
  
  public static int dip2px(Context paramContext, float paramFloat)
  {
    return (int)(paramFloat * paramContext.getResources().getDisplayMetrics().density + 0.5F);
  }
  
  public static boolean existApp(Context paramContext, String paramString)
  {
    boolean bool2 = false;
    boolean bool1 = false;
    if ((paramContext == null) || (TextUtils.isEmpty(paramString))) {
      return false;
    }
    paramContext = paramContext.getPackageManager();
    if (paramContext != null)
    {
      paramContext = paramContext.getInstalledPackages(0);
      int i = 0;
      for (;;)
      {
        bool2 = bool1;
        if (i >= paramContext.size()) {
          break;
        }
        if (((PackageInfo)paramContext.get(i)).packageName.equals(paramString)) {
          bool1 = true;
        }
        i += 1;
      }
    }
    return bool2;
  }
  
  public static String getAvailMemory(Context paramContext)
  {
    ActivityManager localActivityManager = (ActivityManager)paramContext.getSystemService("activity");
    ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
    localActivityManager.getMemoryInfo(localMemoryInfo);
    return Formatter.formatFileSize(paramContext, localMemoryInfo.availMem);
  }
  
  public static String getDeviceID(Context paramContext)
  {
    paramContext = (TelephonyManager)paramContext.getSystemService("phone");
    try
    {
      paramContext = paramContext.getDeviceId();
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return "";
  }
  
  public static PackageInfo getExternalPackageInfo(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = paramContext.getPackageArchiveInfo(paramString, 4097);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static short getLocale()
  {
    short s = 1;
    Locale localLocale = Locale.getDefault();
    if (Locale.SIMPLIFIED_CHINESE.equals(localLocale)) {
      s = 2;
    }
    if (Locale.TRADITIONAL_CHINESE.equals(localLocale)) {
      s = 3;
    }
    if (Locale.TAIWAN.equals(localLocale)) {
      s = 3;
    }
    if (Locale.ENGLISH.equals(localLocale)) {
      s = 1;
    }
    if (Locale.US.equals(localLocale)) {
      s = 1;
    }
    return s;
  }
  
  public static String getMetaOfApplication(Context paramContext, String paramString)
  {
    Object localObject = null;
    String str = "";
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
      if (paramContext != null) {
        str = String.valueOf(paramContext.metaData.getString(paramString));
      }
      return str;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
        paramContext = localObject;
      }
    }
  }
  
  public static String getMetaOfApplicationLong(Context paramContext, String paramString)
  {
    Object localObject = null;
    String str = null;
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
      if (paramContext != null) {
        str = String.valueOf(paramContext.metaData.getLong(paramString));
      }
      return str;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
        paramContext = localObject;
      }
    }
  }
  
  public static Point getScreenSize(Context paramContext)
  {
    paramContext = (WindowManager)paramContext.getSystemService("window");
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramContext.getDefaultDisplay().getMetrics(localDisplayMetrics);
    return new Point(localDisplayMetrics.widthPixels, localDisplayMetrics.heightPixels);
  }
  
  public static int getStatusHeight()
  {
    try
    {
      Class localClass = Class.forName("com.android.internal.R$dimen");
      Object localObject = localClass.newInstance();
      int i = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
      i = Resources.getSystem().getDimensionPixelSize(i);
      return i;
    }
    catch (Exception localException) {}
    return 0;
  }
  
  public static String getTotalMemory(Context paramContext)
  {
    long l2 = 0L;
    long l1 = l2;
    try
    {
      BufferedReader localBufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
      l1 = l2;
      l2 = Integer.valueOf(localBufferedReader.readLine().split("\\s+")[1]).intValue() * 1024;
      l1 = l2;
      localBufferedReader.close();
      l1 = l2;
    }
    catch (IOException localIOException)
    {
      for (;;) {}
    }
    return Formatter.formatFileSize(paramContext, l1);
  }
  
  public static int getVersionCode(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    paramContext = paramContext.getPackageName();
    try
    {
      int i = localPackageManager.getPackageInfo(paramContext, 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return 1;
  }
  
  public static boolean go2GooglePlay(Context paramContext, String paramString)
  {
    boolean bool2;
    if (TextUtils.isEmpty(paramString)) {
      bool2 = false;
    }
    boolean bool1;
    do
    {
      do
      {
        String str;
        boolean bool3;
        Object localObject;
        do
        {
          do
          {
            return bool2;
            str = "market://details?id=" + paramString;
            if (paramString.startsWith("market:")) {
              str = paramString;
            }
            bool3 = false;
            bool1 = false;
            localObject = null;
            if (paramContext != null) {
              localObject = paramContext.getPackageManager();
            }
            bool2 = bool1;
          } while (localObject == null);
          bool2 = bool1;
        } while (paramContext == null);
        bool1 = bool3;
        if (existApp(paramContext, "com.android.vending")) {}
        try
        {
          localObject = ((PackageManager)localObject).getLaunchIntentForPackage("com.android.vending");
          bool1 = bool3;
          if (localObject != null)
          {
            ((Intent)localObject).setAction("android.intent.action.VIEW");
            ((Intent)localObject).addCategory("android.intent.category.DEFAULT");
            ((Intent)localObject).setData(Uri.parse(str));
            paramContext.startActivity((Intent)localObject);
            bool1 = true;
          }
        }
        catch (Exception localException)
        {
          for (;;)
          {
            try
            {
              paramContext.startActivity(paramString);
              return true;
            }
            catch (Exception paramContext)
            {
              paramContext.printStackTrace();
            }
            localException = localException;
            localException.printStackTrace();
            bool1 = false;
          }
        }
        bool2 = bool1;
      } while (bool1);
      paramString = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + paramString));
      bool2 = bool1;
    } while (!isIntentAvailable(paramContext, paramString));
    return false;
  }
  
  public static boolean installNormal(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    File localFile = new File(paramString);
    if ((localFile == null) || (!localFile.exists()) || (!localFile.isFile()) || (localFile.length() <= 0L)) {
      return false;
    }
    try
    {
      localIntent.setDataAndType(Uri.parse("file://" + paramString), "application/vnd.android.package-archive");
      localIntent.addFlags(268435456);
      paramContext.startActivity(localIntent);
      return true;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
  }
  
  public static boolean isIntentAvailable(Context paramContext, Intent paramIntent)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramContext != null)
    {
      bool1 = bool2;
      if (paramIntent != null)
      {
        bool1 = bool2;
        if (paramContext.getPackageManager().queryIntentActivities(paramIntent, 65536).size() > 0) {
          bool1 = true;
        }
      }
    }
    return bool1;
  }
  
  public static boolean isNetworkAvailable(Context paramContext)
  {
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if (paramContext == null) {}
    for (;;)
    {
      return false;
      paramContext = paramContext.getAllNetworkInfo();
      if ((paramContext != null) && (paramContext.length > 0))
      {
        int i = 0;
        while (i < paramContext.length)
        {
          if (paramContext[i].getState() == NetworkInfo.State.CONNECTED) {
            return true;
          }
          i += 1;
        }
      }
    }
  }
  
  public static int px2dip(Context paramContext, float paramFloat)
  {
    return (int)(paramFloat / paramContext.getResources().getDisplayMetrics().density + 0.5F);
  }
  
  @SuppressLint({"InlinedApi"})
  public static void shareFunction(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setType("text/plain");
    localIntent.putExtra("android.intent.extra.SUBJECT", paramContext.getResources().getString(DynamicLoader.getInstance(paramContext).getString("easy3d_wallpaper_s")));
    localIntent.putExtra("android.intent.extra.TITLE", paramContext.getResources().getString(DynamicLoader.getInstance(paramContext).getString("easy3d_wallpaper_s")));
    localIntent.putExtra("android.intent.extra.TEXT", paramContext.getResources().getString(DynamicLoader.getInstance(paramContext).getString("easy3d_wallpaper_s")) + "\t" + "https://play.google.com/store/apps/details?id=" + paramString);
    localIntent.putExtra("sms_body", paramContext.getResources().getString(DynamicLoader.getInstance(paramContext).getString("easy3d_wallpaper_s")) + "\t" + "https://play.google.com/store/apps/details?id=" + paramString);
    localIntent.setFlags(268435456);
    paramContext.startActivity(Intent.createChooser(localIntent, paramContext.getResources().getString(DynamicLoader.getInstance(paramContext).getString("easy3d_wallpaper_s"))));
  }
  
  public static boolean startApp(Context paramContext, String paramString)
  {
    paramString = paramContext.getPackageManager().getLaunchIntentForPackage(paramString);
    if (paramString != null)
    {
      paramContext.startActivity(paramString);
      return true;
    }
    return false;
  }
}
