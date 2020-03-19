package com.skype.m2.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

public class bj
{
  private static final Logger a = Logger.getLogger(bj.class.getSimpleName());
  
  public bj() {}
  
  private static long a(Context paramContext)
  {
    ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
    ((ActivityManager)paramContext.getApplicationContext().getSystemService("activity")).getMemoryInfo(localMemoryInfo);
    return localMemoryInfo.availMem / 1048576L;
  }
  
  public static String a(Context paramContext, boolean paramBoolean)
  {
    String str = b(paramContext, paramBoolean);
    Object localObject = new File(str).listFiles();
    if (localObject != null) {}
    for (int i = localObject.length;; i = 0)
    {
      localObject = a((File[])localObject);
      Runtime localRuntime = Runtime.getRuntime();
      long l1 = (localRuntime.totalMemory() - localRuntime.freeMemory()) / 1048576L;
      long l2 = localRuntime.maxMemory() / 1048576L;
      return String.format(Locale.US, "native dir path: %s names: %s size: %d, appMemUsed: %d maxHeapsize: %d deviceMemAvail: %s", new Object[] { str, localObject, Integer.valueOf(i), Long.valueOf(l1), Long.valueOf(l2), Long.valueOf(a(paramContext)) });
    }
  }
  
  private static String a(File[] paramArrayOfFile)
  {
    StringBuilder localStringBuilder = new StringBuilder("");
    if (paramArrayOfFile != null)
    {
      int j = paramArrayOfFile.length;
      int i = 0;
      while (i < j)
      {
        File localFile = paramArrayOfFile[i];
        localStringBuilder.append(" ").append(localFile.getName());
        i += 1;
      }
    }
    return localStringBuilder.toString();
  }
  
  public static String b(Context paramContext, boolean paramBoolean)
  {
    if (paramBoolean) {
      return paramContext.getApplicationInfo().nativeLibraryDir;
    }
    List localList = paramContext.getPackageManager().getInstalledApplications(0);
    int i = 0;
    while (i < localList.size())
    {
      String str = ((ApplicationInfo)localList.get(i)).publicSourceDir;
      if (str.contains(paramContext.getPackageName()))
      {
        paramContext = str + "!/lib/armeabi-v7a";
        new StringBuilder().append("Path found - ").append(paramContext).toString();
        return paramContext;
      }
      i += 1;
    }
    return "";
  }
}
