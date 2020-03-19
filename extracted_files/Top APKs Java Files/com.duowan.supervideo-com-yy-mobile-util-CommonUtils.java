package com.yy.mobile.util;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.os.Looper;
import android.util.Log;
import com.yy.mobile.config.BasicConfig;
import com.yy.mobile.util.log.MLog;
import com.yy.mobile.util.pref.CommonPref;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class CommonUtils
{
  private static long lastClickTime;
  private static String mSessionId;
  private static HashMap<String, SimpleDateFormat> mSimpleDateFormatCache = new HashMap();
  
  public CommonUtils() {}
  
  public static int getAvailMemory()
  {
    try
    {
      ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
      ((ActivityManager)BasicConfig.getInstance().getAppContext().getSystemService("activity")).getMemoryInfo(localMemoryInfo);
      long l = localMemoryInfo.availMem / 1024L;
      return (int)l;
    }
    catch (Throwable localThrowable)
    {
      MLog.error("CommonUtils", "getTotalMemory()" + localThrowable.toString(), new Object[0]);
    }
    return 0;
  }
  
  public static String getCommonTraceId()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(System.currentTimeMillis());
    localStringBuilder.append(new Random().nextInt(90000000) + 1000);
    return localStringBuilder.toString();
  }
  
  public static String getImei(Context paramContext)
  {
    return "";
  }
  
  public static String getServerEnv()
  {
    if (isEnvProductPre()) {
      return "pre";
    }
    return "";
  }
  
  public static String getSessionId()
  {
    if (mSessionId == null) {
      mSessionId = UUID.randomUUID().toString();
    }
    return mSessionId;
  }
  
  public static SimpleDateFormat getSimpleDateFormat(String paramString)
  {
    if (Looper.myLooper() == Looper.getMainLooper())
    {
      SimpleDateFormat localSimpleDateFormat2 = (SimpleDateFormat)mSimpleDateFormatCache.get(paramString);
      SimpleDateFormat localSimpleDateFormat1 = localSimpleDateFormat2;
      if (localSimpleDateFormat2 == null)
      {
        localSimpleDateFormat1 = new SimpleDateFormat(paramString);
        mSimpleDateFormatCache.put(paramString, localSimpleDateFormat1);
      }
      return localSimpleDateFormat1;
    }
    return new SimpleDateFormat(paramString);
  }
  
  public static int getTotalMemory()
  {
    try
    {
      ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
      ((ActivityManager)BasicConfig.getInstance().getAppContext().getSystemService("activity")).getMemoryInfo(localMemoryInfo);
      if (Build.VERSION.SDK_INT < 16) {
        return 1024;
      }
      long l = localMemoryInfo.totalMem;
      return (int)(l >>> 20);
    }
    catch (Throwable localThrowable)
    {
      MLog.error("CommonUtils", "getTotalMemory()" + localThrowable.toString(), new Object[0]);
    }
    return -1;
  }
  
  public static boolean isAppInstalled(Context paramContext, String paramString)
  {
    if (paramContext == null) {
      return false;
    }
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    if (paramContext != null)
    {
      int i = 0;
      while (i < paramContext.size())
      {
        localArrayList.add(((PackageInfo)paramContext.get(i)).packageName);
        i += 1;
      }
    }
    return localArrayList.contains(paramString);
  }
  
  public static boolean isCurrentMainThread()
  {
    return Thread.currentThread().getId() == Looper.getMainLooper().getThread().getId();
  }
  
  public static boolean isEnvProductPre()
  {
    if (!BasicConfig.getInstance().isDebuggable()) {}
    while (CommonPref.instance().getInt("PREF_URI_SETTING", -1) != 1) {
      return false;
    }
    return CommonPref.instance().getBoolean("server_env_pre", false);
  }
  
  public static boolean isFastClick(long paramLong)
  {
    long l = System.currentTimeMillis();
    if (l - lastClickTime < paramLong) {
      return true;
    }
    lastClickTime = l;
    return false;
  }
  
  public static void setGLSurfaceViewThreadName(String paramString)
  {
    if (paramString == null) {}
    for (;;)
    {
      return;
      try
      {
        Thread localThread = Thread.currentThread();
        String str = localThread.getName();
        if ((str != null) && (str.startsWith("GLThread ")))
        {
          localThread.setName(paramString);
          return;
        }
      }
      catch (Throwable paramString)
      {
        Log.e("CommonUtils", "Empty Catch on setGLSurfaceViewThreadName", paramString);
      }
    }
  }
}
