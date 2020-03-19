package com.ts.common.internal.core.collection.utils;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.support.annotation.Nullable;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RootDetectionUtils
{
  private static final int MAX_UID_SAFETY_MARGIN = 500;
  static Map<String, ProbedPackageData> probedPackages;
  
  public RootDetectionUtils() {}
  
  private static boolean binaryExists(String[] paramArrayOfString, String paramString)
  {
    boolean bool2 = false;
    int j = paramArrayOfString.length;
    int i = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i < j)
      {
        String str = paramArrayOfString[i];
        if (fileExists(str + paramString)) {
          bool1 = true;
        }
      }
      else
      {
        return bool1;
      }
      i += 1;
    }
  }
  
  private static void ensureProbedPackages(PackageManager paramPackageManager)
  {
    HashMap localHashMap;
    Object localObject1;
    Object localObject2;
    ProbedPackageData localProbedPackageData;
    for (;;)
    {
      try
      {
        if (probedPackages == null)
        {
          i = 0;
          localHashMap = new HashMap();
          localObject1 = paramPackageManager.getInstalledPackages(128).iterator();
          if (((Iterator)localObject1).hasNext())
          {
            localObject2 = (PackageInfo)((Iterator)localObject1).next();
            localProbedPackageData = new ProbedPackageData();
            localProbedPackageData.name = ((PackageInfo)localObject2).packageName;
            localProbedPackageData.hidden = false;
            localProbedPackageData.info = ((PackageInfo)localObject2);
          }
        }
      }
      finally {}
      try
      {
        localProbedPackageData.uid = paramPackageManager.getApplicationInfo(((PackageInfo)localObject2).packageName, 128).uid;
        j = i;
        if (localProbedPackageData.uid > i)
        {
          j = i;
          if (localProbedPackageData.uid < 99999) {
            j = localProbedPackageData.uid;
          }
        }
      }
      catch (Exception localException)
      {
        for (;;)
        {
          int m;
          int k;
          j = i;
          break;
          j += 1;
          continue;
          k += 1;
        }
      }
      localHashMap.put(localProbedPackageData.name, localProbedPackageData);
      i = j;
    }
    j = 10000;
    if (j < i + 500)
    {
      localObject1 = paramPackageManager.getPackagesForUid(j);
      if (localObject1 != null)
      {
        m = localObject1.length;
        k = 0;
        if (k < m)
        {
          localProbedPackageData = localObject1[k];
          if (localHashMap.containsKey(localProbedPackageData)) {
            break label285;
          }
          localObject2 = new ProbedPackageData();
          ((ProbedPackageData)localObject2).name = localProbedPackageData;
          ((ProbedPackageData)localObject2).hidden = true;
          ((ProbedPackageData)localObject2).info = null;
          localHashMap.put(((ProbedPackageData)localObject2).name, localObject2);
          break label285;
        }
      }
    }
    else
    {
      probedPackages = localHashMap;
      return;
    }
  }
  
  private static boolean fileExists(String paramString)
  {
    try
    {
      boolean bool = new File(paramString).exists();
      if (bool) {
        return true;
      }
    }
    catch (NullPointerException paramString) {}
    return false;
  }
  
  private static ProbedPackageData getProbedPackage(PackageManager paramPackageManager, String paramString)
  {
    ensureProbedPackages(paramPackageManager);
    return (ProbedPackageData)probedPackages.get(paramString);
  }
  
  public static boolean isKnownRootingPackagePresent(PackageManager paramPackageManager)
  {
    return (isPackagePresent(paramPackageManager, "eu.chainfire.supersu", true)) || (isPackagePresent(paramPackageManager, "eu.chainfire.suhide", true));
  }
  
  public static boolean isPackagePresent(PackageManager paramPackageManager, String paramString, boolean paramBoolean)
  {
    paramPackageManager = getProbedPackage(paramPackageManager, paramString);
    if (paramPackageManager == null) {}
    while ((!paramBoolean) && (paramPackageManager.hidden)) {
      return false;
    }
    return true;
  }
  
  public static boolean isRooted()
  {
    return isRooted(false, null);
  }
  
  public static boolean isRooted(boolean paramBoolean, @Nullable PackageManager paramPackageManager)
  {
    if (isRootedSigningKeys()) {}
    while ((isRootedBinariesPresent()) || ((paramBoolean) && ((isRootedRunCommand()) || (isSUActivityPresent(paramPackageManager)) || (isKnownRootingPackagePresent(paramPackageManager))))) {
      return true;
    }
    return false;
  }
  
  public static boolean isRootedBinariesPresent()
  {
    String[] arrayOfString = new String[3];
    arrayOfString[0] = "Superuser";
    arrayOfString[1] = "su";
    arrayOfString[2] = "busybox";
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      String str = arrayOfString[i];
      if (binaryExists(new String[] { "/sbin/", "/system/bin/", "/system/xbin/", "/data/local/xbin/", "/data/local/bin/", "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/", "/system/app/" }, str)) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public static boolean isRootedRunCommand()
  {
    return new ExecShell().executeCommand(ExecShell.SHELL_CMD.check_su_binary) != null;
  }
  
  public static boolean isRootedSigningKeys()
  {
    String str = Build.TAGS;
    return (str != null) && (str.contains("test-keys"));
  }
  
  public static boolean isSUActivityPresent(PackageManager paramPackageManager)
  {
    new Intent("android.intent.action.MAIN").addCategory("android.intent.category.LAUNCHER");
    try
    {
      paramPackageManager = paramPackageManager.getPackageInfo("com.android.setting", 1).activities;
      int j = paramPackageManager.length;
      int i = 0;
      while (i < j)
      {
        boolean bool = paramPackageManager[i].toString().contains("superuser");
        if (bool) {
          return true;
        }
        i += 1;
      }
      return false;
    }
    catch (PackageManager.NameNotFoundException paramPackageManager)
    {
      return false;
    }
  }
  
  static class ProbedPackageData
  {
    public boolean hidden;
    public PackageInfo info;
    public String name;
    public int uid;
    
    ProbedPackageData() {}
  }
}
